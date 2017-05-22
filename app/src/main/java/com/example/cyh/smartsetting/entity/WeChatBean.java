package com.example.cyh.smartsetting.entity;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.entity
 * 文件名：  WeChatBean
 * 创建者：  CYH
 * 创建时间：2017/5/21 20:54
 * 描述：    微信精选数据类
 */

public class WeChatBean {

    private String title;
    private String source;
    private String firstImg;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
