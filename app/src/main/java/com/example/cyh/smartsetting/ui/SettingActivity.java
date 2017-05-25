package com.example.cyh.smartsetting.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.service.SmsService;
import com.example.cyh.smartsetting.utils.ShareUtil;

import static com.example.cyh.smartsetting.utils.ShareUtil.getBoolean;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    //语音播报
    private TextView tv_speak_action;
    private Switch sw_speak;
    //短信提醒
    private TextView tv_message;
    private Switch sw_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        tv_speak_action = (TextView) findViewById(R.id.tv_speak_action);
        sw_speak = (Switch) findViewById(R.id.sw_speak);
        tv_message = (TextView) findViewById(R.id.tv_message);
        sw_message = (Switch) findViewById(R.id.sw_message);

        sw_speak.setOnClickListener(this);
        sw_message.setOnClickListener(this);

        //初始化语音播报
        initSwitch(this,
                StaticClass.SPEAK,
                sw_speak,
                false,
                tv_speak_action,
                getResources().getString(R.string.start_speak),
                getResources().getString(R.string.stop_speak));

        //初始化短信提醒
        initSwitch(this,
                StaticClass.MESSAGE,
                sw_message,
                false,
                tv_message,
                getResources().getString(R.string.start_message),
                getResources().getString(R.string.stop_message));

        boolean b = getBoolean(this, StaticClass.MESSAGE, true);
        if (b) {
            startService(new Intent(this, SmsService.class));
        }
    }

    private void initSwitch(Context context, String key, Switch mSwitch, boolean defBoolean,
                            TextView textView, String trueContent, String falseContent) {
        boolean b = getBoolean(context, key, defBoolean);
        mSwitch.setChecked(b);
        if (b) {
            textView.setText(trueContent);
        } else {
            textView.setText(falseContent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sw_speak:
                touchSwitch(this,
                        sw_speak,
                        StaticClass.SPEAK,
                        tv_speak_action,
                        getResources().getString(R.string.start_speak),
                        getResources().getString(R.string.stop_speak));
                break;
            case R.id.sw_message:
                touchSwitch(this,
                        sw_message,
                        StaticClass.MESSAGE,
                        tv_message,
                        getResources().getString(R.string.start_message),
                        getResources().getString(R.string.stop_message));
                if (sw_message.isChecked()) {
                    startService(new Intent(this, SmsService.class));
                } else {
                    stopService(new Intent(this, SmsService.class));
                }
                break;
        }
    }

    private void touchSwitch(Context context, Switch mSwitch, String key,
                             TextView textView, String trueContent, String falseContent) {
        mSwitch.setSelected(!mSwitch.isSelected());
        ShareUtil.putBoolean(context, key, mSwitch.isChecked());
        if (mSwitch.isChecked()) {
            textView.setText(trueContent);
        } else {
            textView.setText(falseContent);
        }
    }
}
