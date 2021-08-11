package com.app.assignmentproject.base

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.app.assignmentproject.ui.LoaderFragment
import com.app.assignmentproject.utils.AppConstants
import com.app.assignmentproject.utils.NetworkUtils

/**
 * Created by abhijit
 */
abstract class BaseActivity : AppCompatActivity() {

    private var mLastClickTime: Long = 0

    /*
    * Only layout declared here
    * */
    protected abstract fun defineLayoutResource(): Int

    /*
    * Activity operations are started from here
    * */
    protected abstract fun initializeBehavior()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(defineLayoutResource())

        initializeBehavior()
    }

    override fun onStop() {
        super.onStop()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    /*
    * Show loader for any activity if required
    * */
    fun showLoader(clickable: Boolean = true) {
        val fragment = LoaderFragment()
        val b = Bundle()
        b.putBoolean("tap", clickable)
        fragment.arguments = b
        supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, "LOAD")
            .commitAllowingStateLoss()
    }

    /*
    * Hide loader for any activity if required
    * */
    fun hideLoader() {
        val fragment = supportFragmentManager?.findFragmentByTag("LOAD")
        if (fragment != null) {
            supportFragmentManager?.beginTransaction()
                ?.remove(fragment)
                ?.commitAllowingStateLoss()
        }
    }

    /*
    * Hide keyboard if required
    * */
    fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /*
    * It checks internet connectivity
    * */
    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    /*
    * Only used for clicks
    * SHOULD always be called inside OnClick operation
    * */
    fun isSingleClick(): Boolean {
        val currentClickTime = SystemClock.uptimeMillis()
        val elapsedTime = currentClickTime - mLastClickTime
        mLastClickTime = currentClickTime
        return elapsedTime > AppConstants.CLICK_MIN_INTERVAL
    }
}