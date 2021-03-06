package com.example.cyh.smartsetting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.utils.ShareUtil;

public class SplashActivity extends AppCompatActivity {

    private TextView tvSplash;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.SPLASH_DELAY:
                    if (isFirst()) {
                        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {
        handler.sendEmptyMessageDelayed(StaticClass.SPLASH_DELAY, 1500);
        /*tvSplash = (TextView) findViewById(R.id.tv_splash);
        //设置字体
        UtilTools.setFont(SplashActivity.this, tvSplash);*/
    }

    private boolean isFirst() {
        boolean isFirst = ShareUtil.getBoolean(SplashActivity.this, StaticClass.ISFIRST, true);
        if (isFirst) {
            ShareUtil.putBoolean(SplashActivity.this, StaticClass.ISFIRST, false);
            return true;
        } else {
            return false;
        }
    }

    //禁止使用返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
