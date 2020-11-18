package com.yechaoa.materialdesign.activity

import com.yechaoa.materialdesign.R
import kotlinx.android.synthetic.main.activity_floating_action_button.*

class FloatingActionButtonActivity : ToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_floating_action_button
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.floating_action_button)
    }

    override fun initView() {
        floating_button.setOnClickListener {
            finish()
        }
    }
}