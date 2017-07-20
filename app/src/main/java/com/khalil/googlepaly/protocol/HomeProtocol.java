package com.khalil.googlepaly.protocol;


import com.google.gson.Gson;
import com.khalil.googlepaly.base.BaseProtocol;
import com.khalil.googlepaly.bean.HomeBean;

/**
 * 负责对HomeFragment里面涉及到的网络请求进行封装
 */
public class HomeProtocol extends BaseProtocol<HomeBean> {
    /**
     * 决定协议的关键字
     */
    @Override
    public String getInterfaceKey() {
        return "home";
    }

    /**
     * 完成网络请求回来jsonString的解析
     * @param resJsonString
     * @return
     */
    @Override
    protected HomeBean parseJson(String resJsonString) {
        Gson gson = new Gson();
        return gson.fromJson(resJsonString,HomeBean.class);
    }
}
