package com.tusxapps.step_master.android.ui.main.summary.components.water

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.WaterIconButton
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp

private const val MILLIS_IN_GLASS = 250

@Composable
fun WaterBlock(
    glassCount: Int,
    addGlassButtonOnClick: () -> Unit,
    removeGlassButtonOnClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(mediumDp))
            .background(Color.White, shape = RoundedCornerShape(mediumDp))
            .padding(extraLargeDp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.7f)) {
            Text(text = "Вода", style = MaterialTheme.typography.titleMedium)
            ExtraLargeSpacer()
            ExtraLargeSpacer()
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = "$glassCount", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = " стак., ~${glassCount * MILLIS_IN_GLASS}мл",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            horizontalArrangement = Arrangement.spacedBy(mediumDp)
        ) {
            WaterIconButton(
                icon = R.drawable.ic_remove,
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                onClick = removeGlassButtonOnClick,
                enabled = glassCount > 0
            )
            WaterIconButton(
                icon = R.drawable.ic_add,
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                onClick = addGlassButtonOnClick,
            )
        }
    }
}

@Composable
@Preview
private fun WaterBlockPreview() {
    WaterBlock(glassCount = 0, addGlassButtonOnClick = {}, removeGlassButtonOnClick = {})
}