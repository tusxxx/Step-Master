package com.tusxapps.step_master.android.ui.main.summary.activity.components.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import kotlin.math.floor

private const val DOT_CHAR = "_ "

@Composable
fun TextWithDots(
    startText: String,
    endText: String
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
    ) {
        val composableWidth = maxWidth
        val textMeasurer = rememberTextMeasurer()

        val startTextStyle = MaterialTheme.typography.titleMedium
        val endTextStyle = MaterialTheme.typography.labelLarge
        val dotsTextStyle = MaterialTheme.typography.labelSmall

        val startWidth = LocalDensity.current.run {
            textMeasurer.measure(
                text = startText,
                style = startTextStyle
            ).size.width.toDp()
        }
        val endWidth = LocalDensity.current.run {
            textMeasurer.measure(
                text = endText,
                style = endTextStyle
            ).size.width.toDp()
        }
        val dotWidth = LocalDensity.current.run {
            textMeasurer.measure(
                text = DOT_CHAR,
                style = dotsTextStyle
            ).size.width.toDp()
        }

        val composableWidthInFloat = floor(composableWidth.value)
        val startAndEndComposableWidthWithPadding = (startWidth.value) + (endWidth.value)

        val calculatedNumberOfDots =
            ((composableWidthInFloat - startAndEndComposableWidthWithPadding) / dotWidth.value).toInt()
        val numberOfDots = if (calculatedNumberOfDots < 0) {
            0
        } else {
            calculatedNumberOfDots
        }
        val dotText = DOT_CHAR.repeat(numberOfDots)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = startText,
                style = startTextStyle,
                color = Color.Black
            )
            Text(
                text = dotText,
                style = dotsTextStyle,
                color = Color.LightGray,
                modifier = Modifier.weight(1F),
            )
            Text(
                text = endText,
                style = endTextStyle,
                color = Color.Black
            )
        }
    }
}