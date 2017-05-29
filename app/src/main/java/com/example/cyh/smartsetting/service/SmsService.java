package com.example.cyh.smartsetting.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.gsm.SmsMessage;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.application.BaseApplication;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.utils.L;
import com.example.cyh.smartsetting.view.DispatchLinearLayout;

public class SmsService extends Service implements View.OnClickListener {
    //短信广播
    private SmsReceiver mSmsReceiver;

    //发信人和发信内容
    private String smsPhone;
    private String smsContent;

    //宽口管理器
    private WindowManager wm;
    //布局参数
    private WindowManager.LayoutParams mLayoutParams;
    //View
    private DispatchLinearLayout mView;

    private TextView tv_sms_phone;
    private TextView tv_sms_content;
    private Button btn_sms_reply;
    private Button btn_sms_cancel;

    //Home键广播
    private HomeWatchReceiver mHomeWatchReceiver;

    public SmsService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.i("服务开启");
        init();
    }

    private void init() {
        IntentFilter smsFilter = new IntentFilter();
        smsFilter.addAction(StaticClass.MESSAGE_ACTION);
        smsFilter.setPriority(Integer.MAX_VALUE);
        mSmsReceiver = new SmsReceiver();
        registerReceiver(mSmsReceiver, smsFilter);

        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        mHomeWatchReceiver = new HomeWatchReceiver();
        registerReceiver(mHomeWatchReceiver, homeFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.i("服务结束");
        if (mSmsReceiver != null) {
            unregisterReceiver(mSmsReceiver);
            mSmsReceiver = null;
            L.i("关闭广播");
        }
        if (mHomeWatchReceiver != null) {
            unregisterReceiver(mHomeWatchReceiver);
            mHomeWatchReceiver = null;
            L.i("关闭广播");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class SmsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(StaticClass.MESSAGE_ACTION)) {
                L.i("短信来了");
                //获取短信内容
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    for (int i = 0; i < pdus.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }
                    for (SmsMessage message : messages) {
                        smsPhone = message.getDisplayOriginatingAddress();
                        smsContent = message.getDisplayMessageBody();
                        showWindow();
                    }
                    L.i(smsContent);
                }

                L.i("结束了");
            }
        }
    }

    //Home广播
    class HomeWatchReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(StaticClass.SYSTEM_DIALOGS_RESON_KEY);
                if (reason != null) {
                    if (reason.equals(StaticClass.SYSTEM_DIALOGS_HOME_KEY)
                            || reason.equals(StaticClass.SYSTEM_DIALOG_REASON_RECENT_APPS)) {
                        if (mView.getParent() != null) {
                            wm.removeView(mView);
                        }
                    }
                }
            }
        }
    }

    //定义提示框
    private void showWindow() {
        //获取系统服务
        wm = (WindowManager) BaseApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        //定义宽高
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        //定义标记
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
        //定义格式
        mLayoutParams.format = PixelFormat.TRANSLUCENT;
        //定义类型
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //加载布局
        mView = (DispatchLinearLayout) View.inflate(BaseApplication.getContext(), R.layout.sms_item, null);
        //findview控件
        tv_sms_phone = (TextView) mView.findViewById(R.id.tv_sms_phone);
        tv_sms_content = (TextView) mView.findViewById(R.id.tv_sms_content);
        btn_sms_reply = (Button) mView.findViewById(R.id.btn_sms_reply);
        btn_sms_cancel = (Button) mView.findViewById(R.id.btn_sms_cancel);
        //设置数据
        tv_sms_phone.setText("发信人：" + smsPhone);
        tv_sms_content.setText("    " + smsContent);
        btn_sms_reply.setOnClickListener(this);
        btn_sms_cancel.setOnClickListener(this);

        wm.addView(mView, mLayoutParams);

        mView.setDispatchKeyEventListener(mDispatchKeyEventListener);
    }
    //接口实现
    private DispatchLinearLayout.DispatchKeyEventListener mDispatchKeyEventListener
            = new DispatchLinearLayout.DispatchKeyEventListener() {
        @Override
        public boolean dispatchKeyEvent(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                if (mView.getParent() != null) {
                    wm.removeView(mView);
                }
                return true;
            }
            return false;
        }
    };

    //回复短信
    private void sendSms() {
        Uri uri = Uri.parse("smsto:" + smsPhone);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        //设置启动模式
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("sms_body", "");
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        	case R.id.btn_sms_reply:
        	    L.i("跳转至发送短信界面");
                sendSms();
                if (mView.getParent() != null) {
                    wm.removeView(mView);
                }
        		break;
            case R.id.btn_sms_cancel:
                if (mView.getParent() != null) {
                    wm.removeView(mView);
                }
                break;

        	default:
        		break;
        }
    }
}
