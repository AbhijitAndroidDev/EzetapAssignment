package com.app.networklibrary.network

import android.app.Activity
import com.app.networklibrary.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by abhijit
 */
open class BaseApi(var activity: Activity) {
    private var retrofit: Retrofit
    var service: ApiInterface


    init {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(AppConstants.WS_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(AppConstants.WS_READ_TIMEOUT, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(
                "http://demo.ezetap.com/mobileapps/"
            )
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<ApiInterface>(
            ApiInterface::class.java
        )
    }

    fun errorCodeMessage(responseCode: Int): String {
        return when (responseCode) {
            401 -> NetworkConstants.API_AUTHENTICATION_FAILED
            400 -> NetworkConstants.API_BAD_REQUEST
            404 -> NetworkConstants.API_TRY_AGAIN
            500 -> NetworkConstants.API_SERVER_ERROR
            else -> NetworkConstants.API_TRY_AGAIN
        }
    }

}