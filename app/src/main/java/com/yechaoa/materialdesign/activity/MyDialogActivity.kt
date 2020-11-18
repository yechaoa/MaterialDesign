package com.yechaoa.materialdesign.activity

import com.yechaoa.materialdesign.R
import kotlinx.android.synthetic.main.activity_my_dialog.*

class MyDialogActivity : ToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_my_dialog
    }

    override fun setToolbar() {
        mToolbar.title = "Dialog"
    }

    override fun initView() {
        btn_dialog.setOnClickListener { }
    }
}