package com.nexters.ziine.android.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nexters.ziine.android.presentation.MainActivity
import com.nexters.ziine.android.presentation.common.extensions.startActivityWithAnimation
import com.nexters.ziine.android.presentation.ui.theme.Primary500
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val activity = LocalActivity.current
            val systemUiController = rememberExSystemUiController()

            DisposableEffect(systemUiController) {
                systemUiController.setSystemBarsColor(
                    color = Primary500,
                    darkIcons = false,
                )

                onDispose {}
            }

            LaunchedEffect(key1 = Unit) {
                delay(1000)
                activity?.startActivityWithAnimation<MainActivity>(
                    withFinish = true,
                    intentBuilder = { this },
                )
            }

            ZiineTheme {
                SplashScreen()
            }
        }
    }
}
