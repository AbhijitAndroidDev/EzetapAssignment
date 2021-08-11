package com.app.assignmentproject.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.assignmentproject.R
import com.app.assignmentproject.base.BaseActivity
import com.app.assignmentproject.ui.adapter.MultipleViewTypeAdapter
import com.app.networklibrary.model.UidataItem
import kotlinx.android.synthetic.main.layout_activity_2.*


/**
 * Created by abhijit
 */
class Activity2 : BaseActivity() {

    private lateinit var uidata: ArrayList<UidataItem>
    private lateinit var imgUrl: String
    private lateinit var multipleViewTypeAdapter: MultipleViewTypeAdapter


    override fun defineLayoutResource(): Int {
        return R.layout.layout_activity_2
    }

    override fun initializeBehavior() {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uidata = intent.extras?.getSerializable("UI_DATA") as ArrayList<UidataItem>

        setUIAdapter()
    }

    @SuppressLint("WrongConstant")
    private fun setUIAdapter() {

        multipleViewTypeAdapter = MultipleViewTypeAdapter(this, uidata)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layout_activity_2_rvLayout.layoutManager = linearLayoutManager
        layout_activity_2_rvLayout.itemAnimator = DefaultItemAnimator()
        layout_activity_2_rvLayout.adapter = multipleViewTypeAdapter
    }
}