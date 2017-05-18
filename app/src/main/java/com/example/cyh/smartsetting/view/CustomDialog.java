package com.example.cyh.smartsetting.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.cyh.smartsetting.R;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.view
 * 文件名：  CustomDialog
 * 创建者：  CYH
 * 创建时间：2017/5/16 14:45
 * 描述：    自定义Dialog
 */

public class CustomDialog extends Dialog {
    public CustomDialog(@NonNull Context context, int layout, int style) {
        this(context, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, layout, style, Gravity.CENTER, R.style.pop_anim_style);
    }

    public CustomDialog(Context context, int width, int height, int layout, int style, int gtavity, int anim) {
        super(context, style);
        setContentView(layout);
        Window win = getWindow();
        WindowManager.LayoutParams layoutParams = win.getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.gravity = gtavity;
        win.setAttributes(layoutParams);
        win.setWindowAnimations(anim);
    }

    public CustomDialog(Context context, int width, int height, int layout, int style, int gtavity) {
        this(context, width, height, layout, style, gtavity, R.style.pop_anim_style);
    }
}
