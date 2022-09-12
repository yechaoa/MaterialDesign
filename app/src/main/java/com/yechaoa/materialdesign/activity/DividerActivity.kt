package com.yechaoa.materialdesign.activity

import android.view.View
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityDividerBinding

class DividerActivity : ToolbarActivity<ActivityDividerBinding>() {

    private var isShow = true

    override fun getViewBinding(): ActivityDividerBinding {
        return ActivityDividerBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.divider)
    }

    override fun initView() {
        // for android:animateLayoutChanges="true"
        mBinding.btnVisible.setOnClickListener {
            if (isShow) {
                mBinding.tvAbout.visibility = View.GONE
            } else {
                mBinding.tvAbout.visibility = View.VISIBLE
            }
            isShow = !isShow
        }
    }

}