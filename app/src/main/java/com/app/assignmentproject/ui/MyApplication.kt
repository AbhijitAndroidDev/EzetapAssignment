package com.app.assignmentproject.ui

import android.app.Application
import com.app.networklibrary.BuildConfig
import com.app.networklibrary.timber.ReleaseTree
import timber.log.Timber

/**
 * Created by abhijit
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ':' + element.lineNumber
                }
            })
        } else {
            //Release Mode
            Timber.plant(ReleaseTree())
        }
    }
}