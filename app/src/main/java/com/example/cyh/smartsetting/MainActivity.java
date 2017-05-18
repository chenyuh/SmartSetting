package com.example.cyh.smartsetting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cyh.smartsetting.fragment.ButlerFragment;
import com.example.cyh.smartsetting.fragment.GirlFragment;
import com.example.cyh.smartsetting.fragment.WeChatFragment;
import com.example.cyh.smartsetting.fragment.userFragment;
import com.example.cyh.smartsetting.ui.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mSetting;
    //标题
    private List<String> mTitle;
    private List<Fragment> mFragment;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //取消阴影
        //getSupportActionBar().setElevation(0);
        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(this.getString(R.string.butler));
        mTitle.add(this.getString(R.string.wechat));
        mTitle.add(this.getString(R.string.beautifulGirl));
        mTitle.add(this.getString(R.string.user));

        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WeChatFragment());
        mFragment.add(new GirlFragment());
        mFragment.add(new userFragment());
    }

    //初始化控件
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tl_title);
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mSetting = (FloatingActionButton) findViewById(R.id.flbtn_setting);
        mSetting.setVisibility(View.GONE);
        mSetting.setOnClickListener(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
                    mSetting.setVisibility(View.GONE);
                } else {
                    mSetting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置viewpager
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        //设置adapter
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flbtn_setting:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
