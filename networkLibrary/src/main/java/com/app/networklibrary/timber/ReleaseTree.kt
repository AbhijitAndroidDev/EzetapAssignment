package com.app.networklibrary.timber

import android.util.Log
import timber.log.Timber
import kotlin.math.min

/**
 * Created by abhijit
 */
class ReleaseTree : Timber.Tree() {

    companion object {
        private const val MAX_LOG_LENGTH = 4000
    }


    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return !(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO)

        // only warn error and wtf
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (isLoggable(tag, priority)) {
            if (message.length < MAX_LOG_LENGTH) {
                if (priority == Log.ASSERT) {
                    Timber.wtf(message)
                } else {
                    Log.println(priority, tag, message)
                }
            }

            // Split by line, then ensure each line can fit logs maximum length
            var i = 0
            val length = message.length
            while (i < length) {
                var newLine = message.indexOf('\n', i)
                newLine = if (newLine != -1) newLine else length
                do {
                    val end = min(newLine, i + MAX_LOG_LENGTH)
                    val part = message.substring(i, end)
                    if (priority == Log.ASSERT) {
                        Timber.wtf(part)
                    } else {
                        Log.println(priority, tag, message)
                    }
                    i = end
                } while (i < newLine)
                i++
            }
        }
    }
}