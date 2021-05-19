package com.yechaoa.materialdesign.activity

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityTabLayoutCustomViewBinding
import com.yechaoa.materialdesign.fragment.Fragment1
import java.util.*

class TabLayoutCustomViewActivity : ToolbarActivity<ActivityTabLayoutCustomViewBinding>() {

    private val tabs: MutableList<String> = ArrayList()
    private val tabTimes: MutableList<String> = ArrayList()
    private val fragments: MutableList<Fragment> = ArrayList()

    private var holder: ViewHolder? = null

    override fun getViewBinding(): ActivityTabLayoutCustomViewBinding {
        return ActivityTabLayoutCustomViewBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.tab_layout_custom_view)
    }

    override fun initView() {
        tabs.add("已开抢")
        tabs.add("秒杀中")
        tabs.add("即将开始")
        tabs.add("明天开始")
        tabs.add("后天开始")
        tabTimes.add("22:00")
        tabTimes.add("22:00")
        tabTimes.add("22:00")
        tabTimes.add("22:00")
        tabTimes.add("22:00")
        fragments.add(Fragment1())
        fragments.add(Fragment1())
        fragments.add(Fragment1())
        fragments.add(Fragment1())
        fragments.add(Fragment1())
        mBinding.viewPager.offscreenPageLimit = 1
        mBinding.viewPager.adapter = TabAdapter(supportFragmentManager)
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
        initTabView()
    }

    private fun initTabView() {
        holder = null
        for (i in tabs.indices) {
            //获取tab
            val tab = mBinding.tabLayout.getTabAt(i)
            //给tab设置自定义布局
            tab!!.setCustomView(R.layout.tab_item)
            holder = ViewHolder(tab.customView!!)
            //填充数据
            holder!!.mTabItemTime.text = tabTimes[i]
            holder!!.mTabItemName.text = tabs[i]
            //默认选择第一项
            if (i == 0) {
                holder!!.mTabItemTime.isSelected = true
                holder!!.mTabItemName.isSelected = true
                holder!!.mTabItemTime.textSize = 18f
                holder!!.mTabItemName.textSize = 12f
            }
        }
        mBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                holder = ViewHolder(tab.customView!!)
                holder!!.mTabItemTime.isSelected = true
                holder!!.mTabItemName.isSelected = true
                //设置选中后的字体大小
                holder!!.mTabItemTime.textSize = 18f
                holder!!.mTabItemName.textSize = 12f
                //关联Viewpager
                mBinding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                holder = ViewHolder(tab.customView!!)
                holder!!.mTabItemTime.isSelected = false
                holder!!.mTabItemName.isSelected = false
                //恢复默认字体大小
                holder!!.mTabItemTime.textSize = 12f
                holder!!.mTabItemName.textSize = 12f
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    internal inner class ViewHolder(tabView: View) {
        var mTabItemTime: TextView = tabView.findViewById<View>(R.id.tab_item_time) as TextView
        var mTabItemName: TextView = tabView.findViewById<View>(R.id.tab_item_name) as TextView
    }

    internal inner class TabAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabs[position]
        }
    }

}