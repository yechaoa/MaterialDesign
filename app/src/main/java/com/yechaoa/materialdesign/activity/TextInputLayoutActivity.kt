package com.yechaoa.materialdesign.activity

import android.text.Editable
import android.text.TextWatcher
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.databinding.ActivityTextInputLayoutBinding

class TextInputLayoutActivity : ToolbarActivity<ActivityTextInputLayoutBinding>() {

    override fun getViewBinding(): ActivityTextInputLayoutBinding {
        return ActivityTextInputLayoutBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.text_input_layout)
    }

    override fun initView() {
        mBinding.etName.addTextChangedListener(mTextWatcher)
    }

    private val mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            if (mBinding.tilName.editText!!.text.length > mBinding.tilName.counterMaxLength)
                mBinding.tilName.error = "输入内容超过上限"
            else
                mBinding.tilName.error = null
        }
    }

}