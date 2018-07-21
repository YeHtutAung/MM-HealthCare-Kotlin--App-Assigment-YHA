package com.assignment.ideapro.mmhealthcare.assignment.kotlin.data.models

import com.assignment.ideapro.mmhealthcare.assignment.kotlin.data.vos.HealthCareInfoVO
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.events.SuccessGetHealthCareEvent
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.network.MMHealthCareDataAgent
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MMHealthModel {

    companion object {

        private var objectReference: MMHealthModel? = null

        private const  val ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916"

        private var mDataAgent: MMHealthCareDataAgent? = null

        fun getObjectReference(): MMHealthModel? {

            if (objectReference == null) {
                objectReference = MMHealthModel()
            }
            return objectReference
        }

    }

    private var mDataRepo: HashMap<Int, HealthCareInfoVO> = HashMap()

    private constructor() {

        mDataAgent = RetrofitDataAgentImpl.getObjectReference()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetHealthInfo(event: SuccessGetHealthCareEvent) {
        setHealthInfoDataRepo(event.healthCareList)
    }

    fun setHealthInfoDataRepo(mHealthList: List<HealthCareInfoVO>) {

        for (healthCare: HealthCareInfoVO in mHealthList) {
            mDataRepo[healthCare.id] = healthCare
        }
    }

    fun getHealthInfoById(id: Int): HealthCareInfoVO? {

        return mDataRepo[id]
    }

    fun loadHealthCareInfo() {

        mDataAgent!!.loadHealthCareInfo(ACCESS_TOKEN)
    }


}