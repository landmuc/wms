package com.landmuc.authentication.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.landmuc.authentication.R
import com.landmuc.authentication.di.signUpViewModelModule
import com.landmuc.domain.event.SignUpResult
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ui.email.OutlinedEmailField
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField
import io.github.jan.supabase.compose.auth.ui.password.PasswordRule
import io.github.jan.supabase.compose.auth.ui.password.rememberPasswordRuleList
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit,
    viewModel: SignUpViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val controller = LocalSoftwareKeyboardController.current

    val email by viewModel.email.collectAsState()
    val name by viewModel.name.collectAsState()
    val surname by viewModel.surname.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordConfirm by viewModel.passwordConfirm.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { stringResource(id = R.string.feature_authentication_sign_up)},
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.feature_authentication_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            // text field to enter email for sign up
            OutlinedEmailField(
                value = email,
                onValueChange = viewModel::onEmailChanged,
                label = { Text(stringResource(id = R.string.feature_authentication_label_email)) },
            )

            // text field to enter name for sign up
            OutlinedTextField(
                value = name,
                onValueChange = viewModel::onNameChanged,
                singleLine = true,
                label = { Text(stringResource(id = R.string.feature_authentication_label_name))},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Name"
                    )
                }
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
            )

            // text field to enter surname for sign up
            OutlinedTextField(
                value = surname,
                onValueChange = viewModel::onSurnameChanged,
                singleLine = true,
                label = { Text(stringResource(id = R.string.feature_authentication_label_surname)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Surname"
                    )
                }
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
            )

            // text field to enter password for sign up
            OutlinedPasswordField(
                value = password,
                onValueChange = viewModel::onPasswordChanged,
                label = { Text(stringResource(id = R.string.feature_authentication_label_password)) },
                rules = rememberPasswordRuleList(
                    PasswordRule.minLength(8),
                    PasswordRule.containsSpecialCharacter(),
                    PasswordRule.containsDigit(),
                    PasswordRule.containsUppercase(),
                    PasswordRule.containsLowercase()
                )
            )

            // text field to confirm the password above
            OutlinedPasswordField(
                value = passwordConfirm,
                onValueChange = viewModel::onPasswordConfirmChanged,
                label = { Text(stringResource(id = R.string.feature_authentication_label_password)) },
                rules = rememberPasswordRuleList(
                    PasswordRule.minLength(8),
                    PasswordRule.containsSpecialCharacter(),
                    PasswordRule.containsDigit(),
                    PasswordRule.containsUppercase(),
                    PasswordRule.containsLowercase()
                )
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
            )

            HorizontalDivider(
                modifier = Modifier.width(200.dp),
                thickness = 2.dp
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(22.dp)
            )

            Button(
                onClick = {
                    controller?.hide()
                    viewModel.signUp { signUpResult ->
                        when (signUpResult) {
                            // could be removed, gets checked now in OutlinedEmailField
                            is SignUpResult.InvalidEmail -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Invalid email! Please provide a valid email!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                            // could be removed, gets checked now in OutlinedPasswordField
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
                                        scope.launch {
                                            viewModel.sendNewUserInfoToDatabase(
                                                name = name,
                                                surname = surname,
                                                email = email
                                            )
                                        }

                                        scope.launch {
                                            viewModel.onEmailChanged("")
                                            viewModel.onNameChanged("")
                                            viewModel.onSurnameChanged("")
                                            viewModel.onPasswordChanged("")
                                            viewModel.onPasswordConfirmChanged("")
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


@Preview
@Composable
fun SignUpScreenPreview() {
    KoinApplication(
        application = { modules(signUpViewModelModule)}
    ) {
        SignUpScreen(onBackClick = { })
    }
}