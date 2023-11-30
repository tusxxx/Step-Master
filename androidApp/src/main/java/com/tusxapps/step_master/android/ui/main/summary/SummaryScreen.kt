package com.tusxapps.step_master.android.ui.main.summary

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.main.activity.ActivityScreen
import com.tusxapps.step_master.android.ui.main.summary.components.SummaryTopBar
import com.tusxapps.step_master.android.ui.main.summary.components.activity.ActivityBlock
import com.tusxapps.step_master.android.ui.main.summary.components.body.BodyCompositionBlock
import com.tusxapps.step_master.android.ui.main.summary.components.steps.StepsBlock
import com.tusxapps.step_master.android.ui.main.summary.components.water.WaterBlock
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.viewModels.main.SummaryViewModel
import org.koin.androidx.compose.koinViewModel

object SummaryScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SummaryViewModel>()
        val state by viewModel.state.collectAsState()
        val lce by viewModel.lce.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val fitnessService = get<FitnessService>()

        LaunchedEffect(Unit){
            fitnessService.tryGetGoogleFitSteps()
        }
        LCEView(lce = lce, isRefreshable = true, onRefresh = viewModel::fetchData) {
            SummaryScreenBody(
                state = state,
                addGlassOnClick = remember { viewModel::addGlass },
                removeGlassOnClick = remember { viewModel::removeGlass },
                onGoalStepsChange = remember { viewModel::onGoalStepsCountChange },
                onChangeBodyComposition = remember { viewModel::onBodyCompositionChange },
                onActivityBlockClick = remember { { navigator.push(ActivityScreen) } }
            )
        }
    }
}

@Composable
private fun SummaryScreenBody(
    state: SummaryViewModel.State,
    addGlassOnClick: () -> Unit,
    removeGlassOnClick: () -> Unit,
    onGoalStepsChange: (Int) -> Unit,
    onChangeBodyComposition: (
        weight: Float?,
        muscle: Float?,
        height: Float?,
        fat: Float?
    ) -> Unit,
    onActivityBlockClick: () -> Unit
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
        SummaryTopBar()
        ExtraLargeSpacer()
        ActivityBlock(
            steps = state.stepsCount,
            goalSteps = state.goalStepsCount,
            activeTime = state.activeTime,
            goalActiveTime = state.goalActiveTime,
            calories = state.calories,
            goalCalories = state.goalCalories,
            onBlockClick = onActivityBlockClick
        )
        ExtraLargeSpacer()
        StepsBlock(
            stepsCount = state.stepsCount,
            goalStepsCount = state.goalStepsCount,
            onSaveGoalClick = onGoalStepsChange
        )
        ExtraLargeSpacer()
        WaterBlock(
            glassCount = state.glassCount,
            addGlassButtonOnClick = addGlassOnClick,
            removeGlassButtonOnClick = removeGlassOnClick
        )
        ExtraLargeSpacer()
        BodyCompositionBlock(
            weightValue = state.bodyWeight,
            muscleValue = state.muscleWeight,
            fatValue = state.bodyFat,
            onSaveBodyCompositionClick = onChangeBodyComposition
        )
    }
}

@Composable
@Preview
private fun RegisterScreenBodyPreview() {
    val state = remember {
        SummaryViewModel.State(
            stepsCount = 8000,
            goalStepsCount = 12000,
            glassCount = 2,
            bodyWeight = 51.5f,
            muscleWeight = 25f,
            bodyFat = 15f,
            calories = 170,
            goalCalories = 200,
            activeTime = 135,
            goalActiveTime = 180
        )
    }

    MyApplicationTheme {
        Surface {
            SummaryScreenBody(
                state = state,
                addGlassOnClick = {},
                removeGlassOnClick = {},
                onGoalStepsChange = {},
                onChangeBodyComposition = { _, _, _, _ -> },
                onActivityBlockClick = {}
            )
        }
    }
}