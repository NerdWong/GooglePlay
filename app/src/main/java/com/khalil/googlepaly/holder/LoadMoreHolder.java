package com.khalil.googlepaly.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khalil.googlepaly.R;
import com.khalil.googlepaly.base.BaseHolder;
import com.khalil.googlepaly.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Khalil on 2017/7/14.
 */

public class LoadMoreHolder extends BaseHolder<Integer> {
    //------->>>>根据整数值来决定应该创建的view类型,即决定frameLayout中各view 的显示和隐藏--------★★★
    public static final int LOADMORE_LOADING = 0;//正在加载更多
    public static final int LOADMORE_ERROR = 1;//加载更多失败,点击重试
    public static final int LOADMORE_NONE = 2;//没有加载更多

    @BindView(R.id.item_loadmore_container_loading)
    LinearLayout mItemLoadmoreContainerLoading;
    @BindView(R.id.item_loadmore_tv_retry)
    TextView mItemLoadmoreTvRetry;
    @BindView(R.id.item_loadmore_container_retry)
    LinearLayout mItemLoadmoreContainerRetry;

    @Override
    public View initHolderView() {
        View holderView = View.inflate(UIUtils.getContext(), R.layout.item_loadmore, null);
        ButterKnife.bind(this,holderView);
        return holderView;
    }

    @Override
    public void refreshHolderView(Integer curState) {
        //首先隐藏所有的视图
        mItemLoadmoreContainerLoading.setVisibility(View.GONE);
        mItemLoadmoreContainerRetry.setVisibility(View.GONE);
        switch (curState) {
            case LOADMORE_LOADING:
                mItemLoadmoreContainerLoading.setVisibility(View.VISIBLE);
                break;
            case LOADMORE_ERROR:
                mItemLoadmoreContainerRetry.setVisibility(View.VISIBLE);
                break;
            case LOADMORE_NONE:
                break;
            default:
                break;
        }
    }
}
