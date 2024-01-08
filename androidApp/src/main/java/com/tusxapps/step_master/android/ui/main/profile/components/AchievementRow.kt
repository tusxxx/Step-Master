package com.tusxapps.step_master.android.ui.main.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.tusxapps.step_master.android.ui.components.BlockCard
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.H3
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryProgressIndicator
import com.tusxapps.step_master.android.ui.components.TextRegular
import kotlin.math.roundToInt

@Composable
fun AchievementRow(
    kmDealt: Int,
    kmNeeded: Int,
    prevIcon: String? = null,
    nextIcon: String? = null,
    achievementName: String = ""
) {
    BlockCard {
        Row {
            H3(achievementName)
            LargeSpacer()
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .size(Size(64, 64))
                    .decoderFactory(SvgDecoder.Factory())
                    .data(prevIcon)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        ExtraLargeSpacer()
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .size(Size(64, 64))
                        .decoderFactory(SvgDecoder.Factory())
                        .data(prevIcon)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                TextRegular(text = ((kmDealt.toFloat() / kmNeeded.toFloat()) * 100f).roundToInt().toString() + "%")
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .size(Size(64, 64))
                        .decoderFactory(SvgDecoder.Factory())
                        .data(nextIcon)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            ExtraLargeSpacer()
            PrimaryProgressIndicator(progress = (kmDealt.toFloat() / kmNeeded.toFloat()))
            ExtraLargeSpacer()
            Row(Modifier.align(Alignment.CenterHorizontally), verticalAlignment = Alignment.Bottom) {
                H3(text ="$kmDealt /" )
                TextRegular(text = " $kmNeeded")
            }
        }
    }
}