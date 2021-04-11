package dev.marcocattaneo.cryptogasprice.ui.widgets

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState

@Composable
fun LottieImage(@RawRes resource: Int, modifier: Modifier = Modifier) {
    /*val animationSpec = remember { LottieAnimationSpec.RawRes(resource) }
    val animationState = rememberLottieAnimationState(autoPlay = true, repeatCount = Integer.MAX_VALUE)
    LottieAnimation(
        spec = animationSpec,
        animationState = animationState,
        modifier = modifier
    )*/
}