package com.yechaoa.materialdesign.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.yechaoa.materialdesign.R;
import com.yechaoa.materialdesign.activity.ToolbarActivity;

import butterknife.BindView;

public class SwipeRefreshLayoutActivity extends ToolbarActivity {

    @BindView(R.id.tv_refresh)
    TextView mTvRefresh;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            //关闭刷新
            mSwipeRefreshLayout.setRefreshing(false);
            mTvRefresh.setText("刷新完成");
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_swipe_refresh_layout;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.swipe_refresh_layout);
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        });
    }
}
