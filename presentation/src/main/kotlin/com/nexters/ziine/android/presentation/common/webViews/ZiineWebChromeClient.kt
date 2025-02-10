package com.nexters.ziine.android.presentation.common.webViews

import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts

class ZiineWebChromeClient(private val photoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) : WebChromeClient() {
    private var filePathCallback: ValueCallback<Array<Uri>>? = null

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?,
    ): Boolean {
        this.filePathCallback = filePathCallback
        photoPickerLauncher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
        return true
    }

    fun handleFileChooserResult(uri: Uri?) {
        val webSendValue = if (uri != null) arrayOf(uri) else null
        filePathCallback?.onReceiveValue(webSendValue)
        filePathCallback = null
    }
}


