package com.example.cyh.smartsetting.entity;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.entity
 * 文件名：  StaticClass
 * 创建者：  CYH
 * 创建时间：2017/5/12 15:23
 * 描述：    数据  常量
 */

public class StaticClass {
    //闪屏页延时
    public static final int SPLASH_DELAY = 1001;
    //是否第一次启动app
    public static final String ISFIRST = "isFirst";
    //Bugly的APPID
    public static final String BUGLY_APP_ID = "ee4a61e734";
    //Bmob的APPID
    public static final String BMOB_APP_ID = "69304601af6b1835f911f1bed731a637";
    //匹配是否为邮箱的正则表达式
    public static final String PATTERN_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    //跳转相册请求码
    public static final int IMAGE_REQUEST_CODE = 1002;
    //拍照后的图片名称
    public static final String PHOTO_IMAGE_FILE_NAME = "fileImag.jpg";
    //跳转拍照请求码
    public static final int CAMERA_REQUEST_CODE = 1003;
    //裁剪请求码
    public static final int CROP_REQUEST_CODE = 1004;
    //图片字符串
    public static final String IMAGE_STRING_NAME = "image_string_name";
}
