package com.assignment.ideapro.mmhealthcare.assignment.kotlin.network

import com.assignment.ideapro.mmhealthcare.assignment.kotlin.network.responses.GetHealthCareResponse
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.utils.AppConstants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *
 * Created by Phyo Thiha on 7/10/18.
 */
interface MMHealthCareApi {

    @FormUrlEncoded
    @POST(AppConstants.API_GET_HEALTH_CARE_INFO)
    fun loadHealthCareInfo(
            @Field(AppConstants.PARAM_ACCESS_TOKEN) accessToken: String
    ): Call<GetHealthCareResponse>
}