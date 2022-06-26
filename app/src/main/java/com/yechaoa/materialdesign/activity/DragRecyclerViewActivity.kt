package com.yechaoa.materialdesign.activity

import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.adapter.DragGridAdapter
import com.yechaoa.materialdesign.adapter.DragLinearAdapter
import com.yechaoa.materialdesign.databinding.ActivityDragRecyclerviewBinding
import com.yechaoa.materialdesign.widget.GridSpaceDecoration


/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/6/19.
 * Describe :
 */
class DragRecyclerViewActivity : ToolbarActivity<ActivityDragRecyclerviewBinding>() {

    companion object {
        private var SPAN_COUNT = 5
    }

    override fun getViewBinding(): ActivityDragRecyclerviewBinding {
        return ActivityDragRecyclerviewBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.drag_recyclerview)
    }

    override fun initView() {
        initGrid()
        initLinear()
    }

    private fun initGrid() {
        mBinding.rvGrid.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        mBinding.rvGrid.addItemDecoration(GridSpaceDecoration(22f, 22f))
        val list = getDatas()
        val adapter = DragGridAdapter(this, list)
        mBinding.rvGrid.adapter = adapter
        adapter.setOnItemClickListener(object : DragGridAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@DragRecyclerViewActivity, list[position], Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initLinear() {
        mBinding.rvLinear.layoutManager = LinearLayoutManager(this)
        mBinding.rvLinear.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val list = getDatas()
        val adapter = DragLinearAdapter(this, list)
        mBinding.rvLinear.adapter = adapter
        adapter.setOnItemClickListener(object : DragLinearAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@DragRecyclerViewActivity, list[position], Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getDatas(): MutableList<String> {
        return mutableListOf(
            "JS",
            "Python",
            "Java",
            "C/C++",
            "C#",
            "PHP",
            "Kotlin",
            "Visual DT",
            "Swift",
            "Go",
            "OC",
            "Rust",
            "Ruby",
            "Dart",
            "Lua"
        )
    }
}