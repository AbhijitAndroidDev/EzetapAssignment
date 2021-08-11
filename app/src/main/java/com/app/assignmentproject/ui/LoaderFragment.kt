package com.app.assignmentproject.ui

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.app.assignmentproject.R
import com.app.assignmentproject.base.BaseFragment

/**
 * Created by abhijit
 */
class LoaderFragment : BaseFragment() {

    private lateinit var flMain: FrameLayout

    private var clickable = true

    companion object {
        private val TAG = LoaderFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            clickable = bundle.getBoolean("tap", true)
        }
    }

    override fun defineLayoutResource(): Int {
        return R.layout.fragment_loader
    }

    override fun initializeComponent(view: View) {
        flMain = view.findViewById(R.id.fragment_loader_flMain)
    }

    override fun initializeBehavior() {
        flMain.setOnClickListener {
            if (clickable) {
                hideLoader()
            }
        }
    }

}
