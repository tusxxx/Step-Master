package com.tusxapps.step_master.android.ui.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.components.PrimaryTextField
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.components.SmallTextHint
import com.tusxapps.step_master.android.ui.components.TextRowWithLink
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.domain.Gender
import com.tusxapps.step_master.utils.LCE
import com.tusxapps.step_master.viewModels.auth.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

object RegisterScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<RegisterViewModel>()
        val state by viewModel.state.collectAsState()

        RegisterScreenBody(
            state = state,
            onRegisterFieldsChange = { viewModel.onRegisterFieldsChange(it) },
            onRegisterClick = {},
            onLoginClick = {}
        )
    }
}

@Composable
private fun RegisterScreenBody(
    state: RegisterViewModel.State,
    onRegisterFieldsChange: (RegisterViewModel.State.RegisterFields) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = extraLargeDp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Регистрация", style = MaterialTheme.typography.headlineLarge)
        SmallSpacer()
        TextRowWithLink(startText = "Уже есть аккаунт?", linkText = "Войти", onClick = onLoginClick)
        ExtraLargeSpacer()
        PrimaryTextField(
            value = state.registerFields.email,
            hint = "Email",
            onValueChange = {
                onRegisterFieldsChange(state.registerFields.copy(email = it))
            }
        )
        MediumSpacer()
        SmallTextHint()
        ExtraLargeSpacer()
        PrimaryTextField(
            value = state.registerFields.nickname,
            hint = "Ник",
            onValueChange = {
                onRegisterFieldsChange(state.registerFields.copy(nickname = it))
            }
        )

        PrimaryTextField(
            value = state.registerFields.fullName,
            hint = "Имя Фамилия",
            onValueChange = {
                onRegisterFieldsChange(state.registerFields.copy(fullName = it))
            }
        )

        PrimaryTextField(
            value = state.registerFields.region,
            hint = "Регион",
            onValueChange = {
                onRegisterFieldsChange(state.registerFields.copy(region = it))
            }
        )

        // todo gender selector

        PrimaryTextField(
            value = state.registerFields.password,
            hint = "Пароль",
            onValueChange = {
                onRegisterFieldsChange(state.registerFields.copy(password = it))
            }
        )

        PrimaryTextField(
            value = state.registerFields.passwordRecovery,
            hint = "Подтверждение пароля",
            onValueChange = {
                onRegisterFieldsChange(state.registerFields.copy(passwordRecovery = it))
            }
        )

        // todo checkbox policy

        PrimaryButton(text = "Далее", onClick = onRegisterClick)
    }
}

@Composable
@Preview
private fun RegisterScreenBodyPreview() {
    val state = remember {
        RegisterViewModel.State(
            LCE.Idle,
            RegisterViewModel.State.RegisterFields("", "", "", "", Gender.NONE, "", "", false)
        )
    }

    MyApplicationTheme {
        Surface {
            RegisterScreenBody(
                state = state,
                onRegisterFieldsChange = {},
                onRegisterClick = {},
                onLoginClick = {}
            )
        }
    }
}