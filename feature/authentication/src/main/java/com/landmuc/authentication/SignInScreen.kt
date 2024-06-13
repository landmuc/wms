package com.landmuc.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.landmuc.authentication.di.signInViewModelModule
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ui.ProviderButtonContent
import io.github.jan.supabase.compose.auth.ui.email.OutlinedEmailField
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField
import io.github.jan.supabase.compose.auth.ui.password.rememberPasswordRuleList
import io.github.jan.supabase.gotrue.providers.Google
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel()
) {
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
            rules = rememberPasswordRuleList()
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
            onClick = { /*TODO*/ }, // Sign in with Google
            content = { ProviderButtonContent(provider = Google)}
        )



    }
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