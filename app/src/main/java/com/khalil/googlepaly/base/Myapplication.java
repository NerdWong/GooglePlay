package com.khalil.googlepaly.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Process;

import java.util.List;

/**
 * Created by Khalil on 2017/7/8.
 */

public class Myapplication extends Application {

    private static Context mContext;
    private static Handler mMainHandler;
    private static int mMainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        //★★★-------如果在manifest文件中没有给其他组建设置process属性,这个onCreate方法只会执行一次,
        // 为防止其他配置了process的组建初始化时多次执行这个onCreate方法,可以判断一下--------★★★
        if (isInMainProcess()) {

            //上下文
            mContext = getApplicationContext();
            //在这里获得的thread和handler都是主线程的
            //主线程handler
            mMainHandler = new Handler();

            //获取主线程id
            mMainThreadId = Process.myTid();
        }
    }

    //判断是否是当前线程
    private boolean isInMainProcess() {
        String currentPackageName = "";
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            if (packageInfo != null) {
                currentPackageName = packageInfo.packageName;

            }
            //判断当前线程是否为主线程
            int pid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo appProcessInfo : runningAppProcesses
                    ) {
                if (pid==appProcessInfo.pid) {
                    String processName = appProcessInfo.processName;
                    if (processName.equalsIgnoreCase(currentPackageName)) {
                        return  true;
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Context getContext() {
        return mContext;
    }

    public static  Handler getMainHandler() {
        return mMainHandler;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }
}
