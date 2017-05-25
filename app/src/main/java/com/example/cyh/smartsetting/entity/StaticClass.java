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
    //跳转相册请求码
    public static final int IMAGE_REQUEST_CODE = 1002;
    //跳转拍照请求码
    public static final int CAMERA_REQUEST_CODE = 1003;
    //裁剪请求码
    public static final int CROP_REQUEST_CODE = 1004;
    //信息类型为left
    public static final int TYPE_LEFT = 0;
    //信息类型为right
    public static final int TYPE_RIGHT = 1;
    //是否第一次启动app
    public static final String ISFIRST = "isFirst";
    //语音播报状态
    public static final String SPEAK = "isSpeak";
    //短信提醒状态
    public static final String MESSAGE = "isMessage";
    //Bugly的APPID
    public static final String BUGLY_APP_ID = "ee4a61e734";
    //Bmob的APPID
    public static final String BMOB_APP_ID = "69304601af6b1835f911f1bed731a637";
    //匹配是否为邮箱的正则表达式
    public static final String PATTERN_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    //匹配是否为手机号的正则表达式
    public static final String PATTERN_PHONE = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";
    //拍照后的图片名称
    public static final String PHOTO_IMAGE_FILE_NAME = "fileImag.jpg";
    //图片字符串
    public static final String IMAGE_STRING_NAME = "image_string_name";
    //快递查询APP KEY
    public static final String COURIER_APP_KEY = "eb1384430105897a7a5b37287d3f3d3e";
    //手机归属地APP KEY
    public static final String PHONE_APP_KEY = "9a9a6f4bdc75e0385dd0066b041002f2";
    //问答机器人
    public static final String CHAT_APP_KEY = "7a48539921338ef90866922b21e25f6d";
    //微信精选key
    public static final String WECHAT_APP_KEY = "78f723dccf85aea324a3cf0daac97f35";
    //微信精选标题
    public static final String WECHAT_TITLE = "title";
    //微信精选url
    public static final String WECHAT_URL = "url";
    //语音APP KEY
    public static final String VOICE_APP_KEY = "5923f696";
    //短信广播action
    public static final String MESSAGE_ACTION = "android.provider.Telephony.SMS_RECEIVED";
}
