package com.reading.novel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.reading.novel.utils.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp
import java.util.Date
import javax.inject.Inject


@HiltAndroidApp
class NovelApp : Application() {


    @Inject
    lateinit var preferanceManager: PreferenceUtil
    internal var isApplicationUnlocked = false

    init {
        instance = this
    }

    companion object {

        const val LOCK_WIPE_POLICY_TAG = "LockWipePolicy"
        const val LOCK_CALL_BACK_TAG = "LockCallback"
        const val WIPE_CALL_BACK_TAG = "WipeCallback"
        lateinit var instance: NovelApp

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        //to disable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}