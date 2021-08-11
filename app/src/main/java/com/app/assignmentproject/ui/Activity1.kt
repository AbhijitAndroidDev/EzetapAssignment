package com.app.assignmentproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.app.assignmentproject.R
import com.app.assignmentproject.base.BaseActivity
import com.app.assignmentproject.utils.CommonUtils.appCustomToast
import com.app.networklibrary.model.UidataItem
import com.app.networklibrary.network.get.GetResponseApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by abhijit
 */
class Activity1 : BaseActivity(), GetResponseApi.ResponseListener {

    private lateinit var uidata: ArrayList<UidataItem>
    private lateinit var imgUrl: String

    override fun defineLayoutResource(): Int {
        return R.layout.layout_activity_1
    }

    override fun initializeBehavior() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        callAPI()
    }

    private fun callAPI() {
       // showLoader(false)
        if (isNetworkConnected()) {
            val api = GetResponseApi(this@Activity1, this)
            CoroutineScope(Dispatchers.Main).launch {
                api.getResponse()
                //hideLoader()
            }
        } else {
            this appCustomToast "No internet connection available"
        }

    }

    override fun fetchCustomUI(uidata: ArrayList<UidataItem>) {
        //hideLoader()
        this.uidata = uidata
        Timber.d("UIDATA>>> ${this.uidata.size}")

        val intent = Intent(this,Activity2::class.java)
        val bundle = Bundle()
        bundle.putSerializable("UI_DATA",this.uidata)
        intent.putExtras(bundle)
        startActivity(intent)

    }

    override fun fetchImage(logoUrl: String) {
       // hideLoader()
        imgUrl = logoUrl

    }

    override fun onResponseFailure(msg: String) {
        this appCustomToast msg
    }
}