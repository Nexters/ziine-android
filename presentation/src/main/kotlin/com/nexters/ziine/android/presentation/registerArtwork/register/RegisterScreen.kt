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
        WebView(context).setRegisterSettingsToWebView(setIsScrolled).apply {
            setTestPage()
        }
    }
}

private fun WebView.setTestPage(){
    val htmlData = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
            <style>
                body {
                    color: white;
                    background-color: black;
                    font-size: 18px;
                    padding: 16px;
                }
                input {
                    font-size: 18px;
                    width: 100%;
                    padding: 10px;
                }
            </style>
        </head>
        <body>
            <p>Click on the "Choose File" button to upload a file:</p>
            <form action="/action_page.php">
                <input type="file" id="myFile" name="filename">
            </form>
        </body>
        </html>
    """.trimIndent()


    loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null)
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
