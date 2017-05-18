package com.example.cyh.smartsetting.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.adapter.CourierAdapter;
import com.example.cyh.smartsetting.entity.CourierBean;
import com.example.cyh.smartsetting.entity.ResultModel;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourierActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_courier_name;
    private EditText et_courier_number;
    private Button btn_courier_query;
    private ListView lv_courier;
    private List<CourierBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);
        initView();
    }

    private void initView() {
        et_courier_name = (EditText) findViewById(R.id.et_courier_name);
        et_courier_number = (EditText) findViewById(R.id.et_courier_number);
        btn_courier_query = (Button) findViewById(R.id.btn_courier_query);
        btn_courier_query.setOnClickListener(this);
        lv_courier = (ListView) findViewById(R.id.lv_courier);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_courier_query:
                String name = et_courier_name.getText().toString().trim();
                String number = et_courier_number.getText().toString().trim();
                String url = "http://v.juhe.cn/exp/index?key="+ StaticClass.COURIER_APP_KEY +"&com=" + name + "&no=" + number;
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(number)) {
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            parsingJson(t);
                        }
                        @Override
                        public void onFailure(VolleyError error) {
                            Toast.makeText(CourierActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, R.string.text_tost_empty, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //解析json数据
    private void parsingJson(String t) {
        Gson mGson = new Gson();
        ResultModel model = mGson.fromJson(t, ResultModel.class);
        //判断请求是否成功
        if (model.getResultcode().equals("200")) {
            for (int i = 0; i < model.getResult().getList().size(); i++) {
                CourierBean courier = new CourierBean();
                courier.setRemark(model.getResult().getList().get(i).getRemark());
                courier.setDatetime(model.getResult().getList().get(i).getDatetime());
                mList.add(courier);
            }
        }
        //反转List
        Collections.reverse(mList);
        CourierAdapter adapter = new CourierAdapter(CourierActivity.this, mList);
        lv_courier.setAdapter(adapter);
    }
}
