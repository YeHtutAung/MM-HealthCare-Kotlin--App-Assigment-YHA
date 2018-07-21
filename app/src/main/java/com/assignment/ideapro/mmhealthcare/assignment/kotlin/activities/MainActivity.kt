package com.assignment.ideapro.mmhealthcare.assignment.kotlin.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.R
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.adapters.HealthCareInfoAdapter
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.data.models.MMHealthModel
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.events.ApiErrorEvent
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.events.SuccessGetHealthCareEvent
import com.assignment.ideapro.mmhealthcare.assignment.kotlin.viewpods.EmptyViewPod
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private var adapter: HealthCareInfoAdapter? = null
    private var snack: Snackbar? = null
    private var viewPodEmpty: EmptyViewPod? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        viewPodEmpty = vp_empty as EmptyViewPod
        rv_health_list.layoutManager = LinearLayoutManager(applicationContext
                , LinearLayoutManager.VERTICAL
                , false)
        adapter = HealthCareInfoAdapter()
        rv_health_list.adapter = adapter
        swl.isRefreshing = true
        MMHealthModel.getObjectReference()!!.loadHealthCareInfo()

        swl.setOnRefreshListener {
            MMHealthModel.getObjectReference()!!.loadHealthCareInfo()
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)

    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetHealthList(successEvent: SuccessGetHealthCareEvent) {
        vp_empty.visibility = View.GONE
        swl.isRefreshing = false
        adapter!!.setHealthCareInfoList(successEvent.healthCareList)
        if (snack != null) {
            snack!!.dismiss()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onApiError(errorEvent: ApiErrorEvent) {

        swl.isRefreshing = false
        vp_empty.visibility = View.VISIBLE
        viewPodEmpty!!.setEmptyData(R.drawable.ic_empty_placeholder)
        snack = Snackbar.make(rv_health_list, errorEvent.errorMessage, Snackbar.LENGTH_INDEFINITE)
        snack!!.show()
    }
}
