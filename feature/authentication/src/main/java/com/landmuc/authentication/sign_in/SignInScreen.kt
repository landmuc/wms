package com.landmuc.authentication.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.landmuc.authentication.R
import com.landmuc.authentication.component.UserInputDialog
import com.landmuc.authentication.di.signInViewModelModule
import com.landmuc.network.SupabaseClient
import com.landmuc.domain.dto.EventDto
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.ui.ProviderButtonContent
import io.github.jan.supabase.compose.auth.ui.email.OutlinedEmailField
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSuccessfulGoogleLogIn: () -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: SignInViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val client = SupabaseClient

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val controller = LocalSoftwareKeyboardController.current

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val name by viewModel.name.collectAsState()
    val surname by viewModel.surname.collectAsState()
    val showOverlay by viewModel.showOverlay.collectAsState()


    // handling Google sign in
    val googleSignIn = client.supabaseClient.composeAuth.rememberSignInWithGoogle(
        onResult = { result -> //optional error handling
            when (result) {
                is NativeSignInResult.Success -> {
                    viewModel.checkFirstLogInWithGoogle { onResult ->
                        if (onResult) {
                            viewModel.switchOverlayVisibility()
                            Toast.makeText(context, "Enter your name and surname", Toast.LENGTH_SHORT).show()
                        } else {
                            onSuccessfulGoogleLogIn()
                            Toast.makeText(context, context.getString(R.string.feature_authentication_you_are_signed_in), Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                is NativeSignInResult.ClosedByUser -> {}
                is NativeSignInResult.Error -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }

                is NativeSignInResult.NetworkError -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        },
        fallback = {
        // optional: add custom error handling, not required by default
        }
    )

    // actual Screen
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        if (showOverlay) {
            UserInputDialog(
                onDismissRequest = { viewModel.switchOverlayVisibility() },
                name = name,
                onNameChanged = viewModel::onNameChanged,
                surname = surname,
                onSurnameChanged = viewModel::onSurnameChanged,
                onSubmit = {
                    viewModel.sendNewGoogleUserInfoToDatabase(name = name, surname = surname)
                    onSuccessfulGoogleLogIn()
                }
                )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "WMS",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp
            )
            Text(text = "always up to date")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            OutlinedEmailField(
                value = email,
                onValueChange = viewModel::onEmailChanged,
                label = { Text(stringResource(id = R.string.feature_authentication_label_email)) },
            )
            OutlinedPasswordField(
                value = password,
                onValueChange = viewModel::onPasswordChanged,
                label = { Text(stringResource(id = R.string.feature_authentication_label_password)) },
            )
            Button(
                onClick = {
                    controller?.hide()
                    viewModel.signIn { signInResult ->
                        if (signInResult) {
                            onSignInClick()
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = context.getString(R.string.feature_authentication_invalid_email_and_or_password),
                                    duration = SnackbarDuration.Short
                                )
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
                    text = "Sign In",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            HorizontalDivider(
                modifier = Modifier.width(200.dp),
                thickness = 2.dp
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
            )
            OutlinedButton(
                onClick = { googleSignIn.startFlow() },
                content = { ProviderButtonContent(provider = Google) }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            TextButton(
                onClick = { onSignUpClick() },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.feature_authentication_sign_up),
                    fontSize = 16.sp
                )
            }
        }
    }
}


//@Composable
//@Preview(showSystemUi = true)
//fun SignInScreenPreview() {
//    KoinApplication(
//        application = { modules(signInViewModelModule) }
//    ) {
//        SignInScreen(
//            onSuccessfulGoogleLogIn = {},
//            onSignUpClick = {},
//            onSignInClick = {}
//        )
//    }
//}