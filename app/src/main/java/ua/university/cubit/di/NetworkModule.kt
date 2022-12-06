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

    /*@Provides
    @Named("groupRetrofit")
    fun provideGroupRetrofit(userSource: UserSource, okHttpClient: OkHttpClient): Retrofit {
        val userId = userSource.getUser()?.id
            ?: throw IllegalStateException("User is authorized but user's id is ${userSource.getUser()?.id}")
        val baseUrl = API_URL.plus("api/v1/user/$userId/")
        return createRetrofit(baseUrl, okHttpClient).build()
    }

    @Provides
    @Named("postRetrofit")
    fun providePostRetrofit(userSource: UserSource, okHttpClient: OkHttpClient): Retrofit {
        val userId = userSource.getUser()?.id
            ?: throw IllegalStateException("User is authorized but user's id is ${userSource.getUser()?.id}")
        val baseUrl = API_URL.plus("api/v1/group/")
        return createRetrofit(baseUrl, okHttpClient).build()
    }

    @Provides
    @Named("taskRetrofit")
    fun provideTaskRetrofit(userSource: UserSource, okHttpClient: OkHttpClient): Retrofit {
//        val userId = userSource.getUser()?.id
//            ?: throw IllegalStateException("User is authorized but user's id is ${userSource.getUser()?.id}")
        val baseUrl = API_URL.plus("api/v1/task/")
        return createRetrofit(baseUrl, okHttpClient).build()
    }
*/
    private fun createRetrofit(url: String, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(ResultAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .build()
    }

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}