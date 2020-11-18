package com.yechaoa.materialdesign.activity

import com.yechaoa.materialdesign.R
import kotlinx.android.synthetic.main.activity_swipe_refresh_layout.*

class SwipeRefreshLayoutActivity : ToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_swipe_refresh_layout
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.swipe_refresh_layout)
    }

    override fun initView() {

        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.postDelayed({
                //关闭刷新
                swipeRefreshLayout.isRefreshing = false
                tv_refresh!!.text = "刷新完成"
            }, 2000)
        }

    }
}