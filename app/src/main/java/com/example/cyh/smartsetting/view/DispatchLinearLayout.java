package com.example.cyh.smartsetting.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.LinearLayout;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.view
 * 文件名：  DispatchLinearLayout
 * 创建者：  CYH
 * 创建时间：2017/5/28 17:03
 * 描述：    TODO
 */

public class DispatchLinearLayout extends LinearLayout {

    private DispatchKeyEventListener mDispatchKeyEventListener;

    public DispatchLinearLayout(Context context) {
        super(context);
    }

    public DispatchLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static interface DispatchKeyEventListener {
        boolean dispatchKeyEvent(KeyEvent event);
    }

    public DispatchKeyEventListener getDispatchKeyEventListener() {
        return mDispatchKeyEventListener;
    }

    public void setDispatchKeyEventListener(DispatchKeyEventListener dispatchKeyEventListener) {
        mDispatchKeyEventListener = dispatchKeyEventListener;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mDispatchKeyEventListener != null) {
            return mDispatchKeyEventListener.dispatchKeyEvent(event);
        }
        return super.dispatchKeyEvent(event);
    }
}
