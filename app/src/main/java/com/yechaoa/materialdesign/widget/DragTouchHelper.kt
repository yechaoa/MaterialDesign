package com.yechaoa.materialdesign.widget

import android.graphics.drawable.GradientDrawable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.adapter.DragAdapter
import java.util.*

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/7/17.
 * Describe :
 */
class DragCallBack(adapter: DragAdapter, data: MutableList<String>) : ItemTouchHelper.Callback() {

    private val TAG = "DragCallBack"

    private var mData = data
    private var mAdapter = adapter

    fun setData(data: MutableList<String>) {
        mData = data
    }

    fun getData(): MutableList<String> {
        return mData
    }

    /**
     * 定义拖动方向
     * 1.网格布局：上下左右
     * 2.线性布局：上下/左右
     * 3.return makeMovementFlags
     */
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        var dragFlags = 0
        var swipeFlags = 0
        when (recyclerView.layoutManager) {
            is GridLayoutManager -> {
                // 网格布局
                dragFlags = ItemTouchHelper.LEFT or ItemTouchHelper.UP or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN
                return makeMovementFlags(dragFlags, swipeFlags)
            }
            is LinearLayoutManager -> {
                // 线性布局
                dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }
            else -> {
                // 其他情况可自行处理
                return 0
            }
        }
    }

    /**
     * 拖拽时回调
     */
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        // 起始位置
        val fromPosition = viewHolder.adapterPosition
        // 结束位置
        val toPosition = target.adapterPosition
        // 固定位置
        if (fromPosition == mAdapter.fixedPosition || toPosition == mAdapter.fixedPosition) {
            return false
        }
        // 根据滑动方向 交换数据
        if (fromPosition < toPosition) {
            // 含头不含尾
            for (index in fromPosition until toPosition) {
                Collections.swap(mData, index, index + 1)
            }
        } else {
            // 含头不含尾
            for (index in fromPosition downTo toPosition + 1) {
                Collections.swap(mData, index, index - 1)
            }
        }
        // 刷新布局
        mAdapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    /**
     * 滑动时回调
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.START) {
            Log.i(TAG, "START--->向左滑")
        } else {
            Log.i(TAG, "END--->向右滑")
        }
        val position = viewHolder.adapterPosition
        mData.removeAt(position)
        mAdapter.notifyItemRemoved(position)
    }

    /**
     * 拖拽或滑动 发生改变时回调
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder?.let {
                // 因为拿不到recyclerView，无法通过recyclerView.layoutManager来判断是什么布局，所以用item的宽度来判断
                // itemView.width > 500 用这个来判断是否是线性布局，实际取值自己看情况
                if (it.itemView.width > 500) {
                    // 线性布局 设置背景颜色
                    val drawable = it.itemView.background as GradientDrawable
                    drawable.color = ContextCompat.getColorStateList(it.itemView.context, R.color.greenDark)
                } else {
                    // 网格布局 设置选中放大
                    ViewCompat.animate(it.itemView).setDuration(200).scaleX(1.3F).scaleY(1.3F).start()
                }
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    /**
     * 拖拽或滑动 结束时回调
     */
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        // 恢复显示
        // 这里不能用if判断，因为GridLayoutManager是LinearLayoutManager的子类，改用when，类型推导有区别
        when (recyclerView.layoutManager) {
            is GridLayoutManager -> {
                // 网格布局 设置选中大小
                ViewCompat.animate(viewHolder.itemView).setDuration(200).scaleX(1F).scaleY(1F).start()
            }
            is LinearLayoutManager -> {
                // 线性布局 设置背景颜色
                val drawable = viewHolder.itemView.background as GradientDrawable
                drawable.color = ContextCompat.getColorStateList(viewHolder.itemView.context, R.color.greenPrimary)
            }
        }
        super.clearView(recyclerView, viewHolder)
    }

    /**
     * 是否支持长按拖拽，默认true
     * 1.如果需要自定义item的拖拽，需要重写此方法禁掉默认
     * 2.通过onTouch或onClick等事件，自定义触发
     * 3.调用mItemTouchHelper.startDrag方法开启
     */
    override fun isLongPressDragEnabled(): Boolean {
//        return super.isLongPressDragEnabled()
        return false
    }

    /**
     * 是否支持长按拖拽，默认true
     * 1.如果需要自定义item的滑动，需要重写此方法禁掉默认
     * 2.通过onTouch或onClick等事件，自定义触发
     * 3.调用mItemTouchHelper.startSwipe方法开启
     */
    override fun isItemViewSwipeEnabled(): Boolean {
        return super.isItemViewSwipeEnabled()
//        return false
    }
}