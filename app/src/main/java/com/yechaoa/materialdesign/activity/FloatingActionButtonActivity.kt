package com.yechaoa.materialdesign.activity

import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityFloatingActionButtonBinding

class FloatingActionButtonActivity : ToolbarActivity<ActivityFloatingActionButtonBinding>() {

    override fun getViewBinding(): ActivityFloatingActionButtonBinding {
        return ActivityFloatingActionButtonBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.floating_action_button)
    }

    override fun initView() {
        mBinding.floatingButton.setOnClickListener {
            finish()
        }
    }

}