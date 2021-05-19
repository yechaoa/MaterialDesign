package com.yechaoa.materialdesign.activity

import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.google.android.material.navigation.NavigationView
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityDrawerLayoutBinding

class DrawerLayoutActivity : ToolbarActivity<ActivityDrawerLayoutBinding>(),
    NavigationView.OnNavigationItemSelectedListener {

    override fun getViewBinding(): ActivityDrawerLayoutBinding {
        return ActivityDrawerLayoutBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.drawer_layout)
    }

    override fun initView() {

        //mDrawerLayout与mToolbar关联起来
        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this, mBinding.drawerLayout, mToolbar, R.string.open, R.string.close)
        //初始化状态
        actionBarDrawerToggle.syncState()
        //ActionBarDrawerToggle implements DrawerLayout.DrawerListener
        mBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle)

        //监听
        mBinding.drawerLayout.addDrawerListener(object : DrawerListener {
            override fun onDrawerSlide(view: View, v: Float) {
                Log.i("---", "滑动中")
            }

            override fun onDrawerOpened(view: View) {
                Log.i("---", "打开")
            }

            override fun onDrawerClosed(view: View) {
                Log.i("---", "关闭")
            }

            override fun onDrawerStateChanged(i: Int) {
                Log.i("---", "状态改变")
            }
        })

        //NavigationView 内容点击事件
        mBinding.navigationView.setNavigationItemSelectedListener(this)

        mBinding.btnOpenLeft.setOnClickListener {
            mBinding.drawerLayout.openDrawer(GravityCompat.START)
        }

        mBinding.btnOpenRight.setOnClickListener {
            mBinding.drawerLayout.openDrawer(GravityCompat.END)
        }

        //关闭执行DrawerLayout
        mBinding.btnCloseRight.setOnClickListener {
            mBinding.drawerLayout.closeDrawer(GravityCompat.END)
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val title = menuItem.title as String
        Toast.makeText(this, "点击了----- $title", Toast.LENGTH_SHORT).show()
        return false
    }

}