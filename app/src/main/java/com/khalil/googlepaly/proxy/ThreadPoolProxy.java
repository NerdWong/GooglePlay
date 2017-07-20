package com.khalil.googlepaly.proxy;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**@des 创建线程池的代理类
 * Created by Khalil on 2017/7/11.
 */

public class ThreadPoolProxy {
    ThreadPoolExecutor mExecutor;
    private int mMaxiumPoolSize;
    private int mCorePoolsize;

    public ThreadPoolProxy(int maxiumPoolSize, int corePoolsize) {
        mMaxiumPoolSize = maxiumPoolSize;
        mCorePoolsize = corePoolsize;
    }

    public void initThreadPoolExecutor() {
        //这里用单例模式常见的双重检查机制来防止线程池实现类的多次创建(多线程安全)
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
            //加锁
            synchronized (ThreadPoolProxy.class) {
                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
                    long keepAliveTime = 0;
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
                    mExecutor = new ThreadPoolExecutor(mCorePoolsize, mMaxiumPoolSize,
                            keepAliveTime, unit, workQueue, threadFactory, handler);
                }
            }
        }
    }

    /**
     * 提交任务
     */
    public void submit(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.submit(task);
    }

    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 移除任务
     */
    public void remove(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }


}

