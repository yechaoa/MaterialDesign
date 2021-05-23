package com.yechaoa.materialdesign.activity

import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityFloatingActionButtonBinding
import com.yechaoa.materialdesign.databinding.ActivityMaterialButtonBinding

class MaterialButtonActivity : ToolbarActivity<ActivityMaterialButtonBinding>() {

    override fun getViewBinding(): ActivityMaterialButtonBinding {
        return ActivityMaterialButtonBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.material_button)
    }

    override fun initView() {

    }

}