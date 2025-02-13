package com.nexters.ziine.android.presentation.common.webViews

import android.webkit.JavascriptInterface

class ZiineWebViewBridge(
    private val navigateToRegisterComplete: (() -> Unit)? = null,
) {
    companion object {
        const val ZIINE_APP_BRIDGE_NAME = "ziineApp"
    }

    @JavascriptInterface
    fun artworkRegisterSuccess() {
        navigateToRegisterComplete?.invoke()
    }
}
