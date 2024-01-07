package com.tusxapps.step_master.android.ui.main.profile.components.rating

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor

@Composable
fun RatingBlock(
    userRegionPlace: Int,
    regionUserCount: Int,
    userCountyPlace: Int,
    countryUserCount: Int,
    userClanPlace: Int,
    clanUserCount: Int
) {
    Column(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                spotColor = shadowColor,
                shape = RoundedCornerShape(mediumDp)
            )
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(mediumDp))
            .background(Color.White, shape = RoundedCornerShape(mediumDp))
            .padding(extraLargeDp)
    ) {
        Text(
            text = "Мой рейтинг",
            style = MaterialTheme.typography.titleMedium
        )
        LargeSpacer()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RatingStatsText(
                name = "Регион",
                currentValue = userRegionPlace,
                goalValue = regionUserCount
            )
            RatingStatsText(
                name = "Клан",
                currentValue = userClanPlace,
                goalValue = clanUserCount
            )
        }
        SmallSpacer()
        RatingStatsText(
            name = "Россия",
            currentValue = userCountyPlace,
            goalValue = countryUserCount
        )
    }
}

@Composable
@Preview
fun RatingBlockPreview() {
    MyApplicationTheme {
        RatingBlock(
            userRegionPlace = 1,
            regionUserCount = 10,
            userCountyPlace = 2,
            countryUserCount = 20,
            userClanPlace = 3,
            clanUserCount = 30
        )
    }
}