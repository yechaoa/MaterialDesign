package com.yechaoa.materialdesign.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yechaoa.materialdesign.R
import kotlinx.android.synthetic.main.dialog_my_bottom_sheet.view.*


class MyBottomSheetDialog : BottomSheetDialogFragment() {

    /**
     * setStyle 圆角效果
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_my_bottom_sheet, null)
        dialog.setContentView(view)
        initView(view)
        return dialog
    }

    private fun initView(rootView: View) {
        //do something
        rootView.tv_cancel.setOnClickListener { dismiss() }
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