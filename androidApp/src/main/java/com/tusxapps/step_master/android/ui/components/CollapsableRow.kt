package com.tusxapps.step_master.android.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.tusxapps.step_master.android.R

@Composable
fun CollapsableRow(
    text: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    var isExpanded by remember { mutableStateOf(true) }
    val icon = if (!isExpanded) painterResource(id = R.drawable.round_alt_arrow_down) else painterResource(id = R.drawable.round_alt_arrow_up)

    Column(modifier) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = text, Modifier.weight(1f), style = MaterialTheme.typography.titleMedium, fontSize = 20.sp)
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(painter = icon, contentDescription = null)
            }
        }
        Divider(Modifier.fillMaxWidth())
        MediumSpacer()
        AnimatedVisibility(visible = isExpanded) {
            content()
        }
    }
}