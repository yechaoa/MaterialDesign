package com.yechaoa.materialdesign.activity;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yechaoa.materialdesign.R;

import butterknife.BindView;
import butterknife.OnClick;

public class DrawerLayoutActivity extends ToolbarActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawer_layout;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.drawer_layout);
    }

    @Override
    protected void initView() {

        //mDrawerLayout与mToolbar关联起来
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        //初始化状态
        actionBarDrawerToggle.syncState();
        //ActionBarDrawerToggle implements DrawerLayout.DrawerListener
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        //监听
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                Log.i("---", "滑动中");
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Log.i("---", "打开");
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Log.i("---", "关闭");
            }

            @Override
            public void onDrawerStateChanged(int i) {
                Log.i("---", "状态改变");
            }
        });


        //NavigationView 内容点击事件
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @OnClick({R.id.btn_open_left, R.id.btn_open_right, R.id.btn_close_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_open_left:
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.btn_open_right:
                mDrawerLayout.openDrawer(Gravity.END);
                break;
            case R.id.btn_close_right:
                mDrawerLayout.closeDrawer(Gravity.END);//关闭执行DrawerLayout
                //mDrawerLayout.closeDrawers();//关闭所有
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String title = (String) menuItem.getTitle();
        Toast.makeText(this, "点击了----- " + title, Toast.LENGTH_SHORT).show();
        return false;
    }
}
