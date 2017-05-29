package com.example.cyh.smartsetting.ui;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.entity.UpdateResult;
import com.example.cyh.smartsetting.service.SmsService;
import com.example.cyh.smartsetting.utils.L;
import com.example.cyh.smartsetting.utils.ShareUtil;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import static com.example.cyh.smartsetting.utils.ShareUtil.getBoolean;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    //语音播报
    private TextView tv_speak_action;
    private Switch sw_speak;
    //短信提醒
    private TextView tv_message;
    private Switch sw_message;
    //更新
    private TextView tv_setting_update;
    //版本名称
    private String versionName;
    //版本号
    private int versionCode;
    //更新url
    private String url;

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
            //运行时权限
            //添加窗口权限
            if (ContextCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.RECEIVE_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SettingActivity.this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
            } else {
                startService(new Intent(this, SmsService.class));
            }
        } else {
            stopService(new Intent(this, SmsService.class));
        }

        tv_setting_update = (TextView) findViewById(R.id.tv_setting_update);
        tv_setting_update.setOnClickListener(this);

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
                    if (ContextCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.RECEIVE_SMS)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(SettingActivity.this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
                    } else {
                        startService(new Intent(this, SmsService.class));
                    }
                } else {
                    stopService(new Intent(this, SmsService.class));
                }
                break;
            case R.id.tv_setting_update:
                RxVolley.get(StaticClass.UPDATE_URL, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        L.i(t);
                        try {
                            UpdateResult updateResult = new UpdateResult().getParseJson(t);
                            if (versionCode < updateResult.getVersionCode()) {
                                url = updateResult.getUrl();
                                //弹提示框
                                showUpdateDialog(updateResult.getContent());
                            } else {
                                Toast.makeText(SettingActivity.this, R.string.new_versioncode, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
        }
    }

    private void showUpdateDialog(String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle(R.string.new_version)
                .setMessage(content)
                .setPositiveButton(getString(R.string.update), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UpdateActivity.actionStart(SettingActivity.this, url);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    private void getVersionNameCode() throws PackageManager.NameNotFoundException {
        PackageManager packageManager = getPackageManager();
        PackageInfo info = packageManager.getPackageInfo(getPackageName(), 0);
        versionName = info.versionName;
        versionCode = info.versionCode;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startService(new Intent(this, SmsService.class));
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
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
