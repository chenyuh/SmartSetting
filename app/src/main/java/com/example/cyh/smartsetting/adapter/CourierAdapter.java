package com.example.cyh.smartsetting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.CourierBean;

import java.util.List;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.adapter
 * 文件名：  CourierAdapter
 * 创建者：  CYH
 * 创建时间：2017/5/18 17:11
 * 描述：    快递数据适配器
 */

public class CourierAdapter extends BaseAdapter {

    private Context mContext;
    private List<CourierBean> mList;
    private LayoutInflater inflater;
    private CourierBean data;

    public CourierAdapter(Context mContext, List<CourierBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            view = inflater.inflate(R.layout.courier_list_item, null);
            viewHodler.tv_remark = (TextView) view.findViewById(R.id.tv_remark);
            viewHodler.tv_datetime = (TextView) view.findViewById(R.id.tv_datetime);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        data = mList.get(i);
        viewHodler.tv_remark.setText(data.getRemark());
        viewHodler.tv_datetime.setText(data.getDatetime());
        return view;
    }

    class ViewHodler{
        private TextView tv_remark;
        private TextView tv_datetime;
    }
}
