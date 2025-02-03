package com.nexters.ziine.android.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.ImageLoader
import coil.memory.MemoryCache
import coil.request.ImageRequest
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
    loadingImage: Painter = painterResource(id = R.drawable.placeholder),
    failureImage: Painter = painterResource(id = R.drawable.placeholder),
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current

    if (LocalInspectionMode.current) {
        Image(
            painter = loadingImage,
            contentDescription = "Example Image Icon",
            modifier = modifier,
        )
    } else {
        CoilImage(
            imageRequest = {
                ImageRequest.Builder(context)
                    .data(imageUrl)
                    .build()
            },
            imageLoader = {
                ImageLoader.Builder(context)
                    .memoryCache { MemoryCache.Builder(context).maxSizePercent(0.25).build() }
                    .build()
            },
            modifier = modifier,
            component = rememberImageComponent {
                +CrossfadePlugin(duration = 500)
                // 사진이 어떻게 나오는지 확인하기 위해 개발용으로 넣어둠, API 연동 후 제거 예정
                // +PlaceholderPlugin.Loading(loadingImage)
                +PlaceholderPlugin.Failure(failureImage)
            },
            imageOptions = ImageOptions(
                contentScale = contentScale,
                alignment = Alignment.TopCenter,
                contentDescription = contentDescription,
            ),
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
