package com.yechaoa.materialdesign.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yechaoa.materialdesign.R;
import com.yechaoa.materialdesign.activity.ToolbarActivity;
import com.yechaoa.materialdesign.fragment.Fragment1;
import com.yechaoa.materialdesign.fragment.Fragment2;
import com.yechaoa.materialdesign.fragment.Fragment3;

import butterknife.BindView;

public class TabLayoutActivity extends ToolbarActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.tab_layout);
    }

    @Override
    protected void initView() {
        //设置adapter
        mViewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager()));
        //关联viewpager
        mTabLayout.setupWithViewPager(mViewPager);
        //设置图标
        mTabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher);
        //设置默认选中
        mTabLayout.getTabAt(0).select();
        //设置监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中
            }
        });
    }

    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        private String tabTitles[] = new String[]{"tab1", "tab2", "tab3"};
        private Fragment[] mFragment = new Fragment[]{new Fragment1(), new Fragment2(), new Fragment3()};

        private SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment[position];
        }

        @Override
        public int getCount() {
            return mFragment.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

    }
}
