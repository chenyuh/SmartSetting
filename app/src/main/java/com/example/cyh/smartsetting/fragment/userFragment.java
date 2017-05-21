package com.example.cyh.smartsetting.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.MyUser;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.ui.CourierActivity;
import com.example.cyh.smartsetting.ui.LoginActivity;
import com.example.cyh.smartsetting.ui.PhoneActivity;
import com.example.cyh.smartsetting.utils.UtilTools;
import com.example.cyh.smartsetting.view.CustomDialog;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class userFragment extends Fragment implements View.OnClickListener {

    private Context mContext;

    private TextView edit_user;
    private EditText edit_name;
    private EditText edit_sex;
    private EditText edit_age;
    private EditText edit_des;
    private Button btn_update_ok;
    private Button btn_exit_user;

    private CircleImageView profile_image;
    private TextView tv_courier;
    private TextView tv_phone;

    private CustomDialog dialog;
    private Button btn_cancel;
    private Button btn_picture;
    private Button btn_camera;

    private File tempFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        findView(view);
        return view;
    }

    private void findView(View view) {
        btn_exit_user = (Button) view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);
        edit_user = (TextView) view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);

        edit_name = (EditText) view.findViewById(R.id.edit_name);
        edit_sex = (EditText) view.findViewById(R.id.edit_sex);
        edit_age = (EditText) view.findViewById(R.id.edit_age);
        edit_des = (EditText) view.findViewById(R.id.edit_des);
        setEnabled(false);
        //获取当前用户的数据并显示
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        edit_name.setText(userInfo.getUsername());
        edit_sex.setText(userInfo.getSex() ? getString(R.string.boy) : getString(R.string.girl));
        edit_age.setText(userInfo.getAge() + "");
        edit_des.setText(userInfo.getDesc());

        btn_update_ok = (Button) view.findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);

        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);
        //读取图片
        UtilTools.getImageFromShare(getActivity(), profile_image);


        dialog = new CustomDialog(getActivity(), WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, R.layout.menu_dialog_layout, R.style.theme_dialog, Gravity.BOTTOM);
        dialog.setCancelable(false);

        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_picture = (Button) dialog.findViewById(R.id.btn_picture);
        btn_camera = (Button) dialog.findViewById(R.id.btn_camera);
        btn_cancel.setOnClickListener(this);
        btn_picture.setOnClickListener(this);
        btn_camera.setOnClickListener(this);

        tv_courier = (TextView) view.findViewById(R.id.tv_courier);
        tv_courier.setOnClickListener(this);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        tv_phone.setOnClickListener(this);
    }
    //设置输入框是否可编写
    private void setEnabled(boolean b) {
            edit_name.setEnabled(b);
            edit_sex.setEnabled(b);
            edit_age.setEnabled(b);
            edit_des.setEnabled(b);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //退出登录
            case R.id.btn_exit_user:
                MyUser.logOut();
                BmobUser bmobUser = MyUser.getCurrentUser();
                if (bmobUser == null) {
                    Toast.makeText(mContext, R.string.exit_successful, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                } else {
                    Toast.makeText(mContext, R.string.exit_fail, Toast.LENGTH_SHORT).show();
                }
                break;
            //编辑资料
            case R.id.edit_user:
                setEnabled(true);
                btn_update_ok.setVisibility(View.VISIBLE);
                break;
            //确认修改
            case R.id.btn_update_ok:
                //1.拿到输入框的值
                String username = edit_name.getText().toString().trim();
                String age = edit_age.getText().toString().trim();
                String sex = edit_sex.getText().toString().trim();
                String desc = edit_des.getText().toString().trim();
                //更新资料
                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(age) & !TextUtils.isEmpty(sex)) {
                    MyUser myUser = new MyUser();
                    myUser.setUsername(username);
                    myUser.setAge(Integer.parseInt(age));
                    //设置性别
                    if (sex.equals(getString(R.string.boy))) {
                        myUser.setSex(true);
                    } else if (sex.equals(getString(R.string.girl))) {
                        myUser.setSex(false);
                    }
                    //判断简介是否为空
                    if (!TextUtils.isEmpty(desc)) {
                        myUser.setDesc(desc);
                    } else {
                        myUser.setDesc(getString(R.string.no_thing));
                    }

                    BmobUser bmob = BmobUser.getCurrentUser();
                    myUser.update(bmob.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                setEnabled(false);
                                btn_update_ok.setVisibility(View.GONE);
                                Toast.makeText(mContext, R.string.update_successful, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, R.string.update_fail, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            //圆形头像
            case R.id.profile_image:
                dialog.show();
                break;
            //取消
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
            //图库
            case R.id.btn_picture:
                toPicture();
                break;
            //拍照
            case R.id.btn_camera:
                toCamera();
                break;
            //物流查询
            case R.id.tv_courier:
                startActivity(new Intent(getActivity(), CourierActivity.class));
                break;
            //归属地查询
            case R.id.tv_phone:
                startActivity(new Intent(getActivity(), PhoneActivity.class));
                break;
        }
    }
    //跳转至相机
    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用，可用的话进行储存
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), StaticClass.PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent, StaticClass.CAMERA_REQUEST_CODE);
        dialog.dismiss();
    }

    //跳转至图库
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, StaticClass.IMAGE_REQUEST_CODE);
        dialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_CANCELED) {
            switch (requestCode) {
                case StaticClass.IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case StaticClass.CAMERA_REQUEST_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(), StaticClass.PHOTO_IMAGE_FILE_NAME);
                    startPhotoZoom(Uri.fromFile(tempFile));
                    break;
                case StaticClass.CROP_REQUEST_CODE:
                    //判断是否点击舍弃
                    if (data != null) {
                        //拿到图片进行设置
                        setImageToView(data);
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }
    }
    //设置图片
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap =  bundle.getParcelable("data");
            profile_image.setImageBitmap(bitmap);
            //保存图片
            UtilTools.putImageToShare(getActivity(), profile_image);
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    //裁剪图片
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        //裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪图片质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        //发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, StaticClass.CROP_REQUEST_CODE);
    }
}
