package com.nexters.ziine.android.presentation.registerArtwork.complete

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.component.RegisterTopBar
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.Heading4
import com.nexters.ziine.android.presentation.ui.theme.Subtitle2
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun CompleteRoute(modifier: Modifier = Modifier) {
    CompleteScreen(
        modifier = modifier,
    )
}

@Composable
internal fun CompleteScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        RegisterTopBar(isScrolled = false) { /** 액티비티 finish 로직 */ }
        CompleteUi(Modifier.weight(1f))
        StickyFooter() { }
    }
}

@Composable
private fun CompleteUi(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(40f))
        Text(
            text = stringResource(R.string.register_complete_title),
            color = MaterialTheme.colorScheme.onBackground,
            style = Heading4,
        )
        Spacer(modifier = Modifier.weight(80f))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 47.5.dp)
                .aspectRatio(1f / 1f),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
        )
        Spacer(Modifier.weight(155f))
    }
}

@Composable
private fun StickyFooter(hihi: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        val vibrator = getVibrator()

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
                hihi()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(6.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary,
            ),
        ) {
            Text(
                text = stringResource(R.string.register_complete_footer_button_text),
                style = Subtitle2,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun getVibrator(): Vibrator {
    val context = LocalContext.current
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    return vibrator
}

@DevicePreview
@Composable
private fun CompleteScreenPreview() {
    ZiineTheme {
        CompleteScreen()
    }
}
