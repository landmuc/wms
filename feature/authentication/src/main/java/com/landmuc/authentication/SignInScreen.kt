package com.landmuc.authentication

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.landmuc.authentication.di.signInViewModelModule
import com.landmuc.network.BuildConfig
import com.landmuc.network.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.ui.ProviderButtonContent
import io.github.jan.supabase.compose.auth.ui.email.OutlinedEmailField
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField
import io.github.jan.supabase.compose.auth.ui.password.PasswordRule
import io.github.jan.supabase.compose.auth.ui.password.rememberPasswordRuleList
import io.github.jan.supabase.gotrue.providers.Google
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import java.security.MessageDigest
import java.util.UUID

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val client = SupabaseClient.supabaseClient
    val action = client.composeAuth.rememberSignInWithGoogle(
        onResult = { result -> //optional error handling
            when (result) {
                is NativeSignInResult.Success -> {
                    Toast.makeText(context, "You are signed in!", Toast.LENGTH_SHORT).show()
                }
                is NativeSignInResult.ClosedByUser -> {
                    Toast.makeText(context,"ClosedByUser", Toast.LENGTH_SHORT).show()
                }
                is NativeSignInResult.Error -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }
                is NativeSignInResult.NetworkError -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        },
        fallback = { // optional: add custom error handling, not required by default

        }
    )

    val controller = LocalSoftwareKeyboardController.current

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
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
            label = { Text(stringResource(id = R.string.feature_authentication_label_email))},
        )
        OutlinedPasswordField(
            value = password,
            onValueChange = viewModel::onPasswordChanged,
            label = { Text(stringResource(id = R.string.feature_authentication_label_password))},
            rules = rememberPasswordRuleList(
                PasswordRule.minLength(8),
                PasswordRule.containsSpecialCharacter(),
                PasswordRule.containsDigit(),
                PasswordRule.containsUppercase(),
                PasswordRule.containsLowercase()
            )
        )
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
            onClick = { },
            content = { ProviderButtonContent(provider = Google)}
        )
        GoogleSignInButton()

        Button(
            onClick = { action.startFlow() }
        ) {
            Text("Google ComposeAuth Login")
        }


    }
}


@Composable
fun GoogleSignInButton() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val onClick: () -> Unit = {

    val credentialManager = CredentialManager.create(context)

    val rawNonce = UUID.randomUUID().toString()
    val bytes = rawNonce.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    val hashedNonce = digest.fold("") {str, it -> str +"%02x".format(it)}


    val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(BuildConfig.SERVER_CLIENT_ID)
        .setNonce(hashedNonce) // random piece of string you can pass to a OAuth sign in process to prevent replay attacks
        .build()

    val request: GetCredentialRequest = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

        coroutineScope.launch {
        try {
            val result = credentialManager.getCredential(
                request = request,
                context = context
            )
            val credential = result.credential
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val googleIdToken = googleIdTokenCredential.idToken

            Log.i(TAG, googleIdToken)
            Toast.makeText(context, "You are signed in!", Toast.LENGTH_SHORT).show()
        } catch (e: androidx.credentials.exceptions.GetCredentialException) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: GoogleIdTokenParsingException) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    }

    Button(onClick = onClick) { Text("Google sign in test!")}
}


@Composable
@Preview(showSystemUi = true)
fun SignInScreenPreview() {
    KoinApplication(
        application = { modules(signInViewModelModule) }
    ) {
        SignInScreen()
    }
}