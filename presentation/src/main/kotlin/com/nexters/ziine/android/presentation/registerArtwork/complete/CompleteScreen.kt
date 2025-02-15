package com.nexters.ziine.android.presentation.registerArtwork.complete

import android.os.VibrationEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.common.util.ObserveAsEvents
import com.nexters.ziine.android.presentation.common.util.getVibrator
import com.nexters.ziine.android.presentation.component.LottieImage
import com.nexters.ziine.android.presentation.component.RegisterTopBar
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.registerArtwork.complete.viewmodel.CompleteUiAction
import com.nexters.ziine.android.presentation.registerArtwork.complete.viewmodel.CompleteUiEvent
import com.nexters.ziine.android.presentation.registerArtwork.complete.viewmodel.CompleteViewModel
import com.nexters.ziine.android.presentation.ui.theme.Heading4
import com.nexters.ziine.android.presentation.ui.theme.Subtitle2
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun CompleteRoute(
    modifier: Modifier = Modifier,
    activityFinishAction: () -> Unit,
    completeViewModel: CompleteViewModel = hiltViewModel(),
) {
    ObserveAsEvents(flow = completeViewModel.uiEvent) { event ->
        when (event) {
            is CompleteUiEvent.FinishActivity -> activityFinishAction()
        }
    }

    CompleteScreen(
        modifier = modifier,
        onAction = completeViewModel::onAction,
    )
}

@Composable
internal fun CompleteScreen(
    modifier: Modifier = Modifier,
    onAction: (CompleteUiAction) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        RegisterTopBar(isScrolled = false) { onAction(CompleteUiAction.OnBackButtonClicked) }
        CompleteUi(Modifier.weight(1f))
        StickyFooter { onAction(CompleteUiAction.OnMoveToHomeButtonClicked) }
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
        LottieImage(
            resId = R.raw.artwork_register_completed,
            iterations = 1,
            modifier = Modifier.size(280.dp)
        )
        Spacer(Modifier.weight(155f))
    }
}

@Composable
private fun StickyFooter(moveToHome: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        val vibrator = getVibrator(context)

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
                moveToHome()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(6.dp))
                .height(52.dp),
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

@DevicePreview
@Composable
private fun CompleteScreenPreview() {
    ZiineTheme {
        CompleteScreen {}
    }
}
