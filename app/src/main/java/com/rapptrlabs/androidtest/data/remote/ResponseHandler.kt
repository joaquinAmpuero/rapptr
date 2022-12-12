package com.rapptrlabs.androidtest.data.remote

import com.google.gson.Gson
import retrofit2.Response

class ResponseHandler {
    companion object {
        suspend inline fun <reified T, reified Y> handleApiCall(call: suspend () -> Response<T>): NetworkResult<T, Y> {
            return try {
                val response = call.invoke()
                if (response.isSuccessful && response.body() != null) {
                    NetworkResult.Success(response.body() as T)
                } else {
                    val errorResponse =
                        Gson().fromJson(response.errorBody()?.charStream(), Y::class.java)
                    NetworkResult.Error(
                        response.code(),
                        response.message(),
                        error = errorResponse
                    )
                }
            } catch (e: Exception) {
                NetworkResult.Exception(e)
            }
        }
    }
}
