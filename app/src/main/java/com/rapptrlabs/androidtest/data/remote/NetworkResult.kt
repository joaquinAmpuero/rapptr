package com.rapptrlabs.androidtest.data.remote


sealed class NetworkResult<out T, out Y> {
    class Success<T, Y>(val data: T) : NetworkResult<T, Y>()
    class Error<T, Y>(val code: Int, val message: String?, val error: Y) : NetworkResult<T, Y>()
    class Exception<T, Y>(val e: Throwable) : NetworkResult<T, Y>()
}