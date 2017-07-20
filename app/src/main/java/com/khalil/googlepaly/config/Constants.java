package com.khalil.googlepaly.config;

import com.khalil.googlepaly.utils.LogUtils;

/**
 * Created by Khalil on 2017/7/8.
 */

public class Constants {

    public static final int	DEBUGLEVEL = LogUtils.LEVEL_ALL;
    //表示显示所有的日志输出，包括System.out；

//    public static final int DEBUGLEVEL = LogUtils.LEVEL_OFF;//关闭
public static final long PROTOCOLTIMEOUT = 5 * 60 * 1000;//5分钟

    public static final class URLS {
        public static final String BASEURL = "http://10.0.2.2/GooglePlayServer/";
        public static final String IMGBASEURL = BASEURL + "image?name=";
    }

    public static final class REQ {

    }

    public static final class RES {

    }

    public static final class PAY {
        public static final int PAYTYPE_ZHIFUBAO = 0;
        public static final int PAYTYPE_WEIXIN = 1;
    }


}
