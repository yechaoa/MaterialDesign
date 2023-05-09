package com.yechaoa.materialdesign.activity

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.ViewCompat.offsetTopAndBottom
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityDynamicLayoutBinding

/**
 * 拖拽修改上下布局占比
 */
class DynamicLayoutActivity : ToolbarActivity<ActivityDynamicLayoutBinding>() {

    override fun getViewBinding(): ActivityDynamicLayoutBinding {
        return ActivityDynamicLayoutBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.dynamic_layout)
    }

    override fun initView() {
        setTouchListener()
    }

    private var mDownY = 0F

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener() {
        mBinding.layoutLine.setOnTouchListener { v, event ->
            val y = event.y
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mDownY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    val topHeight = (y - mDownY).toInt()
                    offsetTopAndBottom(v, (y - mDownY).toInt())
                    refreshTopLayout(topHeight)
                }
                MotionEvent.ACTION_UP -> {
                    // TODO: 保存当前位置、
                    // TODO: 优化：添加上下布局的最大、最小高度约束
                }
            }
            true
        }
    }

    private fun refreshTopLayout(topHeight: Int) {
        Log.d("aaa topHeight = ", topHeight.toString())
        val layoutParams = mBinding.layoutTop.layoutParams as ViewGroup.LayoutParams
        layoutParams.height = layoutParams.height + topHeight
        mBinding.layoutTop.layoutParams = layoutParams
    }

}