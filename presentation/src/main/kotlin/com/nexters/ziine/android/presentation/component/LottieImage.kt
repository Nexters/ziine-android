package com.nexters.ziine.android.presentation.component

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieImage(
    @RawRes resId: Int,
    modifier: Modifier = Modifier,
    iterations: Int = LottieConstants.IterateForever,
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId = resId))
    val progress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = iterations,
    )

    LottieAnimation(
        composition = lottieComposition,
        progress = { progress },
        modifier = modifier,
    )
}
