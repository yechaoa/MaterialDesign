package com.yechaoa.materialdesign.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yechaoa.materialdesign.R

/**
 * Created by yechao on 2022/6/19.
 * Describe :
 */
class DragLinearAdapter(private val mContext: Context, private val mList: List<String>) : RecyclerView.Adapter<DragLinearAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_drag_linear, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItemTextView.text = mList[position]
        holder.mItemTextView.setOnClickListener {
            mListener?.onItemClick(position)
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
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
}