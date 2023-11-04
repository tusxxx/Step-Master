package com.tusxapps.step_master.android.ui.auth.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.theme.smallDp
import com.tusxapps.step_master.domain.Gender

@Composable
fun GenderSelector(gender: Gender, onGenderSelect: (Gender) -> Unit) {
    val isMale = remember(gender) { gender == Gender.MALE }
    val isFemale = remember(gender) { gender == Gender.FEMALE }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            text = "Пол",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = smallDp)
        )
        SmallSpacer()
        Row {
            AssistChip(
                onClick = { onGenderSelect(Gender.MALE) },
                label = {
                    Text(
                        text = "Мужчина",
                        color = if (isMale) MaterialTheme.colorScheme.onPrimary else Color.Black
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (isMale) MaterialTheme.colorScheme.primary else Color.White
                ),
                shape = RoundedCornerShape(size = 100.dp),
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = Color.Transparent
                )
            )
            LargeSpacer()
            AssistChip(
                onClick = { onGenderSelect(Gender.FEMALE) },
                label = {
                    Text(
                        text = "Женщина",
                        color = if (isFemale) MaterialTheme.colorScheme.onPrimary else Color.Black
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (isFemale) MaterialTheme.colorScheme.primary else Color.White
                ),
                shape = RoundedCornerShape(size = 100.dp),
                border = AssistChipDefaults.assistChipBorder(
                    borderColor = Color.Transparent
                )
            )
        }
    }
}