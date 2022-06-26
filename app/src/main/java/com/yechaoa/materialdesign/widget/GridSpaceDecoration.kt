package com.yechaoa.materialdesign.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/6/19.
 * Describe : GridLayoutManager 分割间距
 */
class GridSpaceDecoration(private val rowSpace: Float, private val columnSpacing: Float) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val layoutManager: GridLayoutManager? = parent.layoutManager as? GridLayoutManager
        layoutManager?.spanCount?.let { count ->
            val position = parent.getChildAdapterPosition(view) // 获取view 在adapter中的位置。
            val column = position % count // view 所在的列
            outRect.left = (column * columnSpacing / count).toInt() // column * (列间距 * (1f / 列数))
            outRect.right = (columnSpacing - (column + 1) * columnSpacing / count).toInt() // 列间距 - (column + 1) * (列间距 * (1f /列数))
            // 如果position > 行数，说明不是在第一行，则不指定行高，其他行的上间距为 top=rowSpace
            if (position >= count) {
                outRect.top = rowSpace.toInt() // item top
            }
        }
    }

}