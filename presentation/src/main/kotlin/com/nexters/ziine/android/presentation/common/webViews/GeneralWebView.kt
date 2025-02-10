package com.nexters.ziine.android.presentation.common.webViews

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
internal fun ComposeWrappedWebView(modifier: Modifier = Modifier, getWebView: (Context) -> WebView) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var webViewLifecycleObserver: LifecycleObserver? = null
    var webView: WebView? = null
    val photoPickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        val ziineWebChromeClient = webView?.webChromeClient as? ZiineWebChromeClient
        ziineWebChromeClient?.handleFileChooserResult(uri)
    }
    AndroidView(
        modifier = modifier,
        factory = { context ->
            getWebView(context).apply {
                /** 웹뷰 일반 설정 초기화 */
                setGeneralSetting(photoPickerLauncher)
                /** 웹뷰 생명주기 관련 초기화 */
                webViewLifecycleObserver = getWebViewLifecycleObserver()
                webViewLifecycleObserver?.let { lifecycleOwner.lifecycle.addObserver(it) }
                /** PhotoPicker를 사용하기 위한 함수내 웹뷰 인스턴스 참조 유지 */
                webView = this
            }
        },
        onRelease = {
            webViewLifecycleObserver?.let { lifecycleOwner.lifecycle.removeObserver(it) }
        },
    )
}

@SuppressLint("SetJavaScriptEnabled")
private fun WebView.setGeneralSetting(photoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) {
    settings.run {
        javaScriptEnabled = true
        javaScriptCanOpenWindowsAutomatically = true
        loadsImagesAutomatically = true
        useWideViewPort = true
        loadWithOverviewMode = true
        domStorageEnabled = true
        allowFileAccess = true
        mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        cacheMode = WebSettings.LOAD_NO_CACHE
    }
    webViewClient = WebViewClient()
    webChromeClient = ZiineWebChromeClient(photoPickerLauncher)
    /** 기본 테마가 어두운 색이므로 웹뷰 깜방임 방지용 웹뷰 배경 투명화 */
    setBackgroundColor(0)
    /** 웹뷰 깜빡임 제거 설정(하드웨어 가속시 cpu/gpu 사용 관련 설정) */
    setLayerType(WebView.LAYER_TYPE_SOFTWARE, null)

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
