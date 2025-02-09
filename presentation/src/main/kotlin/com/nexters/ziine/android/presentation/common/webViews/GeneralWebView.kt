package com.nexters.ziine.android.presentation.common.webViews

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

/** Ziine기본 설정 웹뷰 생성 함수 */
internal fun getGeneralWebView(context: Context): WebView {
    val webView = WebView(context)
    webView.setGeneralSetting()
    return webView
}

private fun WebView.setGeneralSetting() {
    settings.javaScriptEnabled = true
    webViewClient = WebViewClient()
    webChromeClient = ZiineWebChromeClient()
}

@Composable
internal fun ComposeWrappedWebView(modifier: Modifier = Modifier, getWebView: (Context) -> WebView) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var webViewLifecycleObserver: LifecycleObserver? = null

    AndroidView(
        modifier = modifier,
        factory = { context ->
            getWebView(context).apply {
                webViewLifecycleObserver = getWebViewLifecycleObserver()
                webViewLifecycleObserver?.let { lifecycleOwner.lifecycle.addObserver(it) }
            }
        },
        onRelease = {
            webViewLifecycleObserver?.let { lifecycleOwner.lifecycle.removeObserver(it) }
        },
    )
}

/** 웹뷰 생명 주기 관련 함수 */
private fun WebView.getWebViewLifecycleObserver(): LifecycleObserver =
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> webViewOnResumeListener()
            Lifecycle.Event.ON_PAUSE -> webViewOnPauseListener()
            Lifecycle.Event.ON_DESTROY -> webViewOnDestroyListener()
            else -> {}
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
