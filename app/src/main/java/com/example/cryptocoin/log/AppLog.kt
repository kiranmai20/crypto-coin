package com.example.cryptocoin.log

import android.util.Log

object AppLog {
    var logEnable = true
    fun d(key: String?, value: String?) {
        if (logEnable) Log.d(key, value)
    }

    fun i(key: String?, value: String?) {
        if (logEnable) Log.i(key, value)
    }

    fun e(key: String?, value: String?) {
        if (logEnable) Log.e(key, value)
    }

    fun w(key: String?, value: String?) {
        if (logEnable) Log.w(key, value)
    }

    fun v(key: String?, value: String?) {
        if (logEnable) Log.v(key, value)
    }
}