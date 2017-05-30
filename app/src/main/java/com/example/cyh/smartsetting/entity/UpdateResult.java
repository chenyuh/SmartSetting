package com.example.cyh.smartsetting.entity;

import com.example.cyh.smartsetting.utils.ParseJson;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.entity
 * 文件名：  UpdateResult
 * 创建者：  CYH
 * 创建时间：2017/5/29 23:05
 * 描述：    更新数据
 */

public class UpdateResult extends ParseJson<UpdateResult> {
    /**
     * versionName : 2.0
     * versionCode : 2
     * content : 修复多项bug
     * url : http://192.168.31.238:8080/cyh/eye.apk
     */

    private String versionName;
    private int versionCode;
    private String apkName;

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    private String content;
    private String url;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
