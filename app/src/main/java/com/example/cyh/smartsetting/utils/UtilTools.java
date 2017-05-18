package com.example.cyh.smartsetting.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cyh.smartsetting.entity.StaticClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    //匹配邮箱
    public static boolean isEmail(String string) {
        Pattern r = Pattern.compile(StaticClass.PATTERN_EMAIL);
        Matcher m = r.matcher(string);
        return m.matches();
    }

    public static void putImageToShare(Context context, ImageView imageView) {
        //将图片转换为位图
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        //将Bitmap压缩成字节数组输出流
        ByteArrayOutputStream byStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byStream);
        //利用Base64将我们的字节数组输出流转换成String
        byte[] byteArray = byStream.toByteArray();
        String imageString = new String(Base64.encode(byteArray, Base64.DEFAULT));
        //将图片存储到SharePreference
        ShareUtil.putString(context, StaticClass.IMAGE_STRING_NAME, imageString);
    }

    public static void getImageFromShare(Context context, ImageView imageView) {
        //获取字符串
        String imageString = ShareUtil.getString(context, StaticClass.IMAGE_STRING_NAME, "");
        if (!imageString.equals("")){
            //利用Base64将我们的string转换为字节数组输出流
            byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
            ByteArrayInputStream byStream = new ByteArrayInputStream(byteArray);
            //将字节数组输出流压缩成bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byStream);
            //设置图片
            imageView.setImageBitmap(bitmap);
        }
    }
}
