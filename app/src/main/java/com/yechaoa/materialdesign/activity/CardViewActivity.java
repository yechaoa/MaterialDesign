package com.yechaoa.materialdesign.activity;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.yechaoa.materialdesign.R;

import butterknife.BindView;

public class CardViewActivity extends ToolbarActivity {

    @BindView(R.id.cardView)
    CardView mCardView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_view;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.card_view);
    }

    @Override
    protected void initView() {
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CardViewActivity.this, "CardView", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
