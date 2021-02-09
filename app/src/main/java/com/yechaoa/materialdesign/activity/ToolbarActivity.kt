package com.yechaoa.materialdesign.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.yechaoa.materialdesign.R

abstract class ToolbarActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
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

    protected abstract fun getLayoutId(): Int
    protected abstract fun setToolbar()
    protected abstract fun initView()

}