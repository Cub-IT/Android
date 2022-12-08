package ua.university.cubit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.university.network.adapter.ResultAdapterFactory
import ua.university.preferences.UserSharedPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val API_URL: String = "https://2b4.app/api/"

    @Provides
    @Singleton
    @Named("authRetrofit")
    fun provideAuthRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val baseUrl = API_URL.plus("auth/")
        return createRetrofit(baseUrl, okHttpClient).build()
    }

    @Provides
    fun provideRetrofit(userSource: UserSharedPreferences, okHttpClient: OkHttpClient): Retrofit {
        return createRetrofit(API_URL, okHttpClient).build()
    }

    private fun createRetrofit(url: String, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(ResultAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .addInterceptor(interceptor)
            .build()
    }

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    class HeaderInterceptor @Inject constructor(
        private val userSharedPreferences: UserSharedPreferences
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response{
            val request = chain.request()
                .newBuilder()
                .apply {
                    userSharedPreferences.getTokens()?.let {
                        this.addHeader("Authorization", "Bearer " + it.access)
                    }
                }
                .build()
            return chain.proceed(request)
        }
    }
}