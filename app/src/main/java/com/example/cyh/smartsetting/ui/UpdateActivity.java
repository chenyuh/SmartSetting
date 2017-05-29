package com.example.cyh.smartsetting.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.utils.L;

public class UpdateActivity extends AppCompatActivity {

    //界面跳转
    public static void actionStart(Context context, String url) {
        Intent intent = new Intent(context, UpdateActivity.class);
        intent.putExtra("update_url", url);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        String url = intent.getStringExtra("update_url");
        L.i(url);
    }
}
