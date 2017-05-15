package com.example.cyh.smartsetting.application;

import android.app.Application;
import android.content.Context;

import com.example.cyh.smartsetting.entity.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

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
        //初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
        //初始化Bmob
        Bmob.initialize(getApplicationContext(), StaticClass.BMOB_APP_ID);
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
