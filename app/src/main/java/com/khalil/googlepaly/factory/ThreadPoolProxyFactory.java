package com.khalil.googlepaly.factory;

import com.khalil.googlepaly.proxy.ThreadPoolProxy;

/**
 * 本项目有两种线程池需要创建,
 * 1.普通的size为5的pool
 * 2.下载的size为3的pool
 * Created by Khalil on 2017/7/11.
 */

public class ThreadPoolProxyFactory {
    //普通类型的线程池代理
    static ThreadPoolProxy mNormalThreadPoolProxy;

    //下载类型的线程池代理
    static ThreadPoolProxy mDownLoadThreadPoolProxy;

    /**
     * 得到普通类型的线程池代理
     */
    public static ThreadPoolProxy getNormalThreadPoolProxy() {
        if (mNormalThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mNormalThreadPoolProxy == null) {
                    mNormalThreadPoolProxy = new ThreadPoolProxy(5, 5);
                }
            }
        }
        return mNormalThreadPoolProxy;
    }

    /**
     * 得到下载类型的线程池代理
     */

    public static ThreadPoolProxy getDownLoadThreadPoolProxy() {
        if (mDownLoadThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mDownLoadThreadPoolProxy == null) {
                    mDownLoadThreadPoolProxy = new ThreadPoolProxy(3, 3);
                }
            }
        }
        return mDownLoadThreadPoolProxy;
    }


}
