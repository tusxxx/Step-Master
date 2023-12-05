package com.tusxapps.step_master.android.ui.main.summary.components.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor
import com.tusxapps.step_master.utils.countFatPercent

@Composable
fun BodyCompositionBlock(
    weightValue: Float?,
    muscleValue: Float?,
    fatValue: Float?,
    onSaveBodyCompositionClick: (
        weight: Float?,
        muscle: Float?,
        height: Float?,
        fat: Float?
    ) -> Unit
) {
    var isDialogOpen by remember { mutableStateOf(false) }

    if (isDialogOpen) {
        BodyCompositionEditDialog(
            onDismissRequest = { isDialogOpen = false },
            onSaveButtonClick = onSaveBodyCompositionClick
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
            .padding(extraLargeDp)
    ) {
        Column(modifier = Modifier.weight(0.7f)) {
            Text(
                text = "Состав тела",
                style = MaterialTheme.typography.titleMedium
            )
            ExtraLargeSpacer()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(extraLargeDp)
            ) {
                BodyInfoItem(
                    value = weightValue,
                    measurement = "кг",
                    icon = R.drawable.ic_weight
                )
                BodyInfoItem(
                    value = muscleValue,
                    measurement = "кг",
                    icon = R.drawable.ic_muscules
                )
                BodyInfoItem(
                    value = if (
                        weightValue != null &&
                        fatValue != null &&
                        weightValue != 0.toFloat()
                    ) {
                        countFatPercent(fatValue, weightValue)
                    } else {
                        null
                    },
                    measurement = "%",
                    icon = R.drawable.ic_fat
                )
            }
        }
        PrimaryButton(
            modifier = Modifier.weight(0.3f),
            text = "Ввести",
            shape = RoundedCornerShape(100.dp),
            onClick = { isDialogOpen = true }
        )
    }
}

@Composable
@Preview
private fun BodyCompositionBlockPreview() {
    BodyCompositionBlock(
        weightValue = 51f,
        muscleValue = 10f,
        fatValue = 10f,
        onSaveBodyCompositionClick = { _, _, _, _ -> }
    )
}