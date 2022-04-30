package com.yechaoa.materialdesign.activity

import android.widget.Toast
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityCardViewBinding

class CardViewActivity : ToolbarActivity<ActivityCardViewBinding>() {

    override fun getViewBinding(): ActivityCardViewBinding {
        return ActivityCardViewBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.card_view)
    }

    override fun initView() {
        mBinding.cardViewElevated.setOnClickListener {
            Toast.makeText(this@CardViewActivity, "CardView", Toast.LENGTH_SHORT).show()
        }
    }

}