package com.tusxapps.step_master.android.ui.main.summary.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.R

@Composable
fun SummaryTopBar(
    onIconClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Сводка", style = MaterialTheme.typography.headlineLarge)
        Image(
            painter = painterResource(id = R.mipmap.pfp_round),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(39.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .clickable(onClick = onIconClick)
        )
    }
}

@Preview
@Composable
fun SummaryTopBarPreview() {
    SummaryTopBar({})
}