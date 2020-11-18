package com.yechaoa.materialdesign.activity

import android.widget.Toast
import com.yechaoa.materialdesign.R
import kotlinx.android.synthetic.main.activity_card_view.*

class CardViewActivity : ToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_card_view
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.card_view)
    }

    override fun initView() {
        cardView.setOnClickListener { Toast.makeText(this@CardViewActivity, "CardView", Toast.LENGTH_SHORT).show() }
    }
}