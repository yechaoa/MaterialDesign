package com.yechaoa.materialdesign.activity

import android.text.Editable
import android.text.TextWatcher
import com.yechaoa.materialdesign.R
import kotlinx.android.synthetic.main.activity_text_input_layout.*

class TextInputLayoutActivity : ToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_text_input_layout
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.text_input_layout)
    }

    override fun initView() {
        et_name!!.addTextChangedListener(mTextWatcher)
    }

    private val mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            if (til_name.editText!!.text.length > til_name.counterMaxLength)
                til_name.error = "输入内容超过上限"
            else
                til_name.error = null
        }
    }
}