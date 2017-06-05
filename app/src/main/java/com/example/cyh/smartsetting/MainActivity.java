package com.example.cyh.smartsetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.cyh.smartsetting.fragment.ButlerFragment;
import com.example.cyh.smartsetting.fragment.GirlFragment;
import com.example.cyh.smartsetting.fragment.WeChatFragment;
import com.example.cyh.smartsetting.fragment.userFragment;
import com.example.cyh.smartsetting.ui.BaseActivity;
import com.example.cyh.smartsetting.ui.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mSetting;
    //标题
    private List<String> mTitle;
    private List<Fragment> mFragment;

    private int pos = 0;

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
                pos = position;

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

    /**
     * 传入EditText的Id
     * 没有传入的EditText不做处理
     * @return id 数组
     */
    @Override
    public int[] hideSoftByEditViewIds() {
        if (pos == 0) {
            return ((ButlerFragment) mFragment.get(pos)).hideSoftByEditViewIds();
        } else if (pos == 3) {
            return ((userFragment) mFragment.get(pos)).hideSoftByEditViewIds();
        }
        return super.hideSoftByEditViewIds();
    }

    /**
     * 传入要过滤的View
     * 过滤之后点击将不会有隐藏软键盘的操作
     * @return id 数组
     */
    @Override
    public View[] filterViewByIds() {
        if (pos == 0) {
            return ((ButlerFragment) mFragment.get(pos)).filterViewByIds();
        }
        return super.filterViewByIds();
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
}
