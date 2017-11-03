package com.yechaoa.materialdesign.activity;

import android.support.design.widget.FloatingActionButton;

import com.yechaoa.materialdesign.R;
import com.yechaoa.materialdesign.activity.ToolbarActivity;

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
    protected void setToolbar() {
        mToolbar.setTitle("FloatingActionButton");
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.floating_button)
    public void onClick() {
        finish();
    }
}
