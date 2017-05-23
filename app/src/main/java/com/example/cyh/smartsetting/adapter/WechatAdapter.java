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
import android.widget.TextView;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.WeChatBean;
import com.example.cyh.smartsetting.utils.L;
import com.example.cyh.smartsetting.utils.PicassoUtils;

import java.util.List;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.adapter
 * 文件名：  WechatAdapter
 * 创建者：  CYH
 * 创建时间：2017/5/21 20:37
 * 描述：    微信精选适配器
 */

public class WechatAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<WeChatBean> mList;
    private WeChatBean data;
    private int width;
    private int height;

    public WechatAdapter(Context mContext, List<WeChatBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //获取屏幕宽高
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
        L.i("width:" + width + "height:" + height);
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
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.wechat_list_item, null);
            viewHolder.iv_wechat_img = (ImageView) view.findViewById(R.id.iv_wechat_img);
            viewHolder.tv_wechat_title = (TextView) view.findViewById(R.id.tv_wechat_title);
            viewHolder.tv_wechat_source = (TextView) view.findViewById(R.id.tv_wechat_source);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //设置标题过长时结尾添加省略号
        viewHolder.tv_wechat_title.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tv_wechat_title.setSingleLine(true);

        data = mList.get(i);
        viewHolder.tv_wechat_title.setText(data.getTitle());
        viewHolder.tv_wechat_source.setText(data.getSource());
        L.i("url:" +data.getFirstImg());
        if (!TextUtils.isEmpty(data.getFirstImg())) {
            PicassoUtils.loadImageSize(mContext, data.getFirstImg(), width / 4, height / 10, viewHolder.iv_wechat_img);
        } else {
            viewHolder.iv_wechat_img.setImageResource(R.mipmap.cyh);
        }
        return view;
    }

    class ViewHolder {
        private ImageView iv_wechat_img;
        private TextView tv_wechat_title;
        private TextView tv_wechat_source;
    }

}
