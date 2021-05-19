package com.yechaoa.materialdesign.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.DialogMyBottomSheetBinding


class MyBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogMyBottomSheetBinding

    /**
     * setStyle 圆角效果
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogMyBottomSheetBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        //do something
        binding.tvCancel.setOnClickListener { dismiss() }
    }

    /**
     * 设置固定高度
     */
    override fun onStart() {
        super.onStart()
//        //拿到系统的 bottom_sheet
//        val view: FrameLayout = dialog?.findViewById(R.id.design_bottom_sheet)!!
//        //获取behavior
//        val behavior = BottomSheetBehavior.from(view)
//        //设置弹出高度
//        behavior.peekHeight = 350
    }

}