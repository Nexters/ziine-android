package com.nexters.ziine.android.initializer

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.nexters.ziine.android.BuildConfig

class FirebaseCrashlyticsInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
