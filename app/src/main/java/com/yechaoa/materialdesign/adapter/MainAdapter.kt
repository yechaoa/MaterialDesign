package com.yechaoa.materialdesign.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yechaoa.materialdesign.R

/**
 * Created by yechao on 2017/11/15.
 * Describe :
 */
class MainAdapter(private val mContext: Context?, private val mList: List<String>?) : RecyclerView.Adapter<MainAdapter.ViewHolder>(), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItemTextView.text = mList!![position]
        holder.mItemTextView.tag = position
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemTextView: TextView = itemView.findViewById(R.id.item_textView)
    }

    interface OnItemClickListener {
        fun onItemClick(v: View,position: Int)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener:OnItemClickListener){
        mListener=listener
    }

    override fun onClick(v: View) {
        if (mListener!=null){
            mListener?.onItemClick(v, v.tag as Int)
        }
    }

}