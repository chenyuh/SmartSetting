package com.example.cyh.smartsetting.entity;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.entity
 * 文件名：  Msg
 * 创建者：  CYH
 * 创建时间：2017/5/20 14:50
 * 描述：    消息实体类
 */

public class Msg {

    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

}
