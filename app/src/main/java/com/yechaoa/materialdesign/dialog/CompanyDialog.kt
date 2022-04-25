package com.yechaoa.materialdesign.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.DialogCompanyBinding

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/4/22.
 * Describe :
 */
class CompanyDialog(private val companyMap: Map<String, Int>, private val selectedTabPosition: Int) : BottomSheetDialogFragment() {

    private lateinit var binding: DialogCompanyBinding
    private lateinit var mCompanyAdapter: CompanyAdapter

    /**
     * setStyle 圆角效果
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogCompanyBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.ivCancel.setOnClickListener { dismiss() }

        mCompanyAdapter = CompanyAdapter(requireContext(), companyMap.keys.toMutableList(), selectedTabPosition)
        binding.recycleView.adapter = mCompanyAdapter
    }

    class CompanyAdapter(private val context: Context, private val keys: MutableList<String>, private val selectedTabPosition: Int) :
        RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_string, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = keys[position]
            holder.textView.textSize = 16f
            if (position == selectedTabPosition) {
                holder.textView.setTextColor(ContextCompat.getColor(context, R.color.red))
            } else {
                holder.textView.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }

        override fun getItemCount(): Int {
            return keys.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView.findViewById(R.id.item_textView)
        }
    }
}