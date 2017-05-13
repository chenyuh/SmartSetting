package com.example.cyh.smartsetting.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.cyh.smartsetting.MainActivity;
import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private List<View> mList = new ArrayList<>();
    private ViewPager mViewPager;
    private View view1, view2, view3;

    private ImageView point1, point2, point3;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        point1 = (ImageView) findViewById(R.id.iv_point1);
        point2 = (ImageView) findViewById(R.id.iv_point2);
        point3 = (ImageView) findViewById(R.id.iv_point3);
        back = (ImageView) findViewById(R.id.iv_back);
        //设置默认图片
        setPointImg(true, false, false);

        view1 = View.inflate(this, R.layout.guide_item_one, null);
        view2 = View.inflate(this, R.layout.guide_item_two, null);
        view3 = View.inflate(this, R.layout.guide_item_three, null);

        view3.findViewById(R.id.btn_start).setOnClickListener(this);
        back.setOnClickListener(this);

        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        mViewPager.setAdapter(new GuideAdapter(mList));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setPointImg(true, false, false);
                        back.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setPointImg(false, true, false);
                        back.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setPointImg(false, false, true);
                        back.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPointImg(boolean isCheck1, boolean isCheck2, boolean isCheck3) {
        if (isCheck1) {
            point1.setBackgroundResource(R.drawable.point_on);
        } else {
            point1.setBackgroundResource(R.drawable.point_off);
        }

        if (isCheck2) {
            point2.setBackgroundResource(R.drawable.point_on);
        } else {
            point2.setBackgroundResource(R.drawable.point_off);
        }

        if (isCheck3) {
            point3.setBackgroundResource(R.drawable.point_on);
        } else {
            point3.setBackgroundResource(R.drawable.point_off);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                MainActivity.actionStart(GuideActivity.this);
                finish();
                break;
            case R.id.iv_back:
                MainActivity.actionStart(GuideActivity.this);
                finish();
                break;
            default:
                break;
        }
    }
}
