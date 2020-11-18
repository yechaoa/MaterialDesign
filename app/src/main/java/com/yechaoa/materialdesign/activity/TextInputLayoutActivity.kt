package com.yechaoa.materialdesign.activity;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import com.yechaoa.materialdesign.R;

import butterknife.BindView;

public class TextInputLayoutActivity extends ToolbarActivity {

    @BindView(R.id.til_name)
    TextInputLayout mTilName;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.et_name)
    TextInputEditText mEtName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_text_input_layout;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.text_input_layout);
    }

    @Override
    protected void initView() {
        mEtName.addTextChangedListener(mTextWatcher);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (mTilName.getEditText().getText().length() > mTilName.getCounterMaxLength())
                mTilName.setError("输入内容超过上限");
            else
                mTilName.setError(null);
        }
    };

}
