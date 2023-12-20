package com.tusxapps.step_master.android.ui.main.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.components.PrimaryTopBar
import com.tusxapps.step_master.android.ui.main.regionSelection.RegionSelectionScreen
import com.tusxapps.step_master.android.ui.main.settings.components.PasswordSettingsTextField
import com.tusxapps.step_master.android.ui.main.settings.components.ProfileTextField
import com.tusxapps.step_master.android.ui.main.settings.components.RegionTextField
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.viewModels.main.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

object SettingsScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SettingsViewModel>()
        val state by viewModel.state.collectAsState()
        val lce by viewModel.lce.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LCEView(lce = lce) {
            SettingsScreenBody(
                state = state,
                onRegionFieldClick = remember { { navigator.push(RegionSelectionScreen) } }
            )
        }
    }
}

@Composable
fun SettingsScreenBody(
    state: SettingsViewModel.State,
    onRegionFieldClick: () -> Unit
) {
    var isEditMode by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = extraLargeDp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExtraLargeSpacer()
        PrimaryTopBar(
            text = "Настройки",
            hasBackButton = false,
            icon = R.drawable.ic_edit,
            onIconClick = {
                isEditMode = !isEditMode
            }
        )
        ExtraLargeSpacer()
        Image(
            painter = painterResource(id = R.mipmap.pfp_round),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .shadow(elevation = 0.dp)
        )
        ExtraLargeSpacer()
        ProfileTextField(
            value = state.nickname,
            hint = "Никнейм",
            onValueChange = {},
            canBeEdited = isEditMode
        )
        ExtraLargeSpacer()
        ProfileTextField(
            value = state.name,
            hint = "Имя Фамилия",
            onValueChange = {},
            canBeEdited = isEditMode
        )
        ExtraLargeSpacer()
        RegionTextField(
            value = state.regionName,
            hint = "Регион",
            onValueChange = {},
            isEditMode = isEditMode,
            onIconClick = onRegionFieldClick
        )
        ExtraLargeSpacer()
        PasswordSettingsTextField(
            value = state.currentPassword,
            label = "Пароль",
            hint = "Введите старый пароль",
            onValueChange = {},
            canBeEdited = isEditMode
        )
        AnimatedVisibility(
            visible = isEditMode,
            enter = slideInVertically { fullHeight -> fullHeight / 2 },
            exit = slideOutVertically { fullHeight -> fullHeight / 2 }
        ) {
            Column {
                ExtraLargeSpacer()
                PasswordSettingsTextField(
                    value = state.newPassword,
                    hint = "Введите новый пароль",
                    onValueChange = {},
                    canBeEdited = isEditMode
                )
                ExtraLargeSpacer()
                PasswordSettingsTextField(
                    value = state.confirmPassword,
                    hint = "Повторите пароль",
                    onValueChange = {},
                    canBeEdited = isEditMode
                )
                ExtraLargeSpacer()
                PrimaryButton(text = "Сохранить", onClick = { /*TODO*/ })
            }
        }
        ExtraLargeSpacer()
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    val state = remember {
        SettingsViewModel.State(
            nickname = "",
            name = "",
            profilePictureUrl = "",
            currentPassword = "",
            newPassword = "",
            confirmPassword = ""
        )
    }

    MyApplicationTheme {
        Surface {
            SettingsScreenBody(state = state, onRegionFieldClick = {})
        }
    }
}