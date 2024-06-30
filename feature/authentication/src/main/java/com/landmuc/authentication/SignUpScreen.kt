package com.landmuc.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.landmuc.domain.event.SignUpResult
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit,
    viewModel: SignUpViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val controller = LocalSoftwareKeyboardController.current

    val email by viewModel.emailInput.collectAsState()
    val password by viewModel.passwordInput.collectAsState()
    val passwordConfirm by viewModel.passwordConfirmInput.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Sign Up")},
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // text field to enter email for sign up
            OutlinedTextField(
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primaryContainer),
                        shape = RoundedCornerShape(50)
                    ),
                value = email,
                onValueChange = viewModel::updateEmailInput,
                placeholder = { Text("Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email"
                    )
                }
            )
            // text field to enter password for sign up
            OutlinedTextField(
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primaryContainer),
                        shape = RoundedCornerShape(50)
                    ),
                value = password,
                onValueChange = viewModel::updatePasswordInput,
                placeholder = { Text("Password") },
                leadingIcon =  { Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )},
                visualTransformation = PasswordVisualTransformation() // masks the password input
            )
            // text field to confirm password above
            OutlinedTextField(
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primaryContainer),
                        shape = RoundedCornerShape(50)
                    ),
                value = passwordConfirm,
                onValueChange = viewModel::updatePasswordConfirmInput,
                placeholder = { Text("Confirm Password") },
                leadingIcon =  { Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Confirm Password"
                )},
                visualTransformation = PasswordVisualTransformation() // masks the password input
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
            )
            Button(
                onClick = {
                    controller?.hide()
                    viewModel.signUp { signUpResult ->
                        when (signUpResult) {
                            is SignUpResult.InvalidEmail -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Invalid email! Please provide a valid email!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                            is SignUpResult.InvalidPassword -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Invalid password! Your password must be at least six characters long!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                            is SignUpResult.InvalidPasswordMatch -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Your passwords do not match! Please provide matching passwords!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                            is SignUpResult.ValidCredentials -> {
                                viewModel.signUpRequest { signUpRequestResult ->
                                    if (signUpRequestResult) {
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "You successfully signed up! Log in on the first screen!",
                                                duration = SnackbarDuration.Long
                                            )
                                        }
                                    } else {
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "Something went wrong. You could not sign up.",
                                                duration = SnackbarDuration.Short
                                            )
                                        }
                                    }
                                }
                            }
                        }

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
        }
    }
}