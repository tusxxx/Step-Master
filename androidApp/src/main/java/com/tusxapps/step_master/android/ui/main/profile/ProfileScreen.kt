package com.tusxapps.step_master.android.ui.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryTopBar
import com.tusxapps.step_master.android.ui.main.profile.components.RatingBlock
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp

object ProfileScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        ProfileScreenBody()
    }
}

@Composable
private fun ProfileScreenBody() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = extraLargeDp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryTopBar(
            text = "Профиль",
            onBackClick = { /*TODO*/ },
            icon = R.drawable.ic_settings,
            onIconClick = {}
        )
        ExtraLargeSpacer()
        Image(
            painter = painterResource(id = R.mipmap.pfp_foreground),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        LargeSpacer()
        Text(text = "John Johnos", style = MaterialTheme.typography.bodyLarge)
        Text(
            text = "Джон Джонос",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.LightGray
        )
        ExtraLargeSpacer()
        RatingBlock()
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    MyApplicationTheme {
        Surface {
            ProfileScreenBody()
        }
    }
}