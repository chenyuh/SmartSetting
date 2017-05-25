package com.example.cyh.smartsetting.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.utils.L;

public class SmsService extends Service {

    private SmsReceiver mSmsReceiver;

    private String smsPhone;
    private String smsContent;

    public SmsService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.i("服务开启");
        init();
    }

    private void init() {
        L.i("2");
        IntentFilter filter = new IntentFilter();
        filter.addAction(StaticClass.MESSAGE_ACTION);
        filter.setPriority(Integer.MAX_VALUE);
        mSmsReceiver = new SmsReceiver();
        registerReceiver(mSmsReceiver, filter);
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
                /*try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Uri uri = Uri.parse("content://sms/inbox");
                ContentResolver smsResolver = BaseApplication.getContext().getContentResolver();
                Cursor idCursor = smsResolver.query(uri, new String[] {"count(_id)"}, null, null, null);
                idCursor.moveToNext();
                int id = idCursor.getInt(0);
                idCursor.close();

                Cursor smsCursor = smsResolver.query(uri, new String[] {"address", "body"}, "_id = ?", new String[] {"" + id}, null);
                if (smsCursor != null) {
                    smsCursor.moveToNext();
                    smsPhone = smsCursor.getString(0);
                    smsContent = smsCursor.getString(1);
                    L.i(smsPhone + ":" + smsContent);
                }*/
            }
        }
    }
}
