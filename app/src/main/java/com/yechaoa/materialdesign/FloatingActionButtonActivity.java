package com.yechaoa.materialdesign;

import android.support.design.widget.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;

public class FloatingActionButtonActivity extends ToolbarActivity {

    @BindView(R.id.floating_button)
    FloatingActionButton mFloatingButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_floating_action_button;
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("FloatingActionButton");
    }

    @OnClick(R.id.floating_button)
    public void onClick() {
        finish();
    }
}
