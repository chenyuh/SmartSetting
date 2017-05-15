package com.example.cyh.smartsetting.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.MyUser;
import com.example.cyh.smartsetting.utils.L;
import com.example.cyh.smartsetting.utils.UtilTools;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_forget_email;
    private Button btn_forget_password;

    private EditText et_old_password;
    private EditText et_new_password;
    private EditText et_again_password;
    private Button btn_reset_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        initView();
    }

    private void initView() {
        et_forget_email = (EditText) findViewById(R.id.et_forget_email);
        btn_forget_password = (Button) findViewById(R.id.btn_forget_password);
        btn_forget_password.setOnClickListener(this);

        et_old_password = (EditText) findViewById(R.id.et_old_password);
        et_new_password = (EditText) findViewById(R.id.et_new_password);
        et_again_password = (EditText) findViewById(R.id.et_again_password);
        btn_reset_password = (Button) findViewById(R.id.btn_reset_password);
        btn_reset_password.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_password:
                String email = et_forget_email.getText().toString().trim();
                L.d(email);
                if (!TextUtils.isEmpty(email)) {
                    if (UtilTools.isEmail(email)) {
                        MyUser.resetPasswordByEmail(email, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(ForgetPasswordActivity.this, R.string.email_reset_password_successful, Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(ForgetPasswordActivity.this, R.string.email_reset_password_fail, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        et_forget_email.setText("");
                        Toast.makeText(this, R.string.isemail, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, R.string.text_tost_empty, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_reset_password:
                String oldPassword = et_old_password.getText().toString().trim();
                String newPassword = et_new_password.getText().toString().trim();
                String againPassword = et_again_password.getText().toString().trim();

                if (!TextUtils.isEmpty(oldPassword) & !TextUtils.isEmpty(newPassword) & !TextUtils.isEmpty(againPassword)) {
                    if (newPassword.equals(againPassword)) {
                        MyUser.updateCurrentUserPassword(oldPassword, newPassword, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(ForgetPasswordActivity.this, R.string.reset_successful, Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(ForgetPasswordActivity.this, R.string.email_reset_password_fail, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, R.string.text_tost_empty, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
