package com.rapptrlabs.androidtest.data.repository

import com.rapptrlabs.androidtest.data.dto.LoginResponse
import com.rapptrlabs.androidtest.data.remote.ApiInterface
import com.rapptrlabs.androidtest.data.remote.NetworkResult
import com.rapptrlabs.androidtest.data.remote.ResponseHandler
import com.rapptrlabs.androidtest.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api: ApiInterface) :
    LoginRepository {
    override suspend fun postLogin(
        username: String,
        password: String
    ): NetworkResult<LoginResponse, LoginResponse> {
        return ResponseHandler.handleApiCall {
            api.postLogin(
                username,
                password
            )
        }
    }
}