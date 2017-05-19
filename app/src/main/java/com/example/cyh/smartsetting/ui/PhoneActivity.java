package com.example.cyh.smartsetting.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.PhoneResult;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.utils.L;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_phone;
    private ImageView iv_logo;
    private TextView tv_result;
    private Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_del, btn_phone_query;

    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initView();
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_phone.setSelection(et_phone.getText().length());

        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        tv_result = (TextView) findViewById(R.id.tv_result);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_0.setOnClickListener(this);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_del.setOnClickListener(this);
        btn_phone_query = (Button) findViewById(R.id.btn_phone_query);
        btn_phone_query.setOnClickListener(this);

        btn_del.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                et_phone.setText("");
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if (flag) {
                    flag = false;
                    et_phone.setText("");
                }
                StringBuffer str = new StringBuffer(et_phone.getText().toString().trim());
                et_phone.setText(str.append(((Button) view).getText().toString().trim()).toString());
                et_phone.setSelection(et_phone.getText().length());
                break;
            case R.id.btn_del:
                StringBuffer num = new StringBuffer(et_phone.getText().toString().trim());
                if (!TextUtils.isEmpty(num) && num.length() > 0) {
                    et_phone.setText(num.deleteCharAt(num.length() - 1));
                    et_phone.setSelection(num.length());
                }
                break;
            case R.id.btn_phone_query:
                String phone = et_phone.getText().toString().trim();
                Pattern mPattern = Pattern.compile(StaticClass.PATTERN_PHONE);
                Matcher m = mPattern.matcher(phone);
                String url = "http://apis.juhe.cn/mobile/get?phone=" + phone + "&key=" + StaticClass.PHONE_APP_KEY;
                if (!TextUtils.isEmpty(phone) && m.matches()) {
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            try {
                                PhoneResult phone = new PhoneResult().getParseJson(t);
                                L.d(phone.getResultcode());

                                if (phone.getResultcode().equals("200")) {
                                    String province = phone.getResult().getProvince();
                                    String city = phone.getResult().getCity();
                                    String areacode = phone.getResult().getAreacode();
                                    String zip = phone.getResult().getZip();
                                    String company = phone.getResult().getCompany();

                                    tv_result.setText("归属地:" + province + city + "\n"
                                            + "区号:" + areacode + "\n"
                                            + "邮编:" + zip + "\n"
                                            + "运营商:" + company);

                                    //图片显示
                                    switch (company) {
                                        case "移动":
                                            iv_logo.setImageResource(R.drawable.china_mobile);
                                            break;
                                        case "联通":
                                            iv_logo.setImageResource(R.drawable.china_unicom);
                                            break;
                                        case "电信":
                                            iv_logo.setImageResource(R.drawable.china_telecom);
                                            break;
                                    }
                                    flag = true;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, R.string.phone_fail, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
