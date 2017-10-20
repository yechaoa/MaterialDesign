package com.yechaoa.materialdesign;

import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //设置返回键图标并处理点击事件
//        mToolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
//        mToolbar.setNavigationOnClickListener(null);

        //设置返回键不显示
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //回调刷新toolbar的menu，页面初始化或者在需要的时候调用
        invalidateOptionsMenu();
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

    /**
     * 重写onPrepareOptionsMenu，处理toolbar的menu，此处把搜索按钮去掉
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }
}
