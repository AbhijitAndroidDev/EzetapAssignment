package com.app.networklibrary.network

import com.app.networklibrary.model.FinalResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by abhijit
 */
interface ApiInterface {
    @GET("android_assignment.json")
    suspend fun getResponse(
    ): Response<FinalResponse>
}