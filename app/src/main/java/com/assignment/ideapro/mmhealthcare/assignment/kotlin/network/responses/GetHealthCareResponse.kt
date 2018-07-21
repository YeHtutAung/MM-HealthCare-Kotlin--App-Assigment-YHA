package com.assignment.ideapro.mmhealthcare.assignment.kotlin.network.responses

import com.assignment.ideapro.mmhealthcare.assignment.kotlin.data.vos.HealthCareInfoVO
import com.google.gson.annotations.SerializedName

class GetHealthCareResponse {
    @SerializedName("code")
    val code: Int = 0
    @SerializedName("message")
    val message: String = ""
    @SerializedName("healthcare-info")
    val healthCareInfoList: List<HealthCareInfoVO>? = null

    fun isResponseOK():Boolean{

        return code == 200 && healthCareInfoList !=null

    }
}