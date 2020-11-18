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
class StringAdapter(private val mContext: Context?, private val mList: List<String>?) : RecyclerView.Adapter<StringAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_string, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItemTextView!!.text = mList!![position]
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemTextView: TextView? = itemView.findViewById(R.id.item_textView)
    }
}