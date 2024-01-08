package com.tusxapps.step_master.android.ui.main.summary.achievements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.ui.components.CollapsableRow
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.SecondaryTopBar
import com.tusxapps.step_master.android.ui.main.summary.achievements.composables.AchievableItem
import com.tusxapps.step_master.android.ui.main.summary.achievements.composables.AchievablePinDialog
import com.tusxapps.step_master.android.ui.main.summary.achievements.composables.AchievementFlowRow
import com.tusxapps.step_master.android.ui.theme.largeDp
import com.tusxapps.step_master.domain.achievements.models.Achievable
import com.tusxapps.step_master.viewModels.main.AchievementsViewModel
import org.koin.androidx.compose.koinViewModel

object AchievementsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<AchievementsViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow


        Scaffold(
            topBar = {
                SecondaryTopBar(onBackClick = navigator::pop)
            },
            content = {
                Box(Modifier.padding(it)) {
                    AchievementsScreenBody(
                        state = state,
                        onAchievablePinClick = remember { { viewModel.pinAchievable(it) } }
                    )
                }
            }
        )
    }
}

@Composable
private fun AchievementsScreenBody(
    state: AchievementsViewModel.State,
    onAchievablePinClick: (Achievable) -> Unit = {}
) {
    var selectedAchievable: Achievable? by remember {
        mutableStateOf(null)
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(largeDp)
    ) {
        Text(text = "Звания", style = MaterialTheme.typography.headlineLarge)
        AchievablesList(state.ranks)
        LargeSpacer()
        Text(text = "Достижения", style = MaterialTheme.typography.headlineLarge)
        AchievablesList(
            state.achievements,
            onAchievableClick = {
                if (it.isAchieved)
                    selectedAchievable = it
            }
        )
    }

    selectedAchievable?.let {
        AchievablePinDialog(
            selectedAchievable = it,
            onDismiss = { selectedAchievable = null },
            onPinAchievableClick = onAchievablePinClick
        )
    }
}

@Composable
private fun ColumnScope.AchievablesList(
    achievables: List<Achievable>,
    onAchievableClick: (Achievable) -> Unit = {}
) {
    achievables.groupBy(Achievable::type).forEach {
        MediumSpacer()
        CollapsableRow(text = it.key) {
            AchievementFlowRow {
                it.value.forEach { achievable ->
                    AchievableItem(
                        name = achievable.name,
                        svgLink = achievable.link,
                        isAchieved = achievable.isAchieved,
                        modifier = Modifier.clickable(onClick = { onAchievableClick(achievable) })
                    )
                }
            }
        }
    }
}
