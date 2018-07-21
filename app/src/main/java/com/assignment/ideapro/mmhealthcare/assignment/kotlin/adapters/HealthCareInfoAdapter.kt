package com.assignment.ideapro.mmhealthcare.assignment.kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.R
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.data.vos.HealthCareInfoVO
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.viewholders.BaseViewHolder
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.viewholders.HealthCareInfoViewHolder

class HealthCareInfoAdapter : BaseAdapter<HealthCareInfoViewHolder, HealthCareInfoVO>() {

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<HealthCareInfoVO> {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.view_holder_health_care, parent, false)
        return HealthCareInfoViewHolder(view)
    }


}