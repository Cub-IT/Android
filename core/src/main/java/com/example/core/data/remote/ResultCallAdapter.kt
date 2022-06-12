package com.example.core.data.remote

import com.example.core.util.Result
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultCallAdapter<R>(private val type: Type) : CallAdapter<R, Call<Result<R, Exception>>> {

    override fun responseType() = type

    override fun adapt(call: Call<R>): Call<Result<R, Exception>> = ResultCall(call)
}