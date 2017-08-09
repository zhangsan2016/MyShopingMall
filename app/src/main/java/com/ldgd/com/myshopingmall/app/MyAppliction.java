package com.ldgd.com.myshopingmall.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ldgd on 2017/7/28.
 */

public class MyAppliction extends Application {
    private Context mContext;



    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        initOkhttpUtil();

    }

    /**
     * 初始化OkHttpUtil
     */
    private void initOkhttpUtil() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public Context getmContext() {
        return mContext;
    }
}
