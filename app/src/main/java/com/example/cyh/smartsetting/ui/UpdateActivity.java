package com.example.cyh.smartsetting.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.utils.L;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.ProgressListener;
import com.kymjs.rxvolley.http.VolleyError;
import com.kymjs.rxvolley.toolbox.FileUtils;

import java.io.File;

public class UpdateActivity extends BaseActivity {

    private TextView tv_size;
    private NumberProgressBar number_progress_bar;

    private String path;

    public static final String TRANSFERREDBYTES = "transferredBytes";
    public static final String TOTALSIZE = "totalSize";
    private long mTransferredBytes;
    private long mTotalSize;

    //界面跳转
    public static void actionStart(Context context, String url, String apkName) {
        Intent intent = new Intent(context, UpdateActivity.class);
        intent.putExtra(StaticClass.UPDATE_URL, url);
        intent.putExtra(StaticClass.APK_NAME, apkName);
        context.startActivity(intent);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case StaticClass.UPDATE_SUCCESS:
                    tv_size.setText(R.string.download_success);
                    startInstallApk();
                    break;
                case StaticClass.UPDATE_FAIL:
                    tv_size.setText(R.string.download_fail);
                    break;
                case StaticClass.UPDATE_LOADING:
                    Bundle bundle = msg.getData();
                    mTransferredBytes = bundle.getLong(TRANSFERREDBYTES);
                    mTotalSize = bundle.getLong(TOTALSIZE);
                    number_progress_bar.setProgress((int) (((float) mTransferredBytes / (float) mTotalSize) * 100));
                    tv_size.setText(mTransferredBytes + "/" + mTotalSize);
                    break;
                default:
                    break;
            }
        }
    };

    private void startInstallApk() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        startActivity(intent);
        finish();
    }

    //添加通知
    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.i(mTransferredBytes + "/" + mTotalSize);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initView();
    }

    private void initView() {

        tv_size = (TextView) findViewById(R.id.tv_size);
        number_progress_bar = (NumberProgressBar) findViewById(R.id.number_progress_bar);

        Intent intent = getIntent();
        String apkName = intent.getStringExtra(StaticClass.APK_NAME);
        String url = intent.getStringExtra(StaticClass.UPDATE_URL);

        path = FileUtils.getSDCardPath() + "/" + apkName;

        if (!TextUtils.isEmpty(url)) {
            RxVolley.download(path, url, new ProgressListener() {
                @Override
                public void onProgress(long transferredBytes, long totalSize) {
                    Message message = new Message();
                    message.what = StaticClass.UPDATE_LOADING;
                    Bundle bundle = new Bundle();
                    bundle.putLong(TRANSFERREDBYTES, transferredBytes);
                    bundle.putLong(TOTALSIZE, totalSize);
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                }
            }, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    L.i(getString(R.string.download_success));
                    mHandler.sendEmptyMessage(StaticClass.UPDATE_SUCCESS);
                }

                @Override
                public void onFailure(VolleyError error) {
                    L.i(getString(R.string.download_fail));
                    mHandler.sendEmptyMessage(StaticClass.UPDATE_FAIL);
                }
            });
        }
    }
}
