package com.assignment.ideapro.mmhealthcare.assignment.kotlin.network

import com.assignment.ideapro.mmhealthcare.assignment.kotlin.events.ApiErrorEvent
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.events.SuccessGetHealthCareEvent
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.network.responses.GetHealthCareResponse
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.utils.AppConstants
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitDataAgentImpl : MMHealthCareDataAgent {

    companion object {
        private var objectReference: RetrofitDataAgentImpl? = null

        public fun getObjectReference(): RetrofitDataAgentImpl? {
            if (objectReference == null) {
                objectReference = RetrofitDataAgentImpl()
            }
            return objectReference
        }
    }

    private var mTheApi: MMHealthCareApi? = null

    private constructor() {

        val mClient: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

        var retroit: Retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(mClient)
                .build()

        mTheApi = retroit.create(MMHealthCareApi::class.java)
    }

    override fun loadHealthCareInfo(accessToken: String) {

        var getHealthInfoApiCall: Call<GetHealthCareResponse> = mTheApi!!.loadHealthCareInfo(accessToken)
        getHealthInfoApiCall.enqueue(object : Callback<GetHealthCareResponse> {

            override fun onResponse(call: Call<GetHealthCareResponse>?, response: Response<GetHealthCareResponse>?) {
                val healthCareResponse: GetHealthCareResponse? = response!!.body()
                if (healthCareResponse != null && healthCareResponse.isResponseOK()) {

                    val successEvent = SuccessGetHealthCareEvent(healthCareResponse.healthCareInfoList!!)
                    EventBus.getDefault().post(successEvent)

                } else {
                    if (healthCareResponse == null) {

                        val errorEvent = ApiErrorEvent("Empty in response!")
                        EventBus.getDefault().post(errorEvent)

                    } else {

                        val errorEvent = ApiErrorEvent(healthCareResponse.message)
                        EventBus.getDefault().post(errorEvent)
                    }
                }
            }

            override fun onFailure(call: Call<GetHealthCareResponse>?, t: Throwable?) {

                val errorEvent = ApiErrorEvent(t!!.message!!)
                EventBus.getDefault().post(errorEvent)
            }
        })
    }
}