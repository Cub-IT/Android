package com.example.core.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BaseRetrofit by lazy { createRetrofit("HERE GOES BASE URL").build() }

private fun createRetrofit(url: String) = Retrofit.Builder()
    .baseUrl(url)
    .client(createOkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(createLoggingInterceptor())
        .build()
}

private fun createLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}