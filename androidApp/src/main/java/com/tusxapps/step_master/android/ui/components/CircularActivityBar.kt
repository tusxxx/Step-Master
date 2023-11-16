package com.tusxapps.step_master.android.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularActivityBar(
    steps: Int,
    maxSteps: Int,
    activeTime: Int,
    maxActiveTime: Int,
    calories: Int,
    maxCalories: Int,
    modifier: Modifier = Modifier,
) {
    var height by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current
    val strokeWidth = remember(height) {
        height / 10
    }

    val stepsProgress = remember(steps, maxSteps) {
        steps / maxSteps.toFloat()
    }
    val activeTimeProgress = remember(activeTime, maxActiveTime) {
        activeTime / maxActiveTime.toFloat()
    }
    val caloriesProgress = remember(calories, maxCalories) {
        calories / maxCalories.toFloat()
    }

    val fillModifier = remember {
        Modifier.fillMaxWidth()
    }
    val firstPaddingModifier = remember(height) {
        Modifier.padding(height / 4)
    }
    val secondPaddingModifier = remember(height) {
        Modifier.padding(height / 8)
    }

    Box(contentAlignment = Alignment.Center, modifier = modifier.onGloballyPositioned {
        height = with(localDensity) { it.size.height.toDp() }
    }) {
        Box(Modifier.fillMaxSize()) {
            FadedCircularActivityBar(
                color = Color(0xFF19CB6D),
                strokeWidth = strokeWidth,
                fillModifier
            )
            CircularProgressIndicator(
                progress = stepsProgress,
                strokeWidth = strokeWidth,
                modifier = fillModifier,
                color = Color(0xFF19CB6D),
                strokeCap = StrokeCap.Round
            )
        }

        Box(
            modifier = fillModifier
                .then(firstPaddingModifier)
        ) {
            FadedCircularActivityBar(
                color = Color(0xFF48B8F3),
                strokeWidth = strokeWidth,
                fillModifier
            )
            CircularProgressIndicator(
                progress = activeTimeProgress,
                strokeWidth = strokeWidth,
                modifier = fillModifier,
                color = Color(0xFF48B8F3),
                strokeCap = StrokeCap.Round
            )
        }

        Box(
            modifier = fillModifier
                .then(secondPaddingModifier)
        ) {
            FadedCircularActivityBar(
                color = Color(0xFFFB40A7),
                strokeWidth = strokeWidth,
                fillModifier,
            )
            CircularProgressIndicator(
                progress = caloriesProgress,
                strokeWidth = strokeWidth,
                modifier = fillModifier,
                color = Color(0xFFFB40A7),
                strokeCap = StrokeCap.Round
            )
        }
    }
}

@Composable
private fun FadedCircularActivityBar(color: Color, strokeWidth: Dp, modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        progress = 1f,
        modifier = modifier,
        color = color.copy(0.5f),
        strokeWidth = strokeWidth,
        strokeCap = StrokeCap.Round
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFF)
private fun CircularActivityBarPreview() {
    CircularActivityBar(
        steps = 100,
        maxSteps = 150,
        activeTime = 150,
        maxActiveTime = 180,
        calories = 2000,
        maxCalories = 2500,
        modifier = Modifier.size(64.dp)
    )
}