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

        //mToolbar.inflateMenu(R.menu.menu_toolbar);

        setToolbar()
        initView()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun setToolbar()
    protected abstract fun initView()

    /**
     * 引入toolbar上的menu
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * toolbar上menu的事件处理
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