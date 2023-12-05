package com.tusxapps.step_master.android.ui.main.summary.components.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.main.summary.components.DialogTextField
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor

@Composable
fun StepsGoalEditDialog(
    onDismissRequest: () -> Unit,
    onSaveGoalClick: (Int) -> Unit
) {
    val goal = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    spotColor = shadowColor,
                    shape = RoundedCornerShape(mediumDp)
                )
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(mediumDp))
                .background(Color.White, shape = RoundedCornerShape(mediumDp))
                .padding(extraLargeDp)
        ) {
            Text(text = "Цель", style = MaterialTheme.typography.titleMedium)
            MediumSpacer()
            DialogTextField(
                modifier = Modifier.fillMaxWidth(),
                value = goal.value,
                hint = "Количество шагов",
                onValueChange = { goal.value = it },
            )
            MediumSpacer()
            PrimaryButton(
                text = "Сохранить",
                onClick = {
                    goal.value.toIntOrNull()?.let {
                        onSaveGoalClick(it)
                        onDismissRequest()
                    }
                }
            )
        }
    }
}

@Composable
@Preview
fun StepsGoalEditDialogPreview() {
    MyApplicationTheme {
        StepsGoalEditDialog(
            onDismissRequest = {},
            onSaveGoalClick = {}
        )
    }
}