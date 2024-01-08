package com.tusxapps.step_master.android.ui.main.summary.achievements.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp

@Composable
fun AchievableItem(
    name: String,
    svgLink: String,
    isAchieved: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        SubcomposeAsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .size(Size(128, 128))
                .decoderFactory(SvgDecoder.Factory())
                .data(svgLink)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = extraLargeDp),
            loading = {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(mediumDp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        )
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name)
            if (isAchieved) {
                MediumSpacer()
                Icon(
                    imageVector = Icons.Filled.Done,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }
        }

    }

}