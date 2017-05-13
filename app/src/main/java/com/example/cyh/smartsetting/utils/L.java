package com.example.cyh.smartsetting.utils;

import android.util.Log;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.utils
 * 文件名：  L
 * 创建者：  CYH
 * 创建时间：2017/5/13 15:20
 * 描述：    Log工具类
 */

public class L {
    private static final boolean DEBUG = true;

    private static final String TAG = "SmartSetting";

    public static void v(String msg) {
        if (DEBUG) {
            Log.v(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }
}
