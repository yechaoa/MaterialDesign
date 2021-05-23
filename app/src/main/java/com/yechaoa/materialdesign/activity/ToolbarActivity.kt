package com.yechaoa.materialdesign.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.yechaoa.materialdesign.R

abstract class ToolbarActivity<VB : ViewBinding> : AppCompatActivity() {

    protected open lateinit var mBinding: VB
    protected open lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewBinding()
        setContentView(mBinding.root)
        mToolbar = findViewById(R.id.toolbar)

        setListener()
        setToolbar()
        initView()
    }

    private fun setListener() {

        /**
         * toolbar上back的事件处理
         */
        mToolbar.setNavigationOnClickListener {
            finish()
        }

        /**
         * toolbar上menu的事件处理
         */
        mToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_author -> {
                    Snackbar
                        .make(mBinding.root, "作者：yechaoa", Snackbar.LENGTH_LONG)
                        .setAction("记住了") {
                            Toast.makeText(this, "祝你一夜暴富！", Toast.LENGTH_LONG).show()
                        }
                        .show()
                }
                R.id.menu_share -> {
                    Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_settings -> {
                    Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    protected abstract fun getViewBinding(): VB
    protected abstract fun setToolbar()
    protected abstract fun initView()

}