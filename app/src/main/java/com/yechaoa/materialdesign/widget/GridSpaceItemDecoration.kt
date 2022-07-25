package com.yechaoa.materialdesign.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/6/19.
 * Describe : GridLayoutManager 分割间距
 * 1.spanCount：列数
 * 2.spacing：间隔 px
 * 3.includeEdge：是否需要外边距
 */
class GridSpaceItemDecoration(private val spanCount: Int, private val spacing: Int = 20, private var includeEdge: Boolean = false) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        recyclerView.layoutManager?.let {
            when (recyclerView.layoutManager) {
                is GridLayoutManager -> {
                    val position = recyclerView.getChildAdapterPosition(view) // 获取item在adapter中的位置
                    val column = position % spanCount // item所在的列
                    if (includeEdge) {
                        outRect.left = spacing - column * spacing / spanCount
                        outRect.right = (column + 1) * spacing / spanCount
                        if (position < spanCount) {
                            outRect.top = spacing
                        }
                        outRect.bottom = spacing
                    } else {
                        outRect.left = column * spacing / spanCount
                        outRect.right = spacing - (column + 1) * spacing / spanCount
                        if (position >= spanCount) {
                            outRect.top = spanCount
                        }
                        outRect.bottom = spacing
                    }
                }
                is LinearLayoutManager -> {
                    outRect.top = spanCount
                    outRect.bottom = spacing
                }
            }
        }
    }

}