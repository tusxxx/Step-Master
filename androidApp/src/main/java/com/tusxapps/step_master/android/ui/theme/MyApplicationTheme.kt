package com.tusxapps.step_master.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = lightColorScheme(
        primary = Color(0xFF007AFF),
        secondary = Color.White,
        surface = Color(0xFFF2F1F7),
    )
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        headlineLarge = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight(500),
        ),
        titleMedium = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF272727),
            letterSpacing = 0.02.sp,
        ),
        labelSmall = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(300),
            color = Color(0x66272727),
        ),
        labelMedium = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF272727),
            letterSpacing = 0.01.sp,
        ),
        labelLarge = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            letterSpacing = 0.02.sp,
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(12.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
