package com.yechaoa.materialdesign.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        mToolbar.setNavigationOnClickListener {
            finish()
        }

        /**
         * 引入toolbar上的menu
         * androidx以后onCreateOptionsMenu中引入的方式失效
         * 因为是独立的使用，而不是作为ActionBar来使用，所以要用Toolbar的方式引用及监听
         */
        mToolbar.inflateMenu(R.menu.menu_toolbar);

        mToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_share -> {
                    Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnMenuItemClickListener true
        }

        setToolbar()
        initView()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun setToolbar()
    protected abstract fun initView()


    /**
     * 引入toolbar上的menu(作为ActionBar使用的引入方式)
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * toolbar上menu的事件处理(作为ActionBar使用的监听方式)
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                //Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
                finish()
                true
            }
            R.id.menu_share -> {
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show()
                super.onOptionsItemSelected(item)
            }
        }
    }
}