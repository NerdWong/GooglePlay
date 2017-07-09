package com.khalil.googlepaly.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.khalil.googlepaly.R;
import com.khalil.googlepaly.fragment.FragmentFactory;
import com.khalil.googlepaly.utils.LogUtils;
import com.khalil.googlepaly.utils.UIUtils;

/**
 * Created by Khalil on 2017/7/9.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private final String[] mMainTitles;

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mMainTitles = UIUtils.getStrings(R.array.main_titles);
    }

    @Override
    public Fragment getItem(int position) {
        LogUtils.i("初始化"+mMainTitles[position]);
        Fragment fragment = FragmentFactory.createFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        if (mMainTitles != null) {

            return mMainTitles.length;
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMainTitles[position];
    }
}
