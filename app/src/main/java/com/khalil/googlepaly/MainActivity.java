package com.khalil.googlepaly;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.khalil.googlepaly.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initActionBar();
        initActionBarDrawerToggle();
    }

    private void initActionBarDrawerToggle() {
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open,R.string.close);
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
        supportActionBar.setSubtitle(R.string.sub_title);
        supportActionBar.setIcon(R.drawable.ic_launcher);
        supportActionBar.setLogo(R.drawable.action_download);
        //设置显示logo和icon
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayUseLogoEnabled(true);
        //显示回退部分
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                LogUtils.i("back", "点击回退按钮");
                mToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
