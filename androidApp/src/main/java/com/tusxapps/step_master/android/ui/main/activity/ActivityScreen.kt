package com.tusxapps.step_master.android.ui.main.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
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
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.components.PrimaryTopBar
import com.tusxapps.step_master.android.ui.main.activity.components.activity.ActivityBlock
import com.tusxapps.step_master.android.ui.main.activity.components.weekCalendar.WeekActivityCalendar
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
            ActivityScreenBody(
                state = state,
                onBackCLick = remember { { navigator.pop() } }
            )
        }
    }
}

@Composable
fun ActivityScreenBody(
    state: ActivityViewModel.State,
    onBackCLick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = extraLargeDp),
        horizontalAlignment = Alignment.Start
    ) {
        ExtraLargeSpacer()
        PrimaryTopBar(
            text = "Активность",
            onBackClick = onBackCLick,
            icon = R.drawable.ic_calendar
        )
        WeekActivityCalendar(
            days = state.days,
            currentDayIndex = state.currentDayIndex
        )
        ExtraLargeSpacer()
        ActivityBlock(
            steps = state.days[state.currentDayIndex].steps ?: 0,
            goalSteps = state.days[state.currentDayIndex].goalSteps,
            activeTime = state.days[state.currentDayIndex].activeTime ?: 0,
            goalActiveTime = state.days[state.currentDayIndex].goalActiveTime,
            calories = state.days[state.currentDayIndex].calories ?: 0,
            goalCalories = state.days[state.currentDayIndex].goalCalories
        )
    }
}

@Preview
@Composable
fun ActivityScreenPreview() {
    val state = ActivityViewModel.State()

    MyApplicationTheme {
        Surface {
            ActivityScreenBody(state = state, onBackCLick = {})
        }
    }
}