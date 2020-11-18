package com.yechaoa.materialdesign.activity

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yechaoa.materialdesign.R
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : ToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_bottom_navigation
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.bottom_navigation)
    }

    override fun initView() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation2.selectedItemId = R.id.navigation_dashboard
        navigation3.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setBadge(2, 5)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_test -> {
                message.setText(R.string.title_test)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    /**
     * 给BottomNavigationView 设置Badge 小红点
     *
     * BottomNavigationMenuView中的每一个Tab是一个FrameLayout，所以可以在上面随意添加View、这样就可以实现角标了
     */
    private fun setBadge(index: Int, count: Int) {
        //获取底部菜单view
        val menuView = navigation3.getChildAt(0) as BottomNavigationMenuView
        //获取第2个itemView
        val itemView = menuView.getChildAt(index) as BottomNavigationItemView
        //引入badgeView
        val badgeView = LayoutInflater.from(this).inflate(R.layout.layout_badge_view, menuView, false)
        //把badgeView添加到itemView中
        itemView.addView(badgeView)
        //获取子view并设置显示数目
        val countView = badgeView.findViewById<TextView>(R.id.tv_badge)
        countView.text = count.toString()

        countView.visibility = if (count > 0) View.VISIBLE else View.GONE
    }

}