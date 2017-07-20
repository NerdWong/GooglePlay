package com.khalil.googlepaly.base;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Khalil on 2017/7/11.
 */

public abstract class MyBaseAdapter<RESULTBEAN> extends BaseAdapter {
    private List<RESULTBEAN> mDataSets;
//通过构造方法将数据list一级一级传递进来
    public MyBaseAdapter(List<RESULTBEAN> dataSets) {
        mDataSets = dataSets;
    }

    @Override
    public int getCount() {
        return mDataSets.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
