package com.yechaoa.materialdesign.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yechaoa.materialdesign.R

/**
 * Created by yechao on 2022/6/19.
 * Describe :
 */
class DragAdapter(private val mContext: Context, private val mList: List<String>) : RecyclerView.Adapter<DragAdapter.ViewHolder>() {

    val fixedPosition = 0 // 固定菜单

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_drag_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItemTextView.text = mList[position]

        // 第一个固定菜单
        val drawable = holder.mItemTextView.background as GradientDrawable
        if (holder.adapterPosition == 0) {
            drawable.color = ContextCompat.getColorStateList(mContext, R.color.greenAccent)
        }else{
            drawable.color = ContextCompat.getColorStateList(mContext, R.color.greenPrimary)
        }

        holder.mItemTextView.setOnClickListener {
            mListener?.onItemClick(holder.adapterPosition)
        }
        holder.mItemTextView.setOnLongClickListener {
            mListener?.onItemLongClick(holder)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemTextView: TextView = itemView.findViewById(R.id.item_textView)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick(holder: ViewHolder)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
}