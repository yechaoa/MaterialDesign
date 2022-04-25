package com.yechaoa.materialdesign.activity

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityTabLayoutBinding
import com.yechaoa.materialdesign.dialog.CompanyDialog
import com.yechaoa.materialdesign.fragment.Fragment1
import com.yechaoa.materialdesign.fragment.Fragment2
import com.yechaoa.materialdesign.fragment.Fragment3

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/1/16.
 * Describe :
 */
class TabLayoutActivity : ToolbarActivity<ActivityTabLayoutBinding>() {

    private var defaultIndex = 0

    override fun getViewBinding(): ActivityTabLayoutBinding {
        return ActivityTabLayoutBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.tab_layout)
    }

    override fun initView() {
        initViewPager()

        initTabLayout1()//基础样式
        initTabLayout2()//添加icon & 去掉indicator
        initTabLayout3()//style 字体大小、加粗 & 顶部indicator
        initTabLayout4()//下划线样式 & tab分割线
        initTabLayout5()//Badge 数字 & 红点
        initTabLayout6()//TabLayout样式 & 选中字体加粗
        initTabLayout7()//获取隐藏tab
        initTabLayout8()//自定义item view & lottie
    }

    private fun initViewPager() {
        //viewpager设置adapter
        mBinding.viewPager.adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        mBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mBinding.tabLayout8.getTabAt(position)?.select()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    /**
     * 基础样式
     */
    private fun initTabLayout1() {
        //tabLayout关联viewpager
        mBinding.tabLayout1.setupWithViewPager(mBinding.viewPager)
        //设置默认选中
        mBinding.tabLayout1.getTabAt(defaultIndex)?.select()
    }

    /**
     * 添加icon & 去掉indicator
     */
    private fun initTabLayout2() {
        mBinding.tabLayout2.setupWithViewPager(mBinding.viewPager)
        for (index in 0..mBinding.tabLayout2.tabCount) {
            mBinding.tabLayout2.getTabAt(index)?.setIcon(R.mipmap.ic_launcher)
        }
    }

    /**
     * style 字体大小、加粗 & 顶部indicator
     */
    private fun initTabLayout3() {
        mBinding.tabLayout3.setupWithViewPager(mBinding.viewPager)
    }

    /**
     * 下划线样式 & tab分割线
     */
    private fun initTabLayout4() {
        mBinding.tabLayout4.setupWithViewPager(mBinding.viewPager)
        //设置 分割线
        for (index in 0..mBinding.tabLayout4.tabCount) {
            val linearLayout = mBinding.tabLayout4.getChildAt(index) as? LinearLayout
            linearLayout?.let {
                it.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
                it.dividerDrawable = ContextCompat.getDrawable(this, R.drawable.shape_tab_divider)
                it.dividerPadding = 30
            }
        }
    }

    /**
     * Badge 数字 & 红点
     */
    private fun initTabLayout5() {
        mBinding.tabLayout5.setupWithViewPager(mBinding.viewPager)

        // 数字
        mBinding.tabLayout5.getTabAt(defaultIndex)?.let { tab ->
            tab.orCreateBadge.apply {
                backgroundColor = Color.RED
                maxCharacterCount = 3
                number = 99999
                badgeTextColor = Color.WHITE
            }
        }

        // 红点
        mBinding.tabLayout5.getTabAt(1)?.let { tab ->
            tab.orCreateBadge.backgroundColor = ContextCompat.getColor(this, R.color.orange)
        }
    }

    /**
     * TabLayout样式 & 选中字体加粗
     */
    private fun initTabLayout6() {
        mBinding.tabLayout6.setupWithViewPager(mBinding.viewPager)
        mBinding.tabLayout6.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                switchTextStyle(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                switchTextStyle(tab)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun switchTextStyle(tab: TabLayout.Tab?) {
        tab?.let {
            val textView = it.view.getChildAt(1) as TextView
            textView.typeface = Typeface.defaultFromStyle(if (it.isSelected) Typeface.BOLD else Typeface.NORMAL)
        }
    }

    private val companyMap = mapOf("苹果" to 2, "亚马逊" to 0, "谷歌" to 8, "微软" to 0, "阿里巴巴" to 0, "腾讯" to 0, "脸书" to 0, "三星" to 0, "思科" to 2)

    /**
     * 隐藏tab count红点提示function & tab宽度
     */
    private fun initTabLayout7() {
        companyMap.forEach {
            val tab = mBinding.tabLayout7.newTab()
            tab.text = it.key
            tab.orCreateBadge.apply {
                backgroundColor = Color.RED
                maxCharacterCount = 3
                number = it.value
                badgeTextColor = Color.WHITE
                isVisible = it.value > 0
            }

            hideToolTipText(tab)

            mBinding.tabLayout7.addTab(tab)
        }

        // mBinding.tabLayout7.setOnScrollChangeListener() // min api 23 (6.0)
        // 适配 5.0  滑动过程中判断右侧小红点是否需要显示
        mBinding.tabLayout7.viewTreeObserver.addOnScrollChangedListener {
            mBinding.vArrowDot.visibility = if (isShowDot()) View.VISIBLE else View.INVISIBLE
        }

        mBinding.ivArrow.setOnClickListener {
            CompanyDialog(companyMap, mBinding.tabLayout7.selectedTabPosition).show(supportFragmentManager, "CompanyDialog")
        }
    }

    /**
     * 隐藏长按显示文本
     */
    private fun hideToolTipText(tab: TabLayout.Tab) {
        // 取消长按事件
        tab.view.isLongClickable = false
        // api 26 以上 设置空text
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            tab.view.tooltipText = ""
        }
    }

    override fun onResume() {
        super.onResume()
        // 初始化判断右侧小红点是否需要显示
        mBinding.tabLayout7.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                mBinding.vArrowDot.visibility = if (isShowDot()) View.VISIBLE else View.INVISIBLE
                mBinding.tabLayout7.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private var lastShowIndex = 0// 最后一个可见tab

    private fun isShowDot(): Boolean {
        var showIndex = 0
        var tipCount = 0
        companyMap.keys.forEachIndexed { index, _ ->
            mBinding.tabLayout7.getTabAt(index)?.let { tab ->
                val tabView = tab.view as LinearLayout
                val rect = Rect()
                val visible = tabView.getLocalVisibleRect(rect)
                // 可见范围小于80%也在计算范围之内，剩下20%宽度足够红点透出（可自定义）
                if (visible && rect.right > tab.view.width * 0.8) {
                    showIndex = index
                } else {
                    //if (index > showIndex) // 任意一个有count的tab隐藏就会显示，比如第一个在滑动过程中会隐藏，也在计算范围之内
                    if (index > lastShowIndex) { // 只检测右侧隐藏且有count的tab 才在计算范围之内
                        tab.badge?.let { tipCount += it.number }
                    }
                }

            }
        }
        lastShowIndex = showIndex
        return tipCount > 0
    }


    /**
     * 自定义item view & lottie
     */
    private fun initTabLayout8() {
        val animMap = mapOf("party" to R.raw.anim_confetti, "pizza" to R.raw.anim_pizza, "apple" to R.raw.anim_apple)

        animMap.keys.forEach { s ->
            val tab = mBinding.tabLayout8.newTab()
            val view = LayoutInflater.from(this).inflate(R.layout.item_tab, null)
            val imageView = view.findViewById<LottieAnimationView>(R.id.lav_tab_img)
            val textView = view.findViewById<TextView>(R.id.tv_tab_text)
            imageView.setAnimation(animMap[s]!!)
            imageView.setColorFilter(Color.BLUE)
            textView.text = s
            tab.customView = view
            mBinding.tabLayout8.addTab(tab)
        }

        val defaultTab = mBinding.tabLayout8.getTabAt(0)
        defaultTab?.select()
        defaultTab?.setSelected()

        mBinding.tabLayout8.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.setSelected()
                tab?.let { mBinding.viewPager.currentItem = it.position }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.setUnselected()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    /**
     * 选中状态
     */
    fun TabLayout.Tab.setSelected() {
        this.customView?.let {
            val textView = it.findViewById<TextView>(R.id.tv_tab_text)
            val selectedColor = ContextCompat.getColor(this@TabLayoutActivity, R.color.colorPrimary)
            textView.setTextColor(selectedColor)

            val imageView = it.findViewById<LottieAnimationView>(R.id.lav_tab_img)
            if (!imageView.isAnimating) {
                imageView.playAnimation()
            }
            setLottieColor(imageView, true)
        }
    }

    /**
     * 未选中状态
     */
    fun TabLayout.Tab.setUnselected() {
        this.customView?.let {
            val textView = it.findViewById<TextView>(R.id.tv_tab_text)
            val unselectedColor = ContextCompat.getColor(this@TabLayoutActivity, R.color.black)
            textView.setTextColor(unselectedColor)

            val imageView = it.findViewById<LottieAnimationView>(R.id.lav_tab_img)
            if (imageView.isAnimating) {
                imageView.cancelAnimation()
                imageView.progress = 0f // 还原初始状态
            }
            setLottieColor(imageView, false)
        }
    }

    /**
     * set lottie icon color
     */
    private fun setLottieColor(imageView: LottieAnimationView?, isSelected: Boolean) {
        imageView?.let {
            val color = if (isSelected) R.color.colorPrimary else R.color.black
            val csl = AppCompatResources.getColorStateList(this@TabLayoutActivity, color)
            val filter = SimpleColorFilter(csl.defaultColor)
            val keyPath = KeyPath("**")
            val callback = LottieValueCallback<ColorFilter>(filter)
            it.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback)
        }
    }

    private inner class SimpleFragmentPagerAdapter constructor(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val tabTitles = arrayOf("Android", "Kotlin", "Flutter")
        private val fragment = arrayOf(Fragment1(), Fragment2(), Fragment3())

        override fun getItem(position: Int): Fragment {
            return fragment[position]
        }

        override fun getCount(): Int {
            return fragment.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabTitles[position]
        }
    }

}