package com.yechaoa.materialdesign.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.fragment.Fragment1
import com.yechaoa.materialdesign.fragment.Fragment2
import com.yechaoa.materialdesign.fragment.Fragment3
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayoutActivity : ToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_tab_layout
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.tab_layout)
    }

    override fun initView() {
        //设置adapter
        view_pager.adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        //关联viewpager
        tab_layout.setupWithViewPager(view_pager)
        //设置图标
        tab_layout.getTabAt(0)!!.setIcon(R.mipmap.ic_launcher)
        //设置默认选中
        tab_layout.getTabAt(0)!!.select()
        //设置监听
        tab_layout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //选中
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //未选中
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //再次选中
            }
        })
    }

    private inner class SimpleFragmentPagerAdapter constructor(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val tabTitles = arrayOf("tab1", "tab2", "tab3")
        private val mFragment = arrayOf(Fragment1(), Fragment2(), Fragment3())

        override fun getItem(position: Int): Fragment {
            return mFragment[position]
        }

        override fun getCount(): Int {
            return mFragment.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabTitles[position]
        }
    }
}