package com.yechaoa.materialdesign.activity

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityTabLayoutBinding
import com.yechaoa.materialdesign.fragment.Fragment1
import com.yechaoa.materialdesign.fragment.Fragment2
import com.yechaoa.materialdesign.fragment.Fragment3

class TabLayoutActivity : ToolbarActivity<ActivityTabLayoutBinding>() {

    override fun getViewBinding(): ActivityTabLayoutBinding {
        return ActivityTabLayoutBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.tab_layout)
    }

    override fun initView() {
        //设置adapter
        mBinding.viewPager.adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        //关联viewpager
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
        mBinding.tabLayout2.setupWithViewPager(mBinding.viewPager)
        mBinding.tabLayout3.setupWithViewPager(mBinding.viewPager)
        //设置图标
        mBinding.tabLayout.getTabAt(0)!!.setIcon(R.mipmap.ic_launcher)
        //设置默认选中
        mBinding.tabLayout.getTabAt(0)!!.select()
        //设置监听
        mBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //选中
                if (tab.position == 1) {
                    Toast.makeText(this@TabLayoutActivity, "选中了第2个tab", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //未选中
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //再次选中
            }
        })
    }

    private inner class SimpleFragmentPagerAdapter constructor(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val tabTitles = arrayOf("Tab1", "Tab2", "Tab3")
        private val mFragment = arrayOf(Fragment1(), Fragment2(), Fragment3())

        override fun getItem(position: Int): Fragment {
            return mFragment[position]
        }

        override fun getCount(): Int {
            return mFragment.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabTitles[position]
        }
    }

}