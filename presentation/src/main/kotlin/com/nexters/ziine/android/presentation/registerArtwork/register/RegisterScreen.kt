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
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexters.ziine.android.presentation.common.util.ObserveAsEvents
import com.nexters.ziine.android.presentation.common.webViews.ComposeWrappedWebView
import com.nexters.ziine.android.presentation.common.webViews.ZiineWebViewBridge
import com.nexters.ziine.android.presentation.common.webViews.ZiineWebViewBridge.Companion.ZIINE_APP_BRIDGE_NAME
import com.nexters.ziine.android.presentation.component.RegisterTopBar
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.registerArtwork.register.viewmodel.RegisterUiAction
import com.nexters.ziine.android.presentation.registerArtwork.register.viewmodel.RegisterUiEvent
import com.nexters.ziine.android.presentation.registerArtwork.register.viewmodel.RegisterViewModel
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun RegisterRoute(
    modifier: Modifier = Modifier,
    backToPrevious: () -> Unit,
    navigateToComplete: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {
    ObserveAsEvents(flow = registerViewModel.uiEvent) { event ->
        when (event) {
            is RegisterUiEvent.NavigateToComplete -> navigateToComplete()
            is RegisterUiEvent.BackToPrevious -> backToPrevious()
        }
    }

    RegisterScreen(
        modifier = modifier,
        onAction = registerViewModel::onAction,
    )
}

@Composable
internal fun RegisterScreen(
    modifier: Modifier = Modifier,
    onAction: (RegisterUiAction) -> Unit
) {
    val (isScrolled, setIsScrolled) = remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        RegisterTopBar(isScrolled = isScrolled) { onAction(RegisterUiAction.OnBackButtonClicked) }
        ComposeWrappedWebViewWithTopBar(modifier = Modifier.weight(1f), setIsScrolled, onAction)
    }
}

private fun WebView.setRegisterSettingsToWebView(setIsScrolled: (Boolean) -> Unit): WebView {
    setOnScrollChangeListener { _, _, scrollY, _, _ ->
        setIsScrolled(scrollY > 0)
    }
    return this
}

private fun WebView.setBridgeToWebView(onAction: (RegisterUiAction) -> Unit): WebView {
    val registerBridge = ZiineWebViewBridge(
        navigateToRegisterComplete = { onAction(RegisterUiAction.OnMoveToCompleteButtonClicked) },
    )
    addJavascriptInterface(registerBridge, ZIINE_APP_BRIDGE_NAME)
    return this
}

@Composable
private fun ComposeWrappedWebViewWithTopBar(
    modifier: Modifier = Modifier,
    setIsScrolled: (Boolean) -> Unit,
    onAction: (RegisterUiAction) -> Unit
) {
    ComposeWrappedWebView(modifier = modifier.fillMaxSize()) { context ->
        WebView(context).setRegisterSettingsToWebView(setIsScrolled).setBridgeToWebView(onAction).apply {
            setTestPage()
        }
    }
}

private fun WebView.setTestPage() {
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
            onAction = {},
        )
    }
}
