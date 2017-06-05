package com.example.cyh.smartsetting.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.fragment
 * 文件名：  BaseFragment
 * 创建者：  CYH
 * 创建时间：2017/6/5 15:47
 * 描述：    TODO
 */

public class BaseFragment extends Fragment {

    /**
     * 传入EditText的Id
     * 没有传入的EditText不做处理
     *
     * @return id 数组
     */
    public int[] hideSoftByEditViewIds() {
        return null;
    }

    /**
     * 传入要过滤的View
     * 过滤之后点击将不会有隐藏软键盘的操作
     *
     * @return id 数组
     */
    public View[] filterViewByIds() {
        return null;
    }
}
