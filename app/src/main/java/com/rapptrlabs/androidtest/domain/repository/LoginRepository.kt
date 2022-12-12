package com.rapptrlabs.androidtest.domain.repository

import com.rapptrlabs.androidtest.data.dto.LoginResponse
import com.rapptrlabs.androidtest.data.remote.NetworkResult

interface LoginRepository {
    suspend fun postLogin(
        username: String,
        password: String
    ): NetworkResult<LoginResponse, LoginResponse>
}