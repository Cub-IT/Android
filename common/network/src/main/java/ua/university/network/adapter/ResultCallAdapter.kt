package ua.university.network.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import ua.university.network.result.NetworkResult
import java.lang.reflect.Type

class ResultCallAdapter<R>(private val type: Type) : CallAdapter<R, Call<NetworkResult<R>>> {

    override fun responseType() = type

    override fun adapt(call: Call<R>): Call<NetworkResult<R>> = ResultCall(call)
}