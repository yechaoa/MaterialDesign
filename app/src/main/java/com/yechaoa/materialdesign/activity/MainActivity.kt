package com.yechaoa.materialdesign.activity

import android.content.Intent
import android.view.Menu
import com.yechaoa.materialdesign.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setToolbar() {}

    override fun initView() {
        //设置返回键图标并处理点击事件
//        mToolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
//        mToolbar.setNavigationOnClickListener(null);

        //设置返回键不显示
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        //回调刷新toolbar的menu，页面初始化或者在需要的时候调用
        invalidateOptionsMenu()

        setListener()
    }

    private fun setListener() {
        btn_swipe_refresh.setOnClickListener {
            openActivity(SwipeRefreshLayoutActivity::class.java)
        }
        btn_floating_action.setOnClickListener {
            openActivity(FloatingActionButtonActivity::class.java)
        }
        btn_snack_bar.setOnClickListener {
            openActivity(SnackbarActivity::class.java)
        }
        btn_tab_layout.setOnClickListener {
            openActivity(TabLayoutActivity::class.java)
        }
        btn_card_view.setOnClickListener {
            openActivity(CardViewActivity::class.java)
        }
        bottom_navigation.setOnClickListener {
            openActivity(BottomNavigationActivity::class.java)
        }
        scrolling_bar.setOnClickListener {
            openActivity(ScrollingActivity::class.java)
        }
        text_input_layout.setOnClickListener {
            openActivity(TextInputLayoutActivity::class.java)
        }
        search_view.setOnClickListener {
            openActivity(SearchViewActivity::class.java)
        }
        tab_layout_custom_view.setOnClickListener {
            openActivity(TabLayoutCustomViewActivity::class.java)
        }
        drawer_layout.setOnClickListener {
            openActivity(DrawerLayoutActivity::class.java)
        }
    }

    /**
     * 重写onPrepareOptionsMenu，处理toolbar的menu，此处把搜索按钮去掉
     */
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        //menu.findItem(R.id.action_search).setVisible(false);
        return super.onPrepareOptionsMenu(menu)
    }

    private fun openActivity(targetActivityClass: Class<*>) {
        startActivity(Intent(this@MainActivity, targetActivityClass))
    }
}