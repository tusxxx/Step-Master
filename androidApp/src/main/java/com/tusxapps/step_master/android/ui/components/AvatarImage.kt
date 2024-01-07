package com.tusxapps.step_master.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.tusxapps.step_master.android.R

@Composable
fun AvatarImage(
    image: ByteArray?,
    modifier: Modifier = Modifier,
    isUploadingImage: Boolean = false,
    isEditMode: Boolean = false
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    ) {
        val state = painter.state
        SubcomposeAsyncImageContent()
        when {
            state is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }

            isUploadingImage -> {
                CircularProgressIndicator()
            }

            isEditMode -> {
                Box(
                    modifier = Modifier.background(Color.LightGray.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(id = R.drawable.camera),
                        contentDescription = null,
                        modifier = Modifier.padding(16.dp),
                        colorFilter = ColorFilter.tint(Color.Gray)
                    )
                }
            }
            image == null -> {
                Box(
                    modifier = Modifier.background(Color.LightGray.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.padding(16.dp),
                        colorFilter = ColorFilter.tint(Color.Gray)
                    )
                }
            }
        }
    }
}