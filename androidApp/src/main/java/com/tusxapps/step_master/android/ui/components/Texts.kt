package com.tusxapps.step_master.android.ui.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun H1(text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current) {
    Text(
        text = text,
        style = style.merge(
            fontSize = 28.sp,
            fontWeight = FontWeight(600),
            letterSpacing = 0.03.sp,
        ),
        modifier = modifier
    )
}


@Composable
fun H2(text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current) {
    Text(
        text = text,
        style = style.merge(
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            letterSpacing = 0.02.sp,
        ),
        modifier = modifier
    )
}


@Composable
fun H3(text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current) {
    Text(
        text = text,
        style = style.merge(
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            letterSpacing = 0.02.sp,
        ),
        modifier = modifier
    )
}


@Composable
fun H4(text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current) {
    Text(
        text = text,
        style = style.merge(
            fontSize = 18.sp,
            fontWeight = FontWeight(500),
            letterSpacing = 0.02.sp,
        ),
        modifier = modifier
    )
}


@Composable
fun H5(text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current) {
    Text(
        text = text,
        style = style.merge(
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            letterSpacing = 0.02.sp,
        ),
        modifier = modifier
    )
}


@Composable
fun TextRegular(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = text,
        style = style.merge(
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            letterSpacing = 0.02.sp,
        ),
        modifier = modifier
    )
}


@Composable
fun TextSmall(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = text,
        style = style.merge(
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            letterSpacing = 0.02.sp,
        ),
        modifier = modifier
    )
}