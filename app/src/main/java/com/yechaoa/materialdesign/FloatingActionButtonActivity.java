package com.yechaoa.materialdesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloatingActionButtonActivity extends AppCompatActivity {

    @BindView(R.id.floating_button)
    FloatingActionButton mFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.floating_button)
    public void onClick() {
        finish();
    }
}
