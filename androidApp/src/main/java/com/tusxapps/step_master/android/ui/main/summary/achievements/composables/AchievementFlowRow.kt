package com.tusxapps.step_master.android.ui.main.summary.achievements.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tusxapps.step_master.android.ui.theme.largeDp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AchievementFlowRow(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth(),
        maxItemsInEachRow = 3,
        verticalArrangement = Arrangement.spacedBy(largeDp)
    ) {
        content()
    }
}