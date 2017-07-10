package com.khalil.googlepaly.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 创建fragment的工厂类
 * Created by Khalil on 2017/7/9.
 */

public class FragmentFactory {
    public static final int FRAGMENT_HOME = 0;//首页
    public static final int FRAGMENT_APP = 1;//应用
    public static final int FRAGMENT_GAME = 2;//游戏
    public static final int FRAGMENT_SUBJECT = 3;//专题
    public static final int FRAGMENT_RECOMMEND = 4;//推荐
    public static final int FRAGMENT_CATEGORY = 5;//分类
    public static final int FRAGMENT_HOT = 6;//排行

    //创建缓存fragnents的list
    public static Map<Integer, Fragment> mCacheFragments = new HashMap<>();

    public static Fragment createFragment(int position) {
        Fragment fragment = null;
        if (mCacheFragments.get(position) != null) {
            fragment = mCacheFragments.get(position);
            return fragment;
        }
        switch (position) {
            case FRAGMENT_HOME:
                fragment = new Homefragment();
                break;
            case FRAGMENT_APP://返回 应用 对应的fragment
                fragment = new AppFragment();
                break;
            case FRAGMENT_GAME://返回 游戏 对应的fragment
                fragment = new GameFragment();
                break;
            case FRAGMENT_SUBJECT://返回 专题 对应的fragment
                fragment = new SubjectFragment();
                break;
            case FRAGMENT_RECOMMEND://返回 推荐 对应的fragment
                fragment = new RecommendFragment();
                break;
            case FRAGMENT_CATEGORY://返回 分类 对应的fragment
                fragment = new CategoryFragment();
                break;
            case FRAGMENT_HOT://返回 排行 对应的fragment
                fragment = new HotFragment();
                break;
        }
        mCacheFragments.put(position,fragment);
        return fragment;
    }
}
