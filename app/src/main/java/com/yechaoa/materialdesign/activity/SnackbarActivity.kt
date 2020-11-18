package com.yechaoa.materialdesign.activity;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yechaoa.materialdesign.R;

import butterknife.BindView;
import butterknife.OnClick;

public class SnackbarActivity extends ToolbarActivity {

    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.constraint_layout)
    ConstraintLayout mConstraintLayout;

    String TAG = "SnackbarActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_snackbar;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.snack_bar);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.button, R.id.button2, R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Snackbar.make(mConstraintLayout, "已加入行程", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                final Snackbar mSnackbar = Snackbar.make(mConstraintLayout, "已加入行程", Snackbar.LENGTH_INDEFINITE);
                //设置Action并监听事件
                mSnackbar.setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //to do something
                    }
                })
                        .show();
                break;
            case R.id.button3:
                final Snackbar mSnackbar1 = Snackbar.make(mConstraintLayout, "已加入行程", Snackbar.LENGTH_INDEFINITE);
                //设置Snackbar的背景颜色
                mSnackbar1.getView().setBackgroundColor(ContextCompat.getColor(SnackbarActivity.this, R.color.colorPrimary));
                //设置Action文字的背景颜色
                mSnackbar1.setActionTextColor(Color.WHITE);
                //设置Action并监听事件
                mSnackbar1.setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //to do something
                    }
                })
                        .show();
                //添加回调
                mSnackbar1.addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        Log.i(TAG, "onDismissed(SnackbarActivity.java:78)--->> " + "onDismissed");
                    }

                    @Override
                    public void onShown(Snackbar sb) {
                        super.onShown(sb);
                        Log.i(TAG, "onShown(SnackbarActivity.java:84)--->> " + "onShown");
                    }
                });
                break;
        }
    }
}
