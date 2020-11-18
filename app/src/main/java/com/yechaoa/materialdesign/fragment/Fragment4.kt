package com.yechaoa.materialdesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.adapter.StringAdapter
import kotlinx.android.synthetic.main.fragment_fragment4.*
import java.util.*

class Fragment4 : Fragment() {

    private var mList: MutableList<String> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragment4, container, false)
        initData()
        return view
    }

    private fun initData() {
        mList = ArrayList()
        for (i in 0..19) {
            mList.add("MaterialDesign$i")
        }
    }

    override fun onStart() {
        super.onStart()
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycleView.layoutManager = linearLayoutManager
        recycleView.adapter = StringAdapter(activity, mList)
    }

}