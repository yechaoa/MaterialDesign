package com.yechaoa.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ToolbarActivity {

    @BindView(R.id.btn_swipe_refresh)
    Button mBtnSwipeRefresh;
    @BindView(R.id.btn_floating_action)
    Button mBtnFloatingAction;
    @BindView(R.id.btn_snack_bar)
    Button mBtnSnackBar;
    @BindView(R.id.btn_tab_layout)
    Button mBtnTabLayout;
    @BindView(R.id.btn_card_view)
    Button mBtnCardView;
    @BindView(R.id.button6)
    Button mButton6;
    @BindView(R.id.button7)
    Button mButton7;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.btn_swipe_refresh, R.id.btn_floating_action, R.id.btn_snack_bar, R.id.btn_tab_layout, R.id.btn_card_view, R.id.button6, R.id.button7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_swipe_refresh:
                startActivity(new Intent(MainActivity.this, SwipeRefreshLayoutActivity.class));
                break;
            case R.id.btn_floating_action:
                startActivity(new Intent(MainActivity.this, FloatingActionButtonActivity.class));
                break;
            case R.id.btn_snack_bar:
                break;
            case R.id.btn_tab_layout:
                break;
            case R.id.btn_card_view:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
        }
    }
}
