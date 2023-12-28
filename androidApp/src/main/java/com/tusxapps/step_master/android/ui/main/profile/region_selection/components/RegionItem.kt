package com.tusxapps.step_master.android.ui.main.profile.region_selection.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor

@Composable
fun RegionItem(
    value: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val iconColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
        label = "Icon Color"
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .shadow(
                elevation = 4.dp,
                spotColor = shadowColor,
                shape = RoundedCornerShape(mediumDp)
            )
            .background(Color.White, RoundedCornerShape(mediumDp))
            .padding(start = 16.dp, end = 10.dp, top = 14.dp, bottom = 14.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = value, modifier = Modifier.weight(1f), overflow = TextOverflow.Ellipsis)
        Icon(
            painter = painterResource(id = R.drawable.ic_check_mark),
            contentDescription = null,
            tint = iconColor
        )
    }
}

@Preview
@Composable
fun RegionItemPreview() {
    var isSelected by remember {
        mutableStateOf(false)
    }
    MyApplicationTheme {
        Surface {
            RegionItem(
                value = isSelected.toString(),
                isSelected = isSelected,
                onClick = { isSelected = !isSelected }
            )
        }
    }
}