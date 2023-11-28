package com.tusxapps.step_master.android.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.largeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.smallDp

@Composable
fun RowScope.SmallSpacer() = Spacer(modifier = Modifier.width(smallDp))

@Composable
fun RowScope.MediumSpacer() = Spacer(modifier = Modifier.width(mediumDp))

@Composable
fun RowScope.LargeSpacer() = Spacer(modifier = Modifier.width(largeDp))

@Composable
fun RowScope.ExtraLargeSpacer() = Spacer(modifier = Modifier.width(extraLargeDp))

@Composable
fun ColumnScope.SmallSpacer() = Spacer(modifier = Modifier.height(smallDp))

@Composable
fun ColumnScope.MediumSpacer() = Spacer(modifier = Modifier.height(mediumDp))

@Composable
fun ColumnScope.LargeSpacer() = Spacer(modifier = Modifier.height(largeDp))

@Composable
fun ColumnScope.ExtraLargeSpacer() = Spacer(modifier = Modifier.height(extraLargeDp))