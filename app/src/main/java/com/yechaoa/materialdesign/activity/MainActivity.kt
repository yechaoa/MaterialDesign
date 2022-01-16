package com.yechaoa.materialdesign.activity

import android.content.Intent
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.yechaoa.materialdesign.R
import com.yechaoa.materialdesign.adapter.MainAdapter
import com.yechaoa.materialdesign.databinding.ActivityMainBinding

class MainActivity : ToolbarActivity<ActivityMainBinding>() {

    private lateinit var mList: MutableList<String>
    private lateinit var mAdapter: MainAdapter

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {}

    override fun initView() {
        mToolbar.navigationIcon = null

        //回调刷新toolbar的menu，页面初始化或者在需要的时候调用
        invalidateOptionsMenu()

        mList = mutableListOf(
            getString(R.string.swipe_refresh_layout),
            getString(R.string.floating_action_button),
            getString(R.string.snack_bar),
            getString(R.string.tab_layout),
            getString(R.string.card_view),
            getString(R.string.bottom_navigation),
            getString(R.string.collapsing_toolbar),
            getString(R.string.text_input_layout),
            getString(R.string.search_view),
            getString(R.string.tab_layout_custom_view),
            getString(R.string.drawer_layout),
            getString(R.string.bottom_sheet),
            getString(R.string.material_button),
            getString(R.string.shapeable_image_view),
            getString(R.string.badge_drawable)
        )

        mBinding.recycleView.layoutManager = GridLayoutManager(this, 2)
        mAdapter = MainAdapter(this, mList)
        mBinding.recycleView.adapter = mAdapter

        setListener()
    }

    private fun setListener() {
        mAdapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                when (position) {
                    0 -> openActivity(SwipeRefreshLayoutActivity::class.java)
                    1 -> openActivity(FloatingActionButtonActivity::class.java)
                    2 -> openActivity(SnackbarActivity::class.java)
                    3 -> openActivity(TabLayoutActivity::class.java)
                    4 -> openActivity(CardViewActivity::class.java)
                    5 -> openActivity(BottomNavigationActivity::class.java)
                    6 -> openActivity(CollapsingToolbarActivity::class.java)
                    7 -> openActivity(TextInputLayoutActivity::class.java)
                    8 -> openActivity(SearchViewActivity::class.java)
                    9 -> openActivity(TabLayoutCustomViewActivity::class.java)
                    10 -> openActivity(DrawerLayoutActivity::class.java)
                    11 -> openActivity(BottomSheetActivity::class.java)
                    12 -> openActivity(MaterialButtonActivity::class.java)
                    13 -> openActivity(ShapeableImageViewActivity::class.java)
                    14 -> openActivity(BadgeDrawableActivity::class.java)
                }
            }
        })
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