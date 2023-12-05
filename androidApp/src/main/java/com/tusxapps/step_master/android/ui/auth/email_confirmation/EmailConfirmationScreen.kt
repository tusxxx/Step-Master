package com.tusxapps.step_master.android.ui.auth.email_confirmation

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
import com.tusxapps.step_master.android.ui.auth.email_confirmation.components.CodeNumberFields
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.main.summary.SummaryScreen
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.viewModels.auth.EmailConfirmationViewModel
import org.koin.androidx.compose.koinViewModel

object EmailConfirmationScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<EmailConfirmationViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LCEView(lce = state.lce) {
            EmailConfirmationScreenBody(
                state = state,
                onFirstValueChange = remember { { viewModel.onFirstCodeNumberChange(it) } },
                onSecondValueChange = remember { { viewModel.onSecondCodeNumberChange(it) } },
                onThirdValueChange = remember { { viewModel.onThirdCodeNumberChange(it) } },
                onFourthValueChange = remember { { viewModel.onFourthCodeNumberChange(it) } },
                onFifthValueChange = remember { { viewModel.onFifthCodeNumberChange(it) } },
                onRegisterClick = remember {
                    { viewModel.onRegisterClick { navigator.replaceAll(SummaryScreen) } }
                }
            )
        }
    }
}

@Composable
private fun EmailConfirmationScreenBody(
    state: EmailConfirmationViewModel.State,
    onFirstValueChange: (String) -> Unit,
    onSecondValueChange: (String) -> Unit,
    onThirdValueChange: (String) -> Unit,
    onFourthValueChange: (String) -> Unit,
    onFifthValueChange: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    MyApplicationTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = extraLargeDp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Подтвердите почту", style = MaterialTheme.typography.headlineLarge)
            MediumSpacer()
            Text(
                text = "Мы выслали Вам письмо с кодом на почту.",
                style = MaterialTheme.typography.labelLarge
            )
            ExtraLargeSpacer()
            CodeNumberFields(
                state.firstCodeNumber,
                state.secondCodeNumber,
                state.thirdCodeNumber,
                state.fourthCodeNumber,
                state.fifthCodeNumber,
                onFirstValueChange,
                onSecondValueChange,
                onThirdValueChange,
                onFourthValueChange,
                onFifthValueChange
            )
            ExtraLargeSpacer()
            ExtraLargeSpacer()
            PrimaryButton(text = "Зарегистрироваться", onClick = onRegisterClick)
        }
    }
}

@Composable
@Preview
private fun EmailConfirmationScreenPreview() {
    val state = remember {
        EmailConfirmationViewModel.State()
    }

    MyApplicationTheme {
        Surface {
            EmailConfirmationScreenBody(
                state = state,
                onFirstValueChange = {},
                onSecondValueChange = {},
                onThirdValueChange = {},
                onFourthValueChange = {},
                onFifthValueChange = {},
                onRegisterClick = {},
            )
        }
    }
}