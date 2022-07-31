package com.yechaoa.materialdesign.widget

import android.content.Context
import android.view.View
import com.google.android.material.imageview.ShapeableImageView
import com.yechaoa.materialdesign.R

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/7/31.
 * Describe :
 */
class AvatarFloatView(context: Context) : BaseFloatView(context) {

    private var mAdsorbType = ADSORB_VERTICAL

    override fun getChildView(): View {
        val imageView = ShapeableImageView(context)
        imageView.setImageResource(R.mipmap.ic_avatar)
        return imageView
    }

    override fun getIsCanDrag(): Boolean {
        return true
    }

    override fun getAdsorbType(): Int {
        return mAdsorbType
    }

    fun setAdsorbType(type: Int) {
        mAdsorbType = type
    }
}