package com.rapptrlabs.androidtest.ui.login.viewModel.state

import androidx.annotation.Keep

@Keep
sealed class LoginState {
    object InitialState : LoginState()
    object Loading : LoginState()
    data class Error(val errorMessage: String, val code: String, val time: String) : LoginState()
    data class GenericError(val time: String) : LoginState()
    data class Success(val successMessage: String, val code: String, val time: String) : LoginState()
}