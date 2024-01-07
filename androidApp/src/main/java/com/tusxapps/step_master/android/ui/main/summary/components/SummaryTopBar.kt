package com.tusxapps.step_master.android.ui.main.summary.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.ui.components.AvatarImage

@Composable
fun SummaryTopBar(
    onIconClick: () -> Unit,
    avatar: ByteArray?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Сводка", style = MaterialTheme.typography.headlineLarge)
        AvatarImage(
            image = avatar, modifier = Modifier
                .size(40.dp)
                .clickable(onClick = onIconClick)
                .clip(CircleShape)
        )
    }
}

@Preview
@Composable
fun SummaryTopBarPreview() {
    SummaryTopBar({}, null)
}