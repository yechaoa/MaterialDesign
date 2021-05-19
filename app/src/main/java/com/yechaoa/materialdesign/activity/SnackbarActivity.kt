package com.yechaoa.materialdesign.activity

import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivitySnackbarBinding

class SnackbarActivity : ToolbarActivity<ActivitySnackbarBinding>() {

    var TAG = "SnackbarActivity"

    override fun getViewBinding(): ActivitySnackbarBinding {
        return ActivitySnackbarBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.snack_bar)
    }

    override fun initView() {

        mBinding.button.setOnClickListener {
            Snackbar.make(mBinding.constraintLayout, "已加入行程", Snackbar.LENGTH_SHORT).show()
        }

        mBinding.button2.setOnClickListener {
            val mSnackbar = Snackbar.make(mBinding.constraintLayout, "已加入行程", Snackbar.LENGTH_INDEFINITE)
            //设置Action并监听事件
            mSnackbar.setAction("知道了") {
                //to do something
                Toast.makeText(this, "知道了", Toast.LENGTH_LONG).show()
            }.show()
        }

        mBinding.button3.setOnClickListener {
            val mSnackbar1 = Snackbar.make(mBinding.constraintLayout, "已加入行程", Snackbar.LENGTH_INDEFINITE)
            //设置Snackbar的背景颜色
            mSnackbar1.view.setBackgroundColor(ContextCompat.getColor(this@SnackbarActivity, R.color.colorPrimary))
            //设置Action文字的背景颜色
            mSnackbar1.setActionTextColor(Color.WHITE)
            //设置Action并监听事件
            mSnackbar1.setAction("知道了") {
                //to do something
                Toast.makeText(this, "知道了", Toast.LENGTH_LONG).show()
            }.show()
            //添加回调
            mSnackbar1.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    Log.i(TAG, "onDismissed--->> " + "onDismissed")
                }

                override fun onShown(sb: Snackbar) {
                    super.onShown(sb)
                    Log.i(TAG, "onShown--->> " + "onShown")
                }
            })
        }

    }

}