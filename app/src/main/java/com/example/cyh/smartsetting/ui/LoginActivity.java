package com.example.cyh.smartsetting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyh.smartsetting.MainActivity;
import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.MyUser;
import com.example.cyh.smartsetting.utils.ShareUtil;
import com.example.cyh.smartsetting.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_login_username;
    private EditText et_login_password;
    private Button btnLogin, btnRegister;
    private CheckBox keep_password;
    private TextView tv_forgetPassword;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        //设置选中状态
        boolean isCheck = ShareUtil.getBoolean(this, "keeppassword", false);
        if (isCheck) {
            keep_password.setChecked(isCheck);
            et_login_username.setText(ShareUtil.getString(this, "name", ""));
            et_login_password.setText(ShareUtil.getString(this, "password", ""));
        }
    }

    private void initView() {
        et_login_username = (EditText) findViewById(R.id.et_login_username);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        tv_forgetPassword = (TextView) findViewById(R.id.tv_forgetPassword);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tv_forgetPassword.setOnClickListener(this);

        customDialog = new CustomDialog(this, 180, 180, R.layout.dialog_layout, R.style.theme_dialog, Gravity.CENTER);
        customDialog.setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String username = et_login_username.getText().toString().trim();
                String password = et_login_password.getText().toString().trim();

                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(password)) {
                    customDialog.show();
                    MyUser myUser = new MyUser();
                    myUser.setUsername(username);
                    myUser.setPassword(password);
                    myUser.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            customDialog.dismiss();
                            if (e == null) {
                                if (myUser.getEmailVerified()) {
                                    MainActivity.actionStart(LoginActivity.this);
                                    finish();
                                    Toast.makeText(LoginActivity.this, R.string.login_successful, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, R.string.emailverified, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                switch (e.getErrorCode()) {
                                    case 101:
                                        Toast.makeText(LoginActivity.this, getString(R.string.login_fail) + "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 9010:
                                        Toast.makeText(LoginActivity.this, getString(R.string.login_fail) + "网络超时", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 9016:
                                        Toast.makeText(LoginActivity.this, getString(R.string.login_fail) + "无网络连接，请检查您的手机网络", Toast.LENGTH_SHORT).show();
                                        break;
                                }

                            }
                        }
                    });
                } else {
                    Toast.makeText(this, R.string.text_tost_empty, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_forgetPassword:
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存状态
        ShareUtil.putBoolean(this, "keeppassword", keep_password.isChecked());
        //是否记住密码
        if (keep_password.isChecked()) {
            ShareUtil.putString(this, "name", et_login_username.getText().toString().trim());
            ShareUtil.putString(this, "password", et_login_password.getText().toString().trim());
        } else {
            ShareUtil.deleteValue(this, "name");
            ShareUtil.deleteValue(this, "password");
        }
    }
}
