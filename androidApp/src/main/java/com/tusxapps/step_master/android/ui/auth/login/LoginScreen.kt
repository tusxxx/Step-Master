package com.tusxapps.step_master.android.ui.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.auth.password_recovery.PasswordRecoveryScreen
import com.tusxapps.step_master.android.ui.auth.register.RegisterScreen
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.components.PrimaryTextField
import com.tusxapps.step_master.android.ui.components.TextRowWithLink
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.largeDp
import com.tusxapps.step_master.viewModels.auth.LoginViewModel
import org.koin.androidx.compose.koinViewModel

// TODO refactor as PasswordRecoveryScreen
object LoginScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<LoginViewModel>()
        val state by viewModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = largeDp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.headlineLarge
            )

            TextRowWithLink(
                startText = "Нет аккаунта?",
                linkText = "Зарегистрироваться",
                onClick = { navigator.push(RegisterScreen) }
            )

            LargeSpacer()

            PrimaryTextField(
                value = state.email,
                hint = "Email",
                onValueChange = { viewModel.onAuthFieldsChange(email = it) },
                modifier = Modifier.fillMaxWidth()
            )

            LargeSpacer()

            PrimaryTextField(
                value = state.password,
                hint = "Пароль",
                onValueChange = { viewModel.onAuthFieldsChange(password = it) },
                modifier = Modifier.fillMaxWidth()
            )

            LargeSpacer()

            TextRowWithLink(
                startText = "Забыли пароль?",
                linkText = "Восстановите пароль",
                onClick = { navigator.push(PasswordRecoveryScreen) }
            )

            ExtraLargeSpacer()
            MediumSpacer()

            PrimaryButton(
                text = "Войти",
                onClick = {
                    viewModel.onLoginClick(
                        onSuccess = { TODO("MainScreen") }
                    )
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
private fun LoginScreenPreview() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    MyApplicationTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = largeDp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.headlineLarge
            )

            TextRowWithLink(
                startText = "Нет аккаунта?",
                linkText = "Зарегистрироватся",
                onClick = {}
            )

            LargeSpacer()

            PrimaryTextField(
                value = email,
                hint = "Email",
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth()
            )

            LargeSpacer()

            PrimaryTextField(
                value = password,
                hint = "Пароль",
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth()
            )

            LargeSpacer()

            TextRowWithLink(
                startText = "Забыли пароль?",
                linkText = "Восстановите пароль",
                onClick = {}
            )

            ExtraLargeSpacer()
            MediumSpacer()

            PrimaryButton(
                text = "Войти",
                onClick = {}
            )
        }
    }
}