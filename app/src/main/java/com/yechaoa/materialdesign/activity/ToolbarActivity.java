package com.yechaoa.materialdesign.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yechaoa.materialdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class ToolbarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(getLayoutId(), null);
        setContentView(view);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        //显示返回键并设置可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //mToolbar.inflateMenu(R.menu.menu_toolbar);
        //自定义返回键icon
        //mToolbar.setNavigationIcon(R.drawable.ic_back);

        setToolbar();
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void setToolbar();

    protected abstract void initView();

    /**
     * 引入toolbar上的menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * toolbar上menu的事件处理
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case R.id.menu_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }

}
