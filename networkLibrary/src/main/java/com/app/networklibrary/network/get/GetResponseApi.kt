package com.app.networklibrary.network.get

import android.app.Activity
import com.app.networklibrary.model.UidataItem
import com.app.networklibrary.network.BaseApi
import retrofit2.HttpException
import timber.log.Timber

/**
 * Created by abhijit
 */
class GetResponseApi(mActivity: Activity, private val mListener: ResponseListener): BaseApi(mActivity) {

    suspend fun getResponse(){
        val response = service.getResponse()

        if (response.isSuccessful){
            response.body()?.let {
                mListener.fetchCustomUI(it.uidata)
                mListener.fetchImage(it.logoUrl)
            }
        }else{
            try{
                val msg = errorCodeMessage(responseCode = response.code())
                mListener.onResponseFailure(msg = msg)
            }catch (e: HttpException) {
                Timber.d("API: Exception ${e.message}")
            } catch (e: Throwable) {
                Timber.d("API: Oops Something else went wrong")
                mListener.onResponseFailure("Something else went wrong")
            }

        }


    }

    interface ResponseListener{
        fun fetchCustomUI(uidata: ArrayList<UidataItem>)

        fun fetchImage(logoUrl: String)

        fun onResponseFailure(msg: String)
    }
}

