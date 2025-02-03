package com.nexters.ziine.android.presentation.registerArtwork

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

class RegisterArtworkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZiineTheme {
            }
        }
    }



    companion object {
        fun getIntent(context: Context): Intent =
            Intent(context, RegisterArtworkActivity::class.java)
    }
}


