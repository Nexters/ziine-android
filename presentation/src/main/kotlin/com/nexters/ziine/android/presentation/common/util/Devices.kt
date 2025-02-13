package com.nexters.ziine.android.presentation.common.util

import android.content.Context
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager

fun getVibrator(context: Context): Vibrator {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    return vibrator
}
