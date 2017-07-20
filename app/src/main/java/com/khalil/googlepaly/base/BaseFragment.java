package com.khalil.googlepaly.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khalil.googlepaly.view.LoadingPager;

import java.util.List;
import java.util.Map;

/**
 * Created by Khalil on 2017/7/10.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager mLoadingPager;

    public LoadingPager getLoadingPager() {
        return mLoadingPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoadingPager = new LoadingPager(MyApplication.getContext()) {
            @Override
            public View initSuccessView() {
                return BaseFragment.this.initSuccessView();
            }

            @Override
            protected LoadingResult initData() {
                return BaseFragment.this.initData();
            }
        };
        //------->>>>这里触发以下请求数据,因内部是子线程和handler消息机制post更新UI的,所以可以更新到数据--------★★★
        //------->>>>为了防止viewpager的预加载功能,即还没显示viewpager就会调用fragment的oncreateView方法,所以不在这里调用触发请求数据--------★★★
//        mLoadingPager.triggerLaodData();
        return mLoadingPager;
    }

    public abstract LoadingPager.LoadingResult initData();

    public abstract View initSuccessView();
    /**
     * @des 校验请求回来的数据
     */
    public LoadingPager.LoadingResult checkResult(Object resObj) {
        if (resObj == null) {
            return LoadingPager.LoadingResult.EMPTY;
        }
        //resObj -->List
        if (resObj instanceof List) {
            if (((List) resObj).size() == 0) {
                return LoadingPager.LoadingResult.EMPTY;
            }
        }
        //resObj -->Map
        if (resObj instanceof Map) {
            if (((Map) resObj).size() == 0) {
                return LoadingPager.LoadingResult.EMPTY;
            }
        }
        return LoadingPager.LoadingResult.SUCCESS;
    }
}
