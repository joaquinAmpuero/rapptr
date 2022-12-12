package com.rapptrlabs.androidtest.ui.login

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.ui.common.CustomDialog
import com.rapptrlabs.androidtest.ui.common.CustomToolbar
import com.rapptrlabs.androidtest.ui.login.viewModel.LoginViewModel
import com.rapptrlabs.androidtest.ui.login.viewModel.state.LoginState
import kotlinx.coroutines.delay

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen(viewModel = hiltViewModel())
}

@Composable
fun LoginScreen(
    navController: NavController? = null,
    viewModel: LoginViewModel
) {
    Scaffold(
        topBar = {
            CustomToolbar(navController, R.string.login_title, true)
        }
    ) { padding ->
        val loginState = viewModel.loginState.collectAsState()
        loginView(padding, loginState, viewModel)
        handleDialog(loginState, viewModel, navController)
    }
}

@Composable
private fun loginView(
    padding: PaddingValues,
    loginState: State<LoginState>,
    viewModel: LoginViewModel
) {
    var usernameState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var passwordState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var focusUserNameSavable by rememberSaveable { mutableStateOf(false) }
    var focusPasswordSavable by rememberSaveable { mutableStateOf(false) }
    val focusRequesterUsername = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }

    FocusHandler(focusUserNameSavable, focusRequesterUsername)
    FocusHandler(focusPasswordSavable, focusRequesterPassword)

    // main login view.
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(R.drawable.gpc_sunset),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(color = colorResource(id = R.color.background).copy(alpha = 0.2f))
        ) {
            Column(
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp)
                        .focusRequester(focusRequesterUsername)
                        .onFocusChanged {
                            focusUserNameSavable = it.hasFocus
                        },
                    value = usernameState,
                    onValueChange = { usernameState = it },
                    placeholder = {
                        HintText(hintText = R.string.placeholder_username)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = colorResource(id = R.color.button_blue),
                        cursorColor = colorResource(id = R.color.button_blue),
                        backgroundColor = Color.Transparent
                    )
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, bottom = 8.dp)
                        .focusRequester(focusRequesterPassword)
                        .onFocusChanged {
                            focusPasswordSavable = it.hasFocus
                        },
                    value = passwordState,
                    onValueChange = { passwordState = it },
                    placeholder = {
                        HintText(R.string.placeholder_password)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = colorResource(id = R.color.button_blue),
                        cursorColor = colorResource(id = R.color.button_blue),
                        backgroundColor = Color.Transparent
                    )
                )
                LoginButton(
                    shouldBeEnabled = (usernameState.text.isNotEmpty() && passwordState.text.isNotEmpty())
                            && loginState.value !is LoginState.Loading,
                    onClick = { viewModel.doLogin(usernameState.text, passwordState.text) }
                )
            }
        }
    }
}

@Composable
private fun handleDialog(
    loginState: State<LoginState>,
    viewModel: LoginViewModel,
    navController: NavController?
) {
    when (val state = loginState.value) {
        is LoginState.Error -> CustomDialog(
            state.code,
            "${state.errorMessage}\n${stringResource(R.string.time)}${state.time}",
            stringResource(id = R.string.dialog_dismiss)
        ) { viewModel.closeDialog() }
        LoginState.InitialState -> {}
        LoginState.Loading -> {}
        is LoginState.Success -> CustomDialog(
            state.code,
            "${state.successMessage}\n${stringResource(R.string.time)}${state.time}",
            stringResource(id = R.string.dialog_dismiss)
        ) {
            viewModel.closeDialog()
            navController?.navigateUp()
        }
        is LoginState.GenericError -> CustomDialog(
            title = stringResource(id = R.string.error),
            message = stringResource(id = R.string.errorState),
            buttonText = stringResource(id = R.string.dialog_dismiss)
        ) {
            viewModel.closeDialog()
        }
    }
}

@Composable
private fun FocusHandler(
    focusUserNameSavable: Boolean,
    focusRequesterUsername: FocusRequester
) {
    LaunchedEffect(focusUserNameSavable) {
        if (focusUserNameSavable) {
            focusRequesterUsername.requestFocus()
        }
    }
}

@Composable
fun LoginButton(onClick: () -> Unit, shouldBeEnabled: Boolean) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.button_blue),
            contentColor = Color.White
        ),
        enabled = shouldBeEnabled,
        content = {
            Text(
                text = stringResource(id = R.string.login_button_label),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
        },
        modifier = Modifier
            .height(55.dp)
            .fillMaxWidth()
            .padding(start = 26.dp, end = 26.dp),
        onClick = onClick
    )
}

@Composable
fun HintText(@StringRes hintText: Int) {
    Text(
        text = stringResource(id = hintText),
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.hint_color),
        fontSize = 16.sp
    )
}
