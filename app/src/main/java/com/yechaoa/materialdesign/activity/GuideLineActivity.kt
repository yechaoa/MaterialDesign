package com.yechaoa.materialdesign.activity

import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityGuideLineBinding

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/8/18.
 * Describe :
 */
class GuideLineActivity : ToolbarActivity<ActivityGuideLineBinding>() {

    override fun getViewBinding(): ActivityGuideLineBinding {
        return ActivityGuideLineBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.guide_line)
    }

    override fun initView() {

    }

}