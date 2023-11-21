package com.tusxapps.step_master.android.ui.main.activity.components.weekCalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp

@Composable
fun WeekActivityCalendar(
    dates: List<String>,
    steps: List<Int>,
    goalSteps: Int,
    activeTime: List<Int>,
    goalActiveTime: Int,
    calories: List<Int>,
    goalCalories: Int
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(mediumDp))
            .background(Color.White, shape = RoundedCornerShape(mediumDp))
            .padding(extraLargeDp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(dates) {

        }
    }
}

@Preview
@Composable
fun WeekActivityCalendarPreview() {
    MyApplicationTheme {
        Surface {
//            WeekActivityCalendar(
//            )
        }
    }
}