package com.yechaoa.materialdesign.activity;

import android.view.View;
import android.widget.Button;

import com.yechaoa.materialdesign.R;

import butterknife.BindView;

public class MyDialogActivity extends ToolbarActivity {

    @BindView(R.id.btn_dialog)
    Button mBtnDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_dialog;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle("Dialog");
    }

    @Override
    protected void initView() {
        mBtnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
