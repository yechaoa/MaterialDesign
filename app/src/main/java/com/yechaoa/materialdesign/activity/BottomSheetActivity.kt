package com.yechaoa.materialdesign.activity

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.dialog.MyBottomSheetDialog
import com.yechaoa.materialdesign.dialog.MyFullDialog
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheetActivity : ToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_bottom_sheet
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.bottom_sheet)
    }

    override fun initView() {

        btn_bottom_sheet.setOnClickListener {
            val behavior = BottomSheetBehavior.from(ll_bottom_sheet)
            if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                //如果是展开状态，则关闭，反之亦然
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        btn_bottom_sheet_dialog.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(R.layout.dialog_bottom_sheet)
            bottomSheetDialog.show()
        }

        btn_bottom_sheet_dialog_fragment.setOnClickListener {
            MyBottomSheetDialog().show(supportFragmentManager, "MyBottomSheetDialog")
        }

        btn_full.setOnClickListener {
            MyFullDialog().show(supportFragmentManager, "MyFullDialog")
        }
    }

}