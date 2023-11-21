package com.tusxapps.step_master.android.ui.main.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.components.PrimaryTopBar
import com.tusxapps.step_master.android.ui.main.activity.components.activity.ActivityBlock
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.viewModels.main.ActivityViewModel
import org.koin.androidx.compose.koinViewModel

object ActivityScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ActivityViewModel>()
        val state by viewModel.state.collectAsState()
        val lce by viewModel.lce.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LCEView(lce = lce) {
            ActivityScreenBody(state = state)
        }
    }
}

@Composable
fun ActivityScreenBody(
    state: ActivityViewModel.State
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = extraLargeDp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        ExtraLargeSpacer()
        PrimaryTopBar(
            text = "Активность",
            onBackClick = { /*TODO*/ },
            icon = R.drawable.ic_calendar
        )
        ActivityBlock(
            steps = state.stepsCount,
            goalSteps = state.goalStepsCount,
            activeTime = state.activeTime,
            goalActiveTime = state.goalActiveTime,
            calories = state.calories,
            goalCalories = state.goalCalories
        )

    }
}

@Preview
@Composable
fun ActivityScreenPreview() {
    val state = ActivityViewModel.State(
        stepsCount = 0,
        goalStepsCount = 12000,
        activeTime = 0,
        goalActiveTime = 120,
        calories = 0,
        goalCalories = 250,
        dates = emptyList()
    )

    MyApplicationTheme {
        Surface {
            ActivityScreenBody(
                state
            )
        }
    }
}