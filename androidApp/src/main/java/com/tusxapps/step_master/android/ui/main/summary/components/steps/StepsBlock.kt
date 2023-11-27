package com.tusxapps.step_master.android.ui.main.summary.components.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.theme.editTextFieldColor
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor

@Composable
fun StepsBlock(
    stepsCount: Int,
    goalStepsCount: Int,
    onSaveGoalClick: (Int) -> Unit
) {
    val isDialogOpen = remember { mutableStateOf(false) }

    if (isDialogOpen.value) {
        StepsGoalEditDialog(
            onDismissRequest = { isDialogOpen.value = false },
            onSaveGoalClick = onSaveGoalClick
        )
    }
    Row(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                spotColor = shadowColor,
                shape = RoundedCornerShape(mediumDp)
            )
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(mediumDp))
            .background(Color.White, shape = RoundedCornerShape(mediumDp))
            .padding(extraLargeDp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(modifier = Modifier.weight(0.5f)) {
            Text(text = "Шаги", style = MaterialTheme.typography.titleMedium)
            ExtraLargeSpacer()
            ExtraLargeSpacer()
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = "$stepsCount", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "/$goalStepsCount",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray
                )
            }
        }
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.End
        ) {
            PrimaryButton(
                text = "Изменить цель",
                shape = RoundedCornerShape(100.dp),
                onClick = { isDialogOpen.value = true }
            )
            MediumSpacer()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${(stepsCount.toFloat() / goalStepsCount.toFloat() * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray
                )
                SmallSpacer()
                LinearProgressIndicator(
                    progress = stepsCount.toFloat() / goalStepsCount.toFloat(),
                    trackColor = editTextFieldColor,
                    strokeCap = StrokeCap.Round
                )
            }
        }
    }
}

@Composable
@Preview
private fun StepsBlockPreview() {
    StepsBlock(
        stepsCount = 10000,
        goalStepsCount = 12000,
        onSaveGoalClick = {}
    )
}