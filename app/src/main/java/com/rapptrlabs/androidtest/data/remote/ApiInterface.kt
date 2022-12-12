package com.rapptrlabs.androidtest.data.remote

import com.rapptrlabs.androidtest.data.dto.LoginResponse
import com.rapptrlabs.androidtest.data.dto.ResponseModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("/Tests/scripts/chat_log.php")
    suspend fun getRetailerPoints(): ResponseModel

    @POST("/Tests/scripts/login.php")
    @FormUrlEncoded
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>
}