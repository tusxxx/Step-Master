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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.main.summary.components.EditDialogTextField
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp

@Composable
fun BodyCompositionEditDialog(
    onDismissRequest: () -> Unit,
    onSaveButtonClick: (Float?, Float?, Float?, Float?) -> Unit
) {
    val weightValue = remember { mutableStateOf("") }
    val heightValue = remember { mutableStateOf("") }
    val muscleValue = remember { mutableStateOf("") }
    val fatValue = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(mediumDp))
                .background(Color.White, shape = RoundedCornerShape(mediumDp))
                .padding(extraLargeDp)
        ) {
            Text(text = "Состав тела", style = MaterialTheme.typography.titleMedium)
            MediumSpacer()
            Row {
                EditDialogTextField(
                    modifier = Modifier.weight(0.5f),
                    value = weightValue.value,
                    hint = "Вес, кг",
                    onValueChange = { weightValue.value = it }
                )
                LargeSpacer()
                EditDialogTextField(
                    modifier = Modifier.weight(0.5f),
                    value = heightValue.value,
                    hint = "Рост, см",
                    onValueChange = { heightValue.value = it }
                )
            }
            LargeSpacer()
            Row {
                EditDialogTextField(
                    modifier = Modifier.weight(0.5f),
                    value = muscleValue.value,
                    hint = "Мышцы, кг",
                    onValueChange = { muscleValue.value = it }
                )
                LargeSpacer()
                EditDialogTextField(
                    modifier = Modifier.weight(0.5f),
                    value = fatValue.value,
                    hint = "Жир, кг",
                    onValueChange = { fatValue.value = it }
                )
            }
            MediumSpacer()
            PrimaryButton(text = "Сохранить", onClick = {
                weightValue.value.toFloatOrNull()?.let { weight ->
                    heightValue.value.toFloatOrNull()?.let { height ->
                        muscleValue.value.toFloatOrNull()?.let { muscle ->
                            fatValue.value.toFloatOrNull()
                                ?.let { fat ->
                                    onSaveButtonClick(weight, muscle, height, fat)
                                    onDismissRequest()
                                }
                        }
                    }
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