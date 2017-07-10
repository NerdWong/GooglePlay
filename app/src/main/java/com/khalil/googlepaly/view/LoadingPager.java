package com.khalil.googlepaly.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import com.khalil.googlepaly.R;
import com.khalil.googlepaly.base.MyApplication;
import com.khalil.googlepaly.utils.UIUtils;

/**
 * @description 装载了view和model交互逻辑的controller, 继承framelayout,
 * 在basefragment的oncreateView方法中实例化,返回包含了交互逻辑的布局view
 * <p>
 * Created by Khalil on 2017/7/10.
 */

public abstract class LoadingPager extends FrameLayout {
    //★★★-------- 因包含了view和model的交互逻辑,所以持有两者的引用--------★★★
    //4种状态,对应4中视图
    public static final int STATE_LOADING = 0;//加载中
    public static final int STATE_ERROR = 1;//错误
    public static final int STATE_SUCCESS = 2;//成功
    public static final int STATE_EMPTY = 3;//空

    public int mCurState = STATE_LOADING;//默认是加载中

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    //constructor
    public LoadingPager(@NonNull Context context) {
        super(context);
        //初始化3个初始view
        initCommonView();
    }

    /**
     * @des 初始化常规视图(加载中视图, 错误视图, 空视图3个静态视图)
     * @called LoadingPager的构造方法中调用
     */
    private void initCommonView() {
        //加载中
        mLoadingView = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
        this.addView(mLoadingView);

        //错误视图
        mErrorView = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
        this.addView(mErrorView);

        //空视图
        mEmptyView = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
        this.addView(mEmptyView);

        //根据当前的页面状态手动刷新UI,控制当前应该显示的view
        refreshViewByState();

    }

    /**
     * @ description 根据当前的页面状态手动刷新UI,控制当前应该显示的view
     * @ called 在LoadingPager的构造方法中调用,在3个view被创建后
     */
    private void refreshViewByState() {
        //------->>>>这里不显示的直接gone掉--------★★★
        //控制 加载中视图 显示隐藏
        if (mCurState == STATE_LOADING) {
            mLoadingView.setVisibility(View.VISIBLE);
        } else {
            mLoadingView.setVisibility(View.GONE);
        }

        //控制 错误视图 显示隐藏
        if (mCurState == STATE_ERROR) {
            mErrorView.setVisibility(View.VISIBLE);
        } else {
            mErrorView.setVisibility(View.GONE);
        }

        //控制 空视图 显示隐藏
        if (mCurState == STATE_EMPTY) {
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
        }

        //这里就可能有成功视图了.因为数据已经加载完成了.而且数据加载成功了
        //------->>>>这里的successview是否有,取决于外部是否已经触发了请求数据的方法--------★★★
        if (mSuccessView == null && mCurState == STATE_SUCCESS) {
            mSuccessView = initSuccessView();
            this.addView(mSuccessView);
        }

        // 控制 成功视图的 显示隐藏
        if (mSuccessView != null) {
            if (mCurState == STATE_SUCCESS) {
                mSuccessView.setVisibility(View.VISIBLE);
            } else {
                mSuccessView.setVisibility(View.GONE);
            }
        }

    }

    /**
     * @return
     * @des 决定成功视图长什么样子(需要定义成功视图)
     * @des 数据和视图的绑定过程
     * @des 在LoadingPager中, 其实不知道成功视图具体长什么样子, 更加不知道视图和数据如何绑定, 交给子类, 必须实现
     * @des 必须实现, 但是不知道具体实现, 定义成为抽象方法, 交给子类具体实现
     * @called triggerLoadData()方法被调用, 而且数据加载完成了, 而且数据加载成功
     */

    public abstract View initSuccessView();

    /**
     * @des 触发加载数据的方法
     * @called 外界想让LoadingPager触发加载数据的时候调用
     */
    public void triggerLaodData() {
        //这个runable中包含了子类已经实现的initData()和initSuccessView()方法
        new Thread(new LoadDataTask()).start();
    }

    class LoadDataTask implements Runnable {

        @Override
        public void run() {
            //------->>>>因为这里是子线程,所以code是顺序执行的--------★★★
            LoadingResult loadingResult = initData();
            if (loadingResult != null) {

                mCurState= loadingResult.getState();
            }
            //请求成功后刷新UI
            //在UI线程刷新啊啊啊啊啊
            MyApplication.getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    refreshViewByState();
                }
            });

        }
    }

    /**
     * @des 在子线程中真正的加载具体的数据
     * @des 在LoadingPager中, 不知道如何具体加载数据, 交给子类,子类必须实现
     * @des 必须实现, 但是不知道具体实现, 定义成为抽象方法, 交给子类具体实现
     * @called triggerLoadData()方法被调用的时候
     */
    protected abstract LoadingPager.LoadingResult initData();

    //------->>>>enum类--------★★★
    public enum LoadingResult {
        SUCCESS(STATE_SUCCESS), ERROR(STATE_ERROR), EMPTY(STATE_EMPTY);
        private int state;
//constructor
        LoadingResult(int state) {
            this.state = state;
        }

        //getter
        public int getState() {
            return state;
        }
    }

}
