package com.nexters.ziine.android.presentation.common.webViews

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
internal fun getGeneralWebView(): WebView {
    val context = LocalContext.current
    val webView = WebView(context)
    val lifecycleOwner = LocalLifecycleOwner.current

    webView.setGeneralSetting()
    webView.SetWebViewLifecycleListener(lifecycleOwner)

    return webView
}

private fun WebView.setGeneralSetting() {
    settings.javaScriptEnabled = true
    webViewClient = WebViewClient()
    webChromeClient = ZiineWebChromeClient()
    // 여타 설정
}

@Composable
private fun WebView.SetWebViewLifecycleListener(lifecycleOwner: LifecycleOwner) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> webViewOnResumeListener()
                Lifecycle.Event.ON_PAUSE -> webViewOnPauseListener()
                Lifecycle.Event.ON_DESTROY -> webViewOnDestroyListener()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

private fun WebView.webViewOnResumeListener() {
    onResume()
}

private fun WebView.webViewOnPauseListener() {
    onPause()
}

private fun WebView.webViewOnDestroyListener() {
    stopLoading()
    removeAllViews()
    destroy()
}

@Composable
internal fun ComposeWrappedWebView(modifier: Modifier = Modifier, webView: WebView) {
    AndroidView(
        modifier = modifier,
        factory = { _ ->
            webView
        },
    )
}
