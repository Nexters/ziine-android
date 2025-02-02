package com.nexters.ziine.android.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin

@Composable
fun NetworkImage(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(id = R.drawable.placeholder),
    failureImage: Painter = painterResource(id = R.drawable.placeholder),
    contentScale: ContentScale = ContentScale.Crop,
) {
    if (LocalInspectionMode.current) {
        Image(
            painter = placeholder,
            contentDescription = "Example Image Icon",
            modifier = modifier,
        )
    } else {
        CoilImage(
            imageModel = { imageUrl },
            modifier = modifier,
            component = rememberImageComponent {
                +CrossfadePlugin(duration = 500)
                +PlaceholderPlugin.Loading(placeholder)
                +PlaceholderPlugin.Failure(placeholder)
            },
            imageOptions = ImageOptions(
                contentScale = contentScale,
                alignment = Alignment.Center,
                contentDescription = contentDescription,
            ),
            failure = {
                Image(
                    painter = failureImage,
                    contentDescription = "Failure Image",
                    modifier = modifier,
                )
            },
        )
    }
}

@ComponentPreview
@Composable
private fun NetworkImagePreview() {
    ZiineTheme {
        NetworkImage(
            imageUrl = "",
            contentDescription = "",
        )
    }
}
