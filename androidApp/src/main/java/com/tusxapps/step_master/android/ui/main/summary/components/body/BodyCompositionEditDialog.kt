package com.tusxapps.step_master.android.ui.main.summary.components.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.main.summary.components.DialogTextField
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor

@Composable
fun BodyCompositionEditDialog(
    onDismissRequest: () -> Unit,
    onSaveButtonClick: (
        weight: Float?,
        muscles: Float?,
        height: Float?,
        fat: Float?
    ) -> Unit
) {
    var weightValue by remember { mutableStateOf("") }
    var heightValue by remember { mutableStateOf("") }
    var muscleValue by remember { mutableStateOf("") }
    var fatValue by remember { mutableStateOf("") }

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
            Text(text = "Состав тела", style = MaterialTheme.typography.titleMedium)
            MediumSpacer()
            Row {
                DialogTextField(
                    modifier = Modifier.weight(0.5f),
                    value = weightValue,
                    hint = "Вес, кг",
                    onValueChange = { weightValue = it }
                )
                LargeSpacer()
                DialogTextField(
                    modifier = Modifier.weight(0.5f),
                    value = heightValue,
                    hint = "Рост, см",
                    onValueChange = { heightValue = it }
                )
            }
            LargeSpacer()
            Row {
                DialogTextField(
                    modifier = Modifier.weight(0.5f),
                    value = muscleValue,
                    hint = "Мышцы, кг",
                    onValueChange = { muscleValue = it }
                )
                LargeSpacer()
                DialogTextField(
                    modifier = Modifier.weight(0.5f),
                    value = fatValue,
                    hint = "Жир, кг",
                    onValueChange = { fatValue = it }
                )
            }
            MediumSpacer()
            PrimaryButton(text = "Сохранить", onClick = {
                val weight = weightValue.toFloatOrNull()
                val muscle = muscleValue.toFloatOrNull()
                val height = heightValue.toFloatOrNull()
                val fat = fatValue.toFloatOrNull()
                if (
                    weight != null
                    && muscle != null
                    && height != null
                    && fat != null
                ) {
                    onSaveButtonClick(weight, muscle, height, fat)
                    onDismissRequest()
                }
            })
        }
    }
}

@Composable
@Preview
fun BodyCompositionEditDialogPreview() {
    MyApplicationTheme {
        BodyCompositionEditDialog(onDismissRequest = {}, onSaveButtonClick = { _, _, _, _ -> })
    }
}