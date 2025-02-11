package com.nexters.ziine.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import com.nexters.ziine.android.presentation.ui.theme.Gray900
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemUiController = rememberExSystemUiController()

            DisposableEffect(systemUiController) {
                systemUiController.setSystemBarsColor(
                    color = Gray900,
                    darkIcons = false,
                )

                onDispose {}
            }

            ZiineTheme {
                ZiineApp()
            }
        }
    }
}
