package com.yechaoa.materialdesign.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.yechaoa.materialdesign.R;
import com.yechaoa.materialdesign.fragment.Fragment1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TabLayoutCustomViewActivity extends ToolbarActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<String> tabs = new ArrayList<>();
    private List<String> tabTimes = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private ViewHolder holder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_layout_custom_view;
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.tab_layout_custom_view);
    }

    @Override
    protected void initView() {
        tabs.add("已开抢");
        tabs.add("秒杀中");
        tabs.add("即将开始");
        tabs.add("明天开始");
        tabs.add("后天开始");

        tabTimes.add("22:00");
        tabTimes.add("22:00");
        tabTimes.add("22:00");
        tabTimes.add("22:00");
        tabTimes.add("22:00");

        fragments.add(new Fragment1());
        fragments.add(new Fragment1());
        fragments.add(new Fragment1());
        fragments.add(new Fragment1());
        fragments.add(new Fragment1());

        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

        initTabView();
    }

    private void initTabView() {
        holder = null;
        for (int i = 0; i < tabs.size(); i++) {
            //获取tab
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            //给tab设置自定义布局
            tab.setCustomView(R.layout.tab_item);
            holder = new ViewHolder(tab.getCustomView());
            //填充数据
            holder.mTabItemTime.setText(String.valueOf(tabTimes.get(i)));
            holder.mTabItemName.setText(tabs.get(i));
            //默认选择第一项
            if (i == 0) {
                holder.mTabItemTime.setSelected(true);
                holder.mTabItemName.setSelected(true);
                holder.mTabItemTime.setTextSize(18);
                holder.mTabItemName.setTextSize(12);
            }
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                holder = new ViewHolder(tab.getCustomView());
                holder.mTabItemTime.setSelected(true);
                holder.mTabItemName.setSelected(true);
                //设置选中后的字体大小
                holder.mTabItemTime.setTextSize(18);
                holder.mTabItemName.setTextSize(12);
                //关联Viewpager
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                holder = new ViewHolder(tab.getCustomView());
                holder.mTabItemTime.setSelected(false);
                holder.mTabItemName.setSelected(false);
                //恢复默认字体大小
                holder.mTabItemTime.setTextSize(12);
                holder.mTabItemName.setTextSize(12);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    class ViewHolder {
        TextView mTabItemTime;
        TextView mTabItemName;

        ViewHolder(View tabView) {
            mTabItemTime = (TextView) tabView.findViewById(R.id.tab_item_time);
            mTabItemName = (TextView) tabView.findViewById(R.id.tab_item_name);
        }
    }

    class TabAdapter extends FragmentPagerAdapter {

        TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}
