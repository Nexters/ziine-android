package com.nexters.ziine.android.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
fun LoadingIndicator(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId = R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
    )

    AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(modifier = modifier.size(88.dp)) {
            LottieAnimation(
                composition = lottieComposition,
                progress = { progress },
                modifier = modifier.align(Alignment.Center)
            )
        }
    }
}

@ComponentPreview
@Composable
fun LoadingIndicatorPreview() {
    ZiineTheme {
        LoadingIndicator(
            isLoading = true,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
