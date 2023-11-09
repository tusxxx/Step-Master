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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.ui.auth.email_confirmation.EmailConfirmationScreen
import com.tusxapps.step_master.android.ui.auth.register.components.GenderSelector
import com.tusxapps.step_master.android.ui.components.CheckboxWithText
import com.tusxapps.step_master.android.ui.components.EmailTextField
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PasswordTextField
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
        val navigator = LocalNavigator.currentOrThrow

        LCEView(lce = state.lce) {
            RegisterScreenBody(
                state = state,
                onEmailChange = remember { { viewModel.onRegisterFieldsChange(email = it) } },
                onNicknameChange = remember { { viewModel.onRegisterFieldsChange(nickname = it) } },
                onFullNameChange = remember { { viewModel.onRegisterFieldsChange(fullName = it) } },
                onRegionChange = remember { { viewModel.onRegisterFieldsChange(region = it) } },
                onGenderSelect = remember { { viewModel.onRegisterFieldsChange(gender = it) } },
                onPasswordChange = remember { { viewModel.onRegisterFieldsChange(password = it) } },
                onPasswordConfirmChange = remember {
                    {
                        viewModel.onRegisterFieldsChange(
                            passwordRecovery = it
                        )
                    }
                },
                onAgreementChange = remember { { viewModel.onRegisterFieldsChange(isAgreedWithPolicy = it) } },
                onRegisterClick = remember {
                    {
                        viewModel.onRegisterClick(onSuccess = {
                            navigator.push(
                                EmailConfirmationScreen
                            )
                        })
                    }
                },
                onLoginClick = remember { { navigator.pop() } }
            )
        }
    }
}

@Composable
private fun RegisterScreenBody(
    state: RegisterViewModel.State,
    onEmailChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onFullNameChange: (String) -> Unit,
    onRegionChange: (String) -> Unit,
    onGenderSelect: (Gender) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onAgreementChange: (Boolean) -> Unit,
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
        ExtraLargeSpacer()
        Text(text = "Регистрация", style = MaterialTheme.typography.headlineLarge)
        SmallSpacer()
        TextRowWithLink(startText = "Уже есть аккаунт?", linkText = "Войти", onClick = onLoginClick)
        ExtraLargeSpacer()
        EmailTextField(
            value = state.email,
            onValueChange = onEmailChange
        )
        MediumSpacer()
        SmallTextHint()
        ExtraLargeSpacer()
        PrimaryTextField(
            value = state.nickname,
            hint = "Ник",
            onValueChange = onNicknameChange
        )
        ExtraLargeSpacer()
        PrimaryTextField(
            value = state.fullName,
            hint = "Имя Фамилия",
            onValueChange = onFullNameChange
        )
        ExtraLargeSpacer()
        PrimaryTextField(
            value = state.region,
            hint = "Регион",
            onValueChange = onRegionChange
        )
        ExtraLargeSpacer()
        GenderSelector(state.gender, onGenderSelect)
        ExtraLargeSpacer()
        PasswordTextField(
            value = state.password,
            hint = "Пароль",
            onValueChange = onPasswordChange
        )
        ExtraLargeSpacer()
        PasswordTextField(
            value = state.passwordConfirmation,
            hint = "Подтверждение пароля",
            onValueChange = onPasswordConfirmChange
        )
        ExtraLargeSpacer()
        CheckboxWithText(
            isChecked = state.isAgreedWithPolicy,
            text = "Согласен с обработкой персональных данных и пользовательским соглашением",
            onCheckedChange = onAgreementChange
        )
        ExtraLargeSpacer()
        PrimaryButton(text = "Далее", onClick = onRegisterClick)
        LargeSpacer()
    }
}

@Composable
@Preview
private fun RegisterScreenBodyPreview() {
    val state = remember {
        RegisterViewModel.State(
            LCE.Idle, "", "", "", "", Gender.NONE, "", "", false
        )
    }

    MyApplicationTheme {
        Surface {
            RegisterScreenBody(
                state = state,
                onEmailChange = {},
                onNicknameChange = {},
                onFullNameChange = {},
                onRegionChange = {},
                onPasswordChange = {},
                onPasswordConfirmChange = {},
                onRegisterClick = {},
                onGenderSelect = {},
                onAgreementChange = {},
                onLoginClick = {}
            )
        }
    }
}