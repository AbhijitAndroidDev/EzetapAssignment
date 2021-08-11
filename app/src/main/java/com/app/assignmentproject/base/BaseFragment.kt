package com.app.assignmentproject.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by abhijit
 */
abstract class BaseFragment : Fragment() {

    private lateinit var mActivity: BaseActivity

    protected abstract fun defineLayoutResource(): Int

    protected abstract fun initializeComponent(view: View)

    protected abstract fun initializeBehavior()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(defineLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent(view)
        initializeBehavior()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mActivity = activity as BaseActivity
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun showLoader(clickable: Boolean = true) {
        mActivity.showLoader(clickable)
    }

    fun hideLoader() {
        mActivity.hideLoader()
    }

    fun getBaseActivity(): BaseActivity {
        return mActivity
    }

    fun hideKeyboard() {
        mActivity.hideKeyboard()
    }

    fun isSingleClick(): Boolean {
        return mActivity.isSingleClick()
    }

    fun isNetworkConnected(): Boolean {
        return mActivity.isNetworkConnected()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}