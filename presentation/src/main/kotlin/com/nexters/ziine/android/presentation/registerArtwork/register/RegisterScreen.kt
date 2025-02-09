package com.nexters.ziine.android.presentation.registerArtwork.register

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.nexters.ziine.android.presentation.common.webViews.ComposeWrappedWebView
import com.nexters.ziine.android.presentation.common.webViews.getGeneralWebView
import com.nexters.ziine.android.presentation.component.RegisterTopBar
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun RegisterRoute(modifier: Modifier = Modifier) {
    RegisterScreen(
        modifier = modifier,
    )
}

@Composable
internal fun RegisterScreen(modifier: Modifier = Modifier) {
    val (isScrolled, setIsScrolled) = remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        RegisterTopBar(isScrolled = isScrolled) { /** 기존화면으로 네비게이션 */ }
        ComposeWrappedWebViewWithTopBar(modifier = Modifier.weight(1f), setIsScrolled)
    }
}

private fun WebView.setRegisterSettingsToWebView(setIsScrolled: (Boolean) -> Unit): WebView {
    setOnScrollChangeListener { _, _, scrollY, _, _ ->
        setIsScrolled(scrollY > 0)
    }
    return this
}


@Composable
private fun ComposeWrappedWebViewWithTopBar(modifier: Modifier = Modifier, setIsScrolled: (Boolean) -> Unit) {
    ComposeWrappedWebView(modifier = modifier.fillMaxSize()) { context ->
        getGeneralWebView(context).setRegisterSettingsToWebView(setIsScrolled).apply {
            loadUrl("https://www.naver.com")
        }
    }
}

@DevicePreview
@Composable
private fun RegisterScreenPreview() {
    ZiineTheme {
        RegisterScreen(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background,
            ),
        )
    }
}
