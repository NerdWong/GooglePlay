package com.khalil.googlepaly;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.astuetz.PagerSlidingTabStripExtends;
import com.khalil.googlepaly.adapter.MainFragmentPagerAdapter;
import com.khalil.googlepaly.base.BaseFragment;
import com.khalil.googlepaly.fragment.FragmentFactory;
import com.khalil.googlepaly.utils.LogUtils;
import com.khalil.googlepaly.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //主开发提交

//gittest测试提交

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_tabs)
    PagerSlidingTabStripExtends mMainTabs;
    @BindView(R.id.main_viewpager)
    ViewPager mMainViewpager;
    private ActionBarDrawerToggle mToggle;
    private String[] mMainTitles;
    private MainFragmentPagerAdapter mMainFragmentPagerAdapter;
    private MyOnPageChangeListener mMyOnPageChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initActionBar();
        initActionBarDrawerToggle();
        initData();
        initListener();
    }

    private void initListener() {
        /**
         * 1.tabs的选中监听
         * 2.viewPager的加载完成监听
         */
        //1.初始化PagerSlidingTabstrip绑定的viewpager,其内部实现已经给viewpager设置了选中监听了,所以只需要给tab设置监听即可
        mMyOnPageChangeListener = new MyOnPageChangeListener();
        mMainTabs.setOnPageChangeListener(mMyOnPageChangeListener);
        //2.viewpager的布局成功监听
        mMainViewpager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                //ViewPager已经展示给用户看-->说明HomeFragment和AppFragment已经创建好了
                //手动选中第一页，触发加载数据的方法
                mMyOnPageChangeListener.onPageSelected(0);
                //remove这个布局监听
                mMainTabs.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    /**
     * 绑定viewpager
     */
    private void initData() {
        mMainTitles = UIUtils.getStrings(R.array.main_titles);
        mMainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mMainViewpager.setAdapter(mMainFragmentPagerAdapter);
        mMainTabs.setViewPager(mMainViewpager);
    }

    /**
     * @des 绑定home按钮和drawer
     */
    private void initActionBarDrawerToggle() {
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mToggle.syncState();
        //给drawerlayout布局设置listener,listener为toggle
        mDrawerLayout.setDrawerListener(mToggle);

    }

    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(R.string.main_title);
//        supportActionBar.setSubtitle(R.string.sub_title);
//        supportActionBar.setIcon(R.drawable.ic_launcher);
        supportActionBar.setLogo(R.drawable.ic_launcher);
        //设置显示logo和icon
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayUseLogoEnabled(true);
        //显示回退部分
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * @param item
     * @return
     * @des 点击home, drawer响应关闭或者打开
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //这里必须为R文件增加前缀android.不然id不能匹配
            case android.R.id.home:
                LogUtils.i("back", "点击回退按钮");
                mToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            BaseFragment baseFragment = (BaseFragment) FragmentFactory.mCacheFragments.get(position);
            //当页面被选中后再触发请求数据,而不是在创建的时候就请求数据
            baseFragment.getLoadingPager().triggerLaodData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
