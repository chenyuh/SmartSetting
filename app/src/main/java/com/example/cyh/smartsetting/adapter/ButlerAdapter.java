package com.example.cyh.smartsetting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.entity.Msg;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.utils.UtilTools;

import java.util.List;

/**
 * 项目名：   SmartSetting
 * 包名：    com.example.cyh.smartsetting.adapter
 * 文件名：  ButlerAdapter
 * 创建者：  CYH
 * 创建时间：2017/5/20 14:47
 * 描述：    服务管家适配器
 */

public class ButlerAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Msg> mList;

    public ButlerAdapter(Context mContext, List<Msg> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        //加载布局
        ViewHolderLeft viewHolderLeft = null;
        ViewHolderRight viewHolderRight = null;
        int type = getItemViewType(i);
        if (view == null) {
            switch (type) {
            	case StaticClass.TYPE_LEFT:
                    viewHolderLeft = new ViewHolderLeft();
                    view = mInflater.inflate(R.layout.butler_left_item, null);
                    viewHolderLeft.tv_butler_left = (TextView) view.findViewById(R.id.tv_butler_left);
                    view.setTag(viewHolderLeft);
                    break;
                case StaticClass.TYPE_RIGHT:
                    viewHolderRight = new ViewHolderRight();
                    view = mInflater.inflate(R.layout.butler_right_item, null);
                    viewHolderRight.tv_butler_right = (TextView) view.findViewById(R.id.tv_butler_right);
                    viewHolderRight.img_butler_user = (ImageView) view.findViewById(R.id.img_butler_user);
                    view.setTag(viewHolderRight);
                    break;
            
            	default:
            		break;
            }
        } else {
            switch (type) {
            	case StaticClass.TYPE_LEFT:
            		viewHolderLeft = (ViewHolderLeft) view.getTag();
            		break;
                case StaticClass.TYPE_RIGHT:
                    viewHolderRight = (ViewHolderRight) view.getTag();
                    break;

            	default:
            		break;
            }
        }
        //加载数据
        Msg msg = mList.get(i);
        switch (type) {
        	case StaticClass.TYPE_LEFT:
        		viewHolderLeft.tv_butler_left.setText(msg.getContent());
        		break;
            case StaticClass.TYPE_RIGHT:
                viewHolderRight.tv_butler_right.setText(msg.getContent());
                UtilTools.getImageFromShare(mContext, viewHolderRight.img_butler_user);
                break;
        	default:
        		break;
        }

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        Msg msgData = mList.get(position);
        int type = msgData.getType();
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class ViewHolderLeft {
        private TextView tv_butler_left;
    }
    class ViewHolderRight {
        private TextView tv_butler_right;
        private ImageView img_butler_user;
    }
}
