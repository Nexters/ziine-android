package com.nexters.ziine.android.presentation.component

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Paragraph4
import com.nexters.ziine.android.presentation.ui.theme.Subtitle2
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZiineErrorDialog(
    isErrorDialogVisible: Boolean,
    onDismissRequest: () -> Unit,
    @StringRes titleResId: Int,
    @StringRes descriptionResId: Int,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
) {
    AnimatedVisibility(
        visible = isErrorDialogVisible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        BasicAlertDialog(
            onDismissRequest = onDismissRequest,
            properties = properties,
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = stringResource(id = titleResId),
                    style = Subtitle2,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = descriptionResId),
                    style = Subtitle2,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.height(64.dp))
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.error_image),
                    contentDescription = "Error Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp),
                )
                Spacer(modifier = Modifier.height(40.dp))
                OutlinedButton(
                    onClick = onRetryClick,
                    modifier = Modifier
                        .height(45.dp)
                        .padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
                ) {
                    Text(
                        text = stringResource(id = R.string.retry),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = Paragraph4,
                    )
                }
            }
        }
    }
}

@ComponentPreview
@Composable
private fun ZiineErrorDialogPreview() {
    ZiineTheme {
        ZiineErrorDialog(
            isErrorDialogVisible = true,
            onDismissRequest = { },
            titleResId = R.string.error_title,
            descriptionResId = R.string.error_description,
            onRetryClick = { },
        )
    }
}
