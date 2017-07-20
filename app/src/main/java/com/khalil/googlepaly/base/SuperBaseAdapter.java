package com.khalil.googlepaly.base;

import android.view.View;
import android.view.ViewGroup;

import com.khalil.googlepaly.holder.LoadMoreHolder;

import java.util.List;

/**
 * 针对MyBaseAdapter里面的getView方法，在getView方法中引入了BaseHolder这个类
 */
public abstract class SuperBaseAdapter<T> extends MyBaseAdapter {
    private static final int VIEWTYPE_LOADMORE = 1;
    private static final int VIEWTYPE_NORMAL = 0;
    private List<T> mDataSets;
    public SuperBaseAdapter(List<T> dataSets) {
        super(dataSets);
        mDataSets = dataSets;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position== getCount()-1) {
            return VIEWTYPE_LOADMORE;
        }
        return VIEWTYPE_NORMAL;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         /*--------------- 决定根布局(itemView) ---------------*/
        BaseHolder holder = null;
        //获取当前位置的条目类型
        int curViewType  = getItemViewType(position); if (convertView == null) {
            if (curViewType == VIEWTYPE_LOADMORE) {//当前的条目是加载更多类型
                holder = getLoadMoreHolder();
            } else {//当前的条目是普通的类型
                //创建holer对象
                holder = getSpecialBaseHolder();
            }
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        if (curViewType == VIEWTYPE_LOADMORE) {//当前的条目是加载更多类型
            if (hasLoadMore()) {
                //显示正在加载更多的视图
                mLoadMoreHolder.setDataAndRefreshHolderView(LoadMoreHolder.LOADMORE_LOADING);
            } else {
                //隐藏加载更多的视图,以及重试视图
                mLoadMoreHolder.setDataAndRefreshHolderView(LoadMoreHolder.LOADMORE_NONE);
            }
        } else {
            Object data = mDataSets.get(position);
            holder.setDataAndRefreshHolderView(data);
        }

        convertView =  holder.mHolderView;
        return convertView;//其实这个convertView是经过了数据绑定的convertView
    }

    public  boolean hasLoadMore(){
        return false;
    };

    private LoadMoreHolder getLoadMoreHolder() {
        if (mLoadMoreHolder != null) {
            mLoadMoreHolder = new LoadMoreHolder();
        }
        return mLoadMoreHolder;
    }

    /**
     * @return
     * @des 得到BaseHolder具体的子类对象
     * @des 在SuperBaseAdapter中不知道如何创建BaseHolder的子类对象, 所以只能交给子类, 子类必须实现
     * @des 必须实现, 但是不知道具体实现, 定义成为抽象方法, 交给子类具体实现
     */
    public abstract BaseHolder getSpecialBaseHolder();

    @Override
    public int getCount() {
        return super.getCount()+1;
    }

    private LoadMoreHolder mLoadMoreHolder;
}
