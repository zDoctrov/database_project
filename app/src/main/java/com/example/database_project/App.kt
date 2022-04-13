package com.example.database_project

import android.app.Application
import android.content.Context


//Use this
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    companion object {
        private var mContext: Context? = null
        val context: Context?
            get() = mContext
    }
}