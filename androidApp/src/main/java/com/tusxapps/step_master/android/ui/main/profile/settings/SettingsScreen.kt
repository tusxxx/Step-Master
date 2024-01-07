package com.tusxapps.step_master.android.ui.main.profile.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.AvatarImage
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.components.PrimaryTopBar
import com.tusxapps.step_master.android.ui.main.profile.region_selection.RegionSelectionScreen
import com.tusxapps.step_master.android.ui.main.profile.settings.components.PasswordSettingsTextField
import com.tusxapps.step_master.android.ui.main.profile.settings.components.ProfileTextField
import com.tusxapps.step_master.android.ui.main.profile.settings.components.RegionTextField
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.utils.photoPicker
import com.tusxapps.step_master.viewModels.main.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

object SettingsScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SettingsViewModel>()
        val state by viewModel.state.collectAsState()
        val lce by viewModel.lce.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        if (navigator.lastEvent == StackEvent.Pop) {
            viewModel.fetchData()
        }

        LCEView(lce = lce) {
            SettingsScreenBody(
                state = state,
                onRegionFieldClick = remember { { navigator.push(RegionSelectionScreen) } },
                onPhotoPick = remember { { viewModel.onPhotoPick(it) } },
                onSaveClick = remember { { viewModel.onSaveClick() } },
                onNicknameChange = remember { { viewModel.onNicknameChange(it) } },
                onNameChange = remember { { viewModel.onNameChange(it) } },
                onCurrentPasswordChange = remember { { viewModel.onCurrentPasswordChange(it) } },
                onNewPasswordChange = remember { { viewModel.onNewPasswordChange(it) } },
                onConfirmPasswordChange = remember { { viewModel.onConfirmPasswordChange(it) } },
                onEditClick = remember { { viewModel.changeEditMode() } }
            )
        }
    }
}

@Composable
fun SettingsScreenBody(
    state: SettingsViewModel.State,
    onRegionFieldClick: () -> Unit,
    onPhotoPick: (ByteArray) -> Unit,
    onSaveClick: () -> Unit,
    onNicknameChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onCurrentPasswordChange: (String) -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onEditClick: () -> Unit
) {
    val context = LocalContext.current
    val imageModifier = remember(state.isEditMode) {
        val modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
        if (state.isEditMode) {
            modifier.photoPicker {
                context.contentResolver
                    .openInputStream(it)
                    .use {
                        it
                            ?.readBytes()
                            ?.let(onPhotoPick)
                    }
            }
        } else {
            modifier
        }
    }
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
            onIconClick = onEditClick
        )
        ExtraLargeSpacer()
        AvatarImage(
            state.profilePictureUrl,
            imageModifier,
            state.isUploadingImage,
            state.isEditMode
        )
        ExtraLargeSpacer()
        ProfileTextField(
            value = state.nickname,
            hint = "Никнейм",
            onValueChange = onNicknameChange,
            canBeEdited = state.isEditMode
        )
        ExtraLargeSpacer()
        ProfileTextField(
            value = state.name,
            hint = "Имя Фамилия",
            onValueChange = onNameChange,
            canBeEdited = state.isEditMode
        )
        ExtraLargeSpacer()
        RegionTextField(
            value = state.regionName,
            hint = "Регион",
            onValueChange = {},
            isEditMode = state.isEditMode,
            onIconClick = onRegionFieldClick
        )
        ExtraLargeSpacer()
        PasswordSettingsTextField(
            value = state.currentPassword,
            label = "Пароль",
            hint = "Введите старый пароль",
            onValueChange = onCurrentPasswordChange,
            canBeEdited = state.isEditMode
        )
        AnimatedVisibility(
            visible = state.isEditMode,
            enter = slideInVertically { fullHeight -> fullHeight },
            exit = slideOutVertically { fullHeight -> fullHeight }
        ) {
            Column {
                ExtraLargeSpacer()
                PasswordSettingsTextField(
                    value = state.newPassword,
                    hint = "Введите новый пароль",
                    onValueChange = onNewPasswordChange,
                    canBeEdited = state.isEditMode
                )
                ExtraLargeSpacer()
                PasswordSettingsTextField(
                    value = state.confirmPassword,
                    hint = "Повторите пароль",
                    onValueChange = onConfirmPasswordChange,
                    canBeEdited = state.isEditMode
                )
                ExtraLargeSpacer()
                PrimaryButton(text = "Сохранить", onClick = onSaveClick)
            }
        }
        ExtraLargeSpacer()
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    val state = remember {
        SettingsViewModel.State(
            nickname = "",
            name = "",
            profilePictureUrl = null,
            currentPassword = "",
            newPassword = "",
            confirmPassword = ""
        )
    }

    MyApplicationTheme {
        Surface {
            SettingsScreenBody(
                state = state,
                onRegionFieldClick = {},
                onPhotoPick = {},
                onSaveClick = {},
                onNicknameChange = {},
                onNameChange = {},
                onCurrentPasswordChange = {},
                onNewPasswordChange = {},
                onConfirmPasswordChange = {},
                onEditClick = {}
            )
        }
    }
}