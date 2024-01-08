package com.tusxapps.step_master.android.ui.main.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tusxapps.step_master.android.ui.main.summary.achievements.composables.AchievableItem
import com.tusxapps.step_master.domain.achievements.models.Achievable

@Composable
fun AchievablesRow(achievables: List<Achievable>) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        achievables.forEach {
            AchievableItem(name = it.name, svgLink = it.link, isAchieved = false)
        }
    }
}
