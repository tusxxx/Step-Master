package com.tusxapps.step_master.android.ui.main.summary.achievements.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryButton
import com.tusxapps.step_master.android.ui.theme.largeDp
import com.tusxapps.step_master.domain.achievements.models.Achievable
import com.tusxapps.step_master.domain.achievements.models.Rank

@Composable
fun AchievablePinDialog(
    selectedAchievable: Achievable?,
    onDismiss: () -> Unit = {},
    onPinAchievableClick: (Achievable) -> Unit = {}
) {
    selectedAchievable?.let {
        Dialog(onDismissRequest = onDismiss) {
            Column(
                Modifier
                    .width(264.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Закрепить достижение",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                    )
                    IconButton(onClick = onDismiss, Modifier.align(Alignment.CenterEnd)) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                    }
                }
                AchievableItem(name = it.name, svgLink = it.link, isAchieved = it.isAchieved)
                LargeSpacer()
                PrimaryButton(
                    text = "Закрепить",
                    onClick = {
                        onPinAchievableClick(it)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(largeDp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AchievablePinDialogPreview() {
    val selectedAchievable = Rank("test", "test", true, "test", "", "")
    AchievablePinDialog(selectedAchievable = selectedAchievable)
}