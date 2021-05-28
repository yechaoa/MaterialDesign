package com.yechaoa.materialdesign.activity

import android.graphics.Color
import android.graphics.Paint
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.shape.*
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityShapeableImageViewBinding

class ShapeableImageViewActivity : ToolbarActivity<ActivityShapeableImageViewBinding>() {

    override fun getViewBinding(): ActivityShapeableImageViewBinding {
        return ActivityShapeableImageViewBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.shapeable_image_view)
    }

    override fun initView() {

        // 代码设置圆角、切角
        val shapeAppearanceModel1 = ShapeAppearanceModel.builder().apply {
            setTopLeftCorner(RoundedCornerTreatment())
            setTopLeftCornerSize(80f)
            setBottomRightCorner(RoundedCornerTreatment())
            setBottomRightCornerSize(80f)
            setTopRightCorner(CutCornerTreatment())
            setTopRightCornerSize(80f)
            setBottomLeftCorner(CutCornerTreatment())
            setBottomLeftCornerSize(80f)
        }.build()
        mBinding.siv1.shapeAppearanceModel = shapeAppearanceModel1


        // 代码设置 角和边
        val shapeAppearanceModel2 = ShapeAppearanceModel.builder().apply {
            setAllCorners(RoundedCornerTreatment())
            setAllCornerSizes(50f)
            setAllEdges(TriangleEdgeTreatment(50f, false))
        }.build()
        val drawable2 = MaterialShapeDrawable(shapeAppearanceModel2).apply {
            setTint(ContextCompat.getColor(this@ShapeableImageViewActivity, R.color.colorPrimary))
            paintStyle = Paint.Style.FILL_AND_STROKE
            strokeWidth = 50f
            strokeColor = ContextCompat.getColorStateList(this@ShapeableImageViewActivity, R.color.red)
        }
        mBinding.text2.setTextColor(Color.WHITE)
        mBinding.text2.background = drawable2


        // 代码设置 聊天框效果
        val shapeAppearanceModel3 = ShapeAppearanceModel.builder().apply {
            setAllCorners(RoundedCornerTreatment())
            setAllCornerSizes(20f)
            setRightEdge(object : TriangleEdgeTreatment(20f, false) {
                // center 位置 ， interpolation 角的大小
                override fun getEdgePath(length: Float, center: Float, interpolation: Float, shapePath: ShapePath) {
                    super.getEdgePath(length, 35f, interpolation, shapePath)
                }
            })
        }.build()
        val drawable3 = MaterialShapeDrawable(shapeAppearanceModel3).apply {
            setTint(ContextCompat.getColor(this@ShapeableImageViewActivity, R.color.colorPrimary))
            paintStyle = Paint.Style.FILL
        }
        (mBinding.text3.parent as ViewGroup).clipChildren = false // 不限制子view在其范围内
        mBinding.text3.setTextColor(Color.WHITE)
        mBinding.text3.background = drawable3
    }

}