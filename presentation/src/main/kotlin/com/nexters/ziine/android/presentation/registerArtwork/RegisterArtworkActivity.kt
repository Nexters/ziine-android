package com.nexters.ziine.android.presentation.registerArtwork

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.nexters.ziine.android.presentation.registerArtwork.navigation.RegisterArtWorkNavHost
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterArtworkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZiineTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) { innerPadding ->
                    RegisterArtWorkNavHost(
                        modifier = Modifier.padding(innerPadding),
                        activityFinishAction = ::finish
                    )
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent =
            Intent(context, RegisterArtworkActivity::class.java)
    }
}
