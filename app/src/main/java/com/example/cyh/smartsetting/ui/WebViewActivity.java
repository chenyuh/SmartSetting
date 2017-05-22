package com.example.cyh.smartsetting.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.utils.L;

import java.util.List;

public class WebViewActivity extends BaseActivity {

    private ProgressBar progess_bar;
    private WebView mWebView;
    private String url;

    public static void actionStart(Context context, List<String> mListTitle,
                                   List<String> mListUrl, int position) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(StaticClass.WECHAT_TITLE, mListTitle.get(position));
        intent.putExtra(StaticClass.WECHAT_URL, mListUrl.get(position));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
    }

    private void initView() {

        progess_bar = (ProgressBar) findViewById(R.id.progress_bar);
        mWebView = (WebView) findViewById(R.id.mWebView);

        Intent intent = getIntent();
        String title = intent.getStringExtra(StaticClass.WECHAT_TITLE);
        url = intent.getStringExtra(StaticClass.WECHAT_URL);
        L.i(url);

        getSupportActionBar().setTitle(title);
        //支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        //接口回调
        mWebView.setWebChromeClient(new MyWebViewClient());
        //本地加载
        mWebView.setWebViewClient(new WebViewClient());
        //加载网页
        mWebView.loadUrl(url);
    }

    public class MyWebViewClient extends WebChromeClient{
        //加载进度
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                progess_bar.setVisibility(View.GONE);
            } else {
                progess_bar.setProgress(newProgress);
            }
        }
    }
}
