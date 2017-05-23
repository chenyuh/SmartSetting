package com.example.cyh.smartsetting.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.utils.PicassoUtils;

import java.util.List;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.adapter
 * 文件名：  GirlAdapter
 * 创建者：  CYH
 * 创建时间：2017/5/23 11:29
 * 描述：    美女适配器
 */

public class GirlAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mList;

    private WindowManager wm;
    private int width;

    public GirlAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler viewHodler = null;
        if (view == null) {
            viewHodler = new ViewHodler();
            view = mInflater.inflate(R.layout.girl_girlview_item, null);
            viewHodler.imageView = (ImageView) view.findViewById(R.id.iv_girl_img);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }

        for (int j = 0; j < mList.size(); j++) {
            if (!TextUtils.isEmpty(mList.get(i))) {
                PicassoUtils.loadImageSize(mContext, mList.get(i), width / 2, 280, viewHodler.imageView);
            }
        }

        return view;
    }

    class ViewHodler {
        private ImageView imageView;
    }
}
