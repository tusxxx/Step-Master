package com.tusxapps.step_master.android.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.R

@Composable
fun PrimaryTopBar(
    text: String,
    onBackClick: () -> Unit,
    @DrawableRes icon: Int? = null,
    onIconClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null
            )
        }
        Text(text = text, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.weight(1f))
        icon?.let {
            IconButton(onClick = onIconClick) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun PrimaryTopBarPreview() {
    PrimaryTopBar(
        text = "Активонсть",
        onBackClick = {},
        icon = R.drawable.ic_calendar
    )
}