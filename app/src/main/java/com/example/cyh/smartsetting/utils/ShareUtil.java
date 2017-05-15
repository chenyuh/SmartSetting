package com.example.cyh.smartsetting.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.utils
 * 文件名：  ShareUtil
 * 创建者：  CYH
 * 创建时间：2017/5/13 15:38
 * 描述：    SharedPreferences工具类
 */

public class ShareUtil {

    private static final String NAME = "config";

    //存数据
    public static void putString(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value).commit();
    }

    //取数据
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    //存数据
    public static void putInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value).commit();
    }

    //取数据
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    //存数据
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value).commit();
    }

    //取数据
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    //删除单个数据
    public static void deleteValue(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        editor.remove(key).commit();
    }

    //删除所有数据
    public static void deleteAll(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        editor.clear().commit();
    }
}
