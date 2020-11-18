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
import kotlinx.android.synthetic.main.activity_drawer_layout.*

class DrawerLayoutActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_drawer_layout
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.drawer_layout)
    }

    override fun initView() {

        //mDrawerLayout与mToolbar关联起来
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, mToolbar, R.string.open, R.string.close)
        //初始化状态
        actionBarDrawerToggle.syncState()
        //ActionBarDrawerToggle implements DrawerLayout.DrawerListener
        drawer_layout.addDrawerListener(actionBarDrawerToggle)

        //监听
        drawer_layout.addDrawerListener(object : DrawerListener {
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
        navigation_view.setNavigationItemSelectedListener(this)

        btn_open_left.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        btn_open_right.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.END)
        }

        //关闭执行DrawerLayout
        btn_close_right.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.END)
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val title = menuItem.title as String
        Toast.makeText(this, "点击了----- $title", Toast.LENGTH_SHORT).show()
        return false
    }
}