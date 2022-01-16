package com.yechaoa.materialdesign.activity

import android.annotation.SuppressLint
import android.view.ViewTreeObserver
import androidx.core.content.ContextCompat
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityBadgeDrawableBinding

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/1/16.
 * Describe :
 */
class BadgeDrawableActivity : ToolbarActivity<ActivityBadgeDrawableBinding>() {

    override fun getViewBinding(): ActivityBadgeDrawableBinding {
        return ActivityBadgeDrawableBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.badge_drawable)
    }

    override fun initView() {
        initTabLayout()
        initTextView()
        initButton()
        initImageView()
        initNavigationView()
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun initTabLayout() {
        // 带数字小红点
        mBinding.tabLayout.getTabAt(0)?.let {
            it.orCreateBadge.apply {
                backgroundColor = ContextCompat.getColor(this@BadgeDrawableActivity, R.color.red)
                badgeTextColor = ContextCompat.getColor(this@BadgeDrawableActivity, R.color.white)
                number = 6
            }
        }

        // 不带数字小红点
        mBinding.tabLayout.getTabAt(1)?.let {
            it.orCreateBadge.apply {
                backgroundColor = ContextCompat.getColor(this@BadgeDrawableActivity, R.color.red)
                badgeTextColor = ContextCompat.getColor(this@BadgeDrawableActivity, R.color.white)
            }
        }
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun initTextView() {
        // 在视图树变化
        mBinding.tvBadge.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                BadgeDrawable.create(this@BadgeDrawableActivity).apply {
                    // 设置基于目标view的位置
                    badgeGravity = BadgeDrawable.TOP_END
                    number = 6
                    backgroundColor = ContextCompat.getColor(this@BadgeDrawableActivity, R.color.colorPrimary)
                    isVisible = true
                    BadgeUtils.attachBadgeDrawable(this, mBinding.tvBadge)
                }
                mBinding.tvBadge.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun initButton() {
        mBinding.mbBadge.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            @SuppressLint("UnsafeOptInUsageError")
            override fun onGlobalLayout() {
                BadgeDrawable.create(this@BadgeDrawableActivity).apply {
                    badgeGravity = BadgeDrawable.TOP_START
                    number = 6
                    backgroundColor = ContextCompat.getColor(this@BadgeDrawableActivity, R.color.red)
                    // MaterialButton本身有间距，不设置为0dp的话，可以设置badge的偏移量
                    verticalOffset = 15
                    horizontalOffset = 10
                    BadgeUtils.attachBadgeDrawable(this, mBinding.mbBadge, mBinding.flBtn)
                }
                mBinding.mbBadge.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun initImageView() {
        mBinding.sivBadge.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            @SuppressLint("UnsafeOptInUsageError")
            override fun onGlobalLayout() {
                BadgeDrawable.create(this@BadgeDrawableActivity).apply {
                    badgeGravity = BadgeDrawable.TOP_END
                    number = 99999
                    // badge最多显示字符，默认999+ 是4个字符（带'+'号）
                    maxCharacterCount = 3
                    backgroundColor = ContextCompat.getColor(this@BadgeDrawableActivity, R.color.red)
                    BadgeUtils.attachBadgeDrawable(this, mBinding.sivBadge, mBinding.flImg)
                }
                mBinding.sivBadge.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun initNavigationView() {
        mBinding.navigationView.getOrCreateBadge(R.id.navigation_home).apply {
            backgroundColor = ContextCompat.getColor(this@BadgeDrawableActivity, R.color.red)
            badgeTextColor = ContextCompat.getColor(this@BadgeDrawableActivity, R.color.white)
            number = 9999
        }
    }

}