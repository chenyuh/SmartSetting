package com.example.cyh.smartsetting.application;

import android.app.Application;
import android.content.Context;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.application
 * 文件名：  BaseApplication
 * 创建者：  CYH
 * 创建时间：2017/5/12 14:51
 * 描述：    Application
 */

public class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
