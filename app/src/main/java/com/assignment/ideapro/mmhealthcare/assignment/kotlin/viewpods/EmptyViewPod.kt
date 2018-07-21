package com.assignment.ideapro.mmhealthcare.assignment.kotlin.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.R
import kotlinx.android.synthetic.main.view_pod_empty.view.*

class EmptyViewPod : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setEmptyData(errorImage: Int) {
        iv_empty.setImageResource(R.drawable.ic_empty_placeholder)

    }
}