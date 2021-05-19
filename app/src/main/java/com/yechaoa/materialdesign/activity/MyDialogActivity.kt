package com.yechaoa.materialdesign.activity

import com.yechaoa.materialdesign.databinding.ActivityMyDialogBinding

class MyDialogActivity : ToolbarActivity<ActivityMyDialogBinding>() {

    override fun getViewBinding(): ActivityMyDialogBinding {
        return ActivityMyDialogBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.title = "Dialog"
    }

    override fun initView() {
        mBinding.btnDialog.setOnClickListener { }
    }

}