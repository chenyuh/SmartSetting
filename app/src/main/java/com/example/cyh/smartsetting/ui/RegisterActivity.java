package com.example.cyh.smartsetting.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.MyUser;
import com.example.cyh.smartsetting.utils.UtilTools;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_age;
    private EditText et_desc;
    private RadioGroup mRadioGroup;
    private EditText et_password;
    private EditText et_again;
    private EditText et_email;
    private Button btnRegistered;
    //性别
    private boolean isGender = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

/*  点击空白隐藏键盘
    @Override
    public int[] hideSoftByEditViewIds() {
        int[] ids = {R.id.et_username, R.id.et_age, R.id.et_desc,
                R.id.et_password, R.id.et_again, R.id.et_email};
        return ids;
    }*/

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_age = (EditText) findViewById(R.id.et_age);
        et_desc = (EditText) findViewById(R.id.et_desc);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        et_password = (EditText) findViewById(R.id.et_password);
        et_again = (EditText) findViewById(R.id.et_again);
        et_email = (EditText) findViewById(R.id.et_email);
        btnRegistered = (Button) findViewById(R.id.btn_registered);
        btnRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_registered:
                String userName = et_username.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String again = et_again.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                //判断输入框是否为空
                if (!TextUtils.isEmpty(userName) &
                        !TextUtils.isEmpty(age) &
                        !TextUtils.isEmpty(password) &
                        !TextUtils.isEmpty(again) &
                        !TextUtils.isEmpty(email)) {
                    //判断两次密码是否一致
                    if (password.equals(again)) {
                        if (UtilTools.isEmail(email)) {
                            //判断性别
                            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                                    if (checkedId == R.id.rb_boy) {
                                        isGender = true;
                                    } else if (checkedId == R.id.rb_girl) {
                                        isGender = false;
                                    }
                                }
                            });
                            //判断简介是否为空
                            if (TextUtils.isEmpty(desc)) {
                                desc = getString(R.string.no_thing);
                            }
                            //注册
                            MyUser myUser = new MyUser();
                            myUser.setUsername(userName);
                            myUser.setPassword(password);
                            myUser.setEmail(email);
                            myUser.setAge(Integer.parseInt(age));
                            myUser.setSex(isGender);
                            myUser.setDesc(desc);
                            myUser.signUp(new SaveListener<MyUser>() {
                                @Override
                                public void done(MyUser myUser, BmobException e) {
                                    if(e == null){
                                        Toast.makeText(RegisterActivity.this, R.string.text_registered_successful, Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Toast.makeText(RegisterActivity.this, R.string.text_registered_failure, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            et_email.setText("");
                            Toast.makeText(this, R.string.isemail, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        et_password.setText("");
                        et_again.setText("");
                        Toast.makeText(this, R.string.text_two_input_not_consistent, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, R.string.text_tost_empty, Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
