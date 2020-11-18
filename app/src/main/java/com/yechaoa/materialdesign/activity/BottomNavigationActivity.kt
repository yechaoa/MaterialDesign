package com.yechaoa.materialdesign.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.yechaoa.materialdesign.R;

import butterknife.BindView;

public class BottomNavigationActivity extends ToolbarActivity {

    @BindView(R.id.message)
    TextView mTextMessage;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.navigation2)
    BottomNavigationView mNavigation2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bottom_navigation;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.bottom_navigation);
    }

    @Override
    protected void initView() {
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigation2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigation2.setSelectedItemId(R.id.navigation_dashboard);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_test:
                    mTextMessage.setText(R.string.title_test);
                    return true;
            }
            return false;
        }
    };

}
