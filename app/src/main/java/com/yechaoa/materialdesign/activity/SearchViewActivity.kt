package com.yechaoa.materialdesign.activity;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.yechaoa.materialdesign.R;

import butterknife.BindView;

public class SearchViewActivity extends ToolbarActivity {

    @BindView(R.id.constraint_layout)
    ConstraintLayout mConstraintLayout;
    @BindView(R.id.search_result)
    TextView mSearchResult;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_view;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.search_view);
    }

    @Override
    protected void initView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //引用menu文件
        getMenuInflater().inflate(R.menu.menu_search, menu);

        //找到SearchView并配置相关参数
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        //搜索图标是否显示在搜索框内
        mSearchView.setIconifiedByDefault(true);
        //设置搜索框展开时是否显示提交按钮，可不显示
        mSearchView.setSubmitButtonEnabled(true);
        //让键盘的回车键设置成搜索
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        //搜索框是否展开，false表示展开
        mSearchView.setIconified(false);
        //获取焦点
        mSearchView.setFocusable(true);
        mSearchView.requestFocusFromTouch();
        //设置提示词
        mSearchView.setQueryHint("请输入关键字");
        //设置输入框文字颜色
        EditText editText = (EditText) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editText.setHintTextColor(ContextCompat.getColor(this, R.color.white));
        editText.setTextColor(ContextCompat.getColor(this, R.color.white));

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(mConstraintLayout, "搜索内容===" + query, Snackbar.LENGTH_SHORT).show();

                //伪搜索
                mSearchResult.setVisibility(View.VISIBLE);

                //清除焦点，收软键盘
                //mSearchView.clearFocus();

                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                //do something
                //当没有输入任何内容的时候清除结果，看实际需求
                if (TextUtils.isEmpty(newText)) mSearchResult.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
