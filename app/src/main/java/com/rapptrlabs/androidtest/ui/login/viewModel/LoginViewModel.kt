package com.rapptrlabs.androidtest.ui.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rapptrlabs.androidtest.data.dto.LoginResponse
import com.rapptrlabs.androidtest.data.remote.NetworkResult
import com.rapptrlabs.androidtest.domain.repository.LoginRepository
import com.rapptrlabs.androidtest.ui.login.viewModel.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {
    private val _loginState: MutableStateFlow<LoginState> =
        MutableStateFlow(LoginState.InitialState)
    val loginState = _loginState.asStateFlow()

    fun doLogin(username: String, password: String) {
        val start = System.currentTimeMillis()
        _loginState.update { LoginState.Loading }
        viewModelScope.launch {
            when (val response = repository.postLogin(username, password)) {
                is NetworkResult.Success -> success(response, start)
                is NetworkResult.Error -> error(response, start)
                is NetworkResult.Exception -> exceptionGiven(response, start)
            }
        }
    }

    private fun exceptionGiven(
        response: NetworkResult.Exception<LoginResponse, LoginResponse>,
        start: Long
    ) {
        _loginState.update {
            val end = System.currentTimeMillis()
            LoginState.GenericError(
                "${end - start}"
            )
        }
    }

    private fun error(
        response: NetworkResult.Error<LoginResponse, LoginResponse>,
        start: Long
    ) {
        _loginState.update {
            val end = System.currentTimeMillis()
            LoginState.Error(
                response.error.message ?: "",
                response.error.code,
                "${end - start}"
            )
        }
    }

    private fun success(
        response: NetworkResult.Success<LoginResponse, LoginResponse>,
        start: Long
    ) {
        val end = System.currentTimeMillis()
        _loginState.update {
            LoginState.Success(
                response.data.message,
                response.data.code,
                "${end - start}"
            )
        }
    }

    fun closeDialog() {
        _loginState.update { LoginState.InitialState }
    }

    companion object {
        const val TAG = "repository"
    }
}