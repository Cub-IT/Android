package com.example.core.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BaseRetrofit by lazy { createRetrofit("HERE GOES BASE URL").build() }

private fun createRetrofit(url: String) = Retrofit.Builder()
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())