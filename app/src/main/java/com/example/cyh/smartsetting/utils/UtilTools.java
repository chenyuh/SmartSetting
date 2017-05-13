package com.example.cyh.smartsetting.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.utils
 * 文件名：  UtilTools
 * 创建者：  CYH
 * 创建时间：2017/5/12 15:22
 * 描述：    工具类
 */

public class UtilTools {
    //设置字体
    public static void setFont(Context context, TextView textView) {
        Typeface fontType = Typeface.createFromAsset(context.getAssets(), "fonts/FONT.TTF");
        textView.setTypeface(fontType);
    }
}
