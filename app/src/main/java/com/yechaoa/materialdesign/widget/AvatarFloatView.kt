package com.yechaoa.materialdesign.widget

import android.content.Context
import android.view.View
import android.view.ViewTreeObserver
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
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
        imageView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                // 获取宽度设置圆角
                val radius = imageView.width / 2f
                imageView.shapeAppearanceModel = ShapeAppearanceModel().toBuilder().setAllCornerSizes(radius).build()
            }
        })
        return imageView
    }

    override fun getIsCanDrag(): Boolean {
        return true
    }

    override fun getAdsorbType(): Int {
        return mAdsorbType
    }

    override fun getAdsorbTime(): Long {
        return 3000
    }

    fun setAdsorbType(type: Int) {
        mAdsorbType = type
    }


}