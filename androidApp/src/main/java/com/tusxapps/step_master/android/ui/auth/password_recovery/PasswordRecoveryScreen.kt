package com.tusxapps.step_master.android.ui.auth.password_recovery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.components.PrimaryTextField
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.utils.LCE
import com.tusxapps.step_master.viewModels.auth.PasswordRecoveryViewModel
import org.koin.androidx.compose.koinViewModel
import java.lang.Exception

object PasswordRecoveryScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<PasswordRecoveryViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LCEView(lce = state.lce) {
            PasswordRecoveryScreenBody(
                state = state,
                onEmailChange = viewModel::onEmailChange,
                onRecoveryClick = viewModel::onRecoveryClick,
                onBackClick = navigator::pop
            )
        }
    }
}

@Composable
private fun PasswordRecoveryScreenBody(
    state: PasswordRecoveryViewModel.State,
    onEmailChange: (String) -> Unit,
    onRecoveryClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = extraLargeDp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Восстановить пароль", style = MaterialTheme.typography.headlineLarge
        )

        if (state.isEmailSent) {
            ExtraLargeSpacer()
            Text(
                text = "Мы выслали Вам на почту письмо с паролем, используйте его для авторизации",
                textAlign = TextAlign.Center
            )
            ExtraLargeSpacer()
            ExtraLargeSpacer()
            PrimaryButton(text = "Вернуться к авторизации", onClick = onBackClick)
        } else {
            ExtraLargeSpacer()
            SmallSpacer()
            PrimaryTextField(value = state.email, hint = "Email", onValueChange = onEmailChange)
            ExtraLargeSpacer()
            PrimaryButton(text = "Восстановить пароль", onClick = onRecoveryClick)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PasswordRecoveryScreenBodyPreview() {
    MyApplicationTheme {
        Surface {
            PasswordRecoveryScreenBody(
                state = PasswordRecoveryViewModel.State("email", false),
                onEmailChange = {},
                onRecoveryClick = {},
                onBackClick = {}
            )
        }
    }
}