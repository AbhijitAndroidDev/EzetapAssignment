package com.app.assignmentproject.utils

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.app.assignmentproject.R

/**
 * Created by abhijit
 */
object CommonUtils {

    infix fun Context.appCustomToast(message: String) {
        val activity = this as Activity
        //inflate and use
        val layout = activity.layoutInflater.inflate(
            R.layout.custom_toast,
            activity.findViewById(R.id.custom_toast_cvMain)
        )
        val tvMessage: TextView = layout.findViewById(R.id.custom_toast_tvMessage)
        tvMessage.text = message

        Toast(activity).apply {
            setGravity(Gravity.BOTTOM, 0, 40)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}