package com.tusxapps.step_master.android.ui.main.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.AvatarImage
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryTopBar
import com.tusxapps.step_master.android.ui.main.profile.components.clan.ClanBlock
import com.tusxapps.step_master.android.ui.main.profile.components.rating.RatingBlock
import com.tusxapps.step_master.android.ui.main.profile.settings.SettingsScreen
import com.tusxapps.step_master.android.ui.navigation.BottomBarScreen
import com.tusxapps.step_master.android.ui.navigation.BottomBarScreenOptions
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.viewModels.main.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

object ProfileScreen : BottomBarScreen() {
    override val options: BottomBarScreenOptions
        @Composable
        get() = BottomBarScreenOptions(
            name = stringResource(id = R.string.profile),
            icon = painterResource(id = R.drawable.ic_profile)
        )

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ProfileViewModel>()
        val state by viewModel.state.collectAsState()
        val lce by viewModel.lce.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        if (navigator.lastEvent == StackEvent.Pop) {
            viewModel.fetchProfile()
        }

        LCEView(lce = lce) {
            ProfileScreenBody(
                state = state,
                onSettingsIconClick = remember { { navigator.push(SettingsScreen) } }
            )
        }
    }
}

@Composable
private fun ProfileScreenBody(
    state: ProfileViewModel.State,
    onSettingsIconClick: () -> Unit
) {
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
            text = "Профиль",
            hasBackButton = false,
            icon = R.drawable.ic_settings,
            onIconClick = onSettingsIconClick
        )
        ExtraLargeSpacer()
        AvatarImage(
            image = state.avatar,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        LargeSpacer()
        Text(
            text = state.nickname,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = state.name,
            style = MaterialTheme.typography.titleSmall,
            color = Color.LightGray
        )
        ExtraLargeSpacer()
        RatingBlock(
            userRegionPlace = state.userRegionPlace,
            regionUserCount = state.regionUserCount,
            userCountyPlace = state.userCountyPlace,
            countryUserCount = state.countryUserCount,
            userClanPlace = state.userClanPlace,
            clanUserCount = state.clanUserCount
        )
        ExtraLargeSpacer()
        ClanBlock()
    }
}

@Composable
@Preview
private fun ProfileScreenPreview() {
    val state = remember {
        ProfileViewModel.State(
            nickname = "Тест",
            name = "Тест Тестович",
            avatar = null,
            userRegionPlace = 1,
            regionUserCount = 10,
            userCountyPlace = 2,
            countryUserCount = 20,
            userClanPlace = 3,
            clanUserCount = 30
        )
    }

    MyApplicationTheme {
        Surface {
            ProfileScreenBody(state = state, onSettingsIconClick = {})
        }
    }
}