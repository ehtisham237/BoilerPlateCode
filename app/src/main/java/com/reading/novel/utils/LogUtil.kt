package com.reading.novel.utils

import android.util.Log
import com.reading.novel.BuildConfig

object LogUtil {
    @JvmStatic
    fun i(message: String?) {
        if (BuildConfig.DEV_MODE)
            Log.i("Meter==>", message!!)
    }

    fun v(message: String?) {
        if (BuildConfig.DEV_MODE)
            Log.v("Meter==>", message!!)
    }

    @JvmStatic
    fun e(message: String?) {
        if (BuildConfig.DEV_MODE)
            Log.e("Meter==>", message!!)
    }

    fun d(message: String?) {
        if (BuildConfig.DEV_MODE)
            Log.d("Meter==>", message!!)
    }

}


