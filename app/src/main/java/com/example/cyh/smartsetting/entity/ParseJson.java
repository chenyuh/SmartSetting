package com.example.cyh.smartsetting.entity;

import com.example.cyh.smartsetting.utils.L;
import com.google.gson.Gson;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.entity
 * 文件名：  ParseJson
 * 创建者：  CYH
 * 创建时间：2017/5/19 14:27
 * 描述：    Gson解析封装类
 */

public class ParseJson<T> {
    public T getParseJson(String t) throws Exception {
        Gson mGson = new Gson();
        try {
            return (T) mGson.fromJson(t, this.getClass());
        } catch (Exception e) {
            L.d(e + "");
            return null;
        }
    }
}
