package com.assignment.ideapro.mmhealthcare.assignment.kotlin.viewholders

import android.view.View
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.R
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.data.vos.HealthCareInfoVO
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.utils.GlideApp
import kotlinx.android.synthetic.main.view_holder_health_care.view.*

class HealthCareInfoViewHolder(itemView: View) :
        BaseViewHolder<HealthCareInfoVO>(itemView) {

    override fun bindData(data: HealthCareInfoVO) {

        mData = data
        itemView.tv_health_info_heading.text = mData!!.title
        itemView.tv_type.text = if (mData!!.infoType != "") {
            mData!!.infoType
        } else {
            "Other"
        }
        itemView.tv_publisher.text = mData!!.author!!.authorName

        GlideApp.with(itemView.iv_health_care_hero)
                .load(mData!!.image)
                .placeholder(R.drawable.ic_empty_placeholder)
                .centerCrop()
                .into(itemView.iv_health_care_hero)

        GlideApp.with(itemView.iv_share)
                .load(mData!!.author!!.authorPhoto)
                .placeholder(R.drawable.ic_empty_placeholder)
                .centerCrop()
                .into(itemView.iv_share)
    }

    override fun onClick(v: View?) {

    }


}