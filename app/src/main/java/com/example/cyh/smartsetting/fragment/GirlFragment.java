package com.example.cyh.smartsetting.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.adapter.GirlAdapter;
import com.example.cyh.smartsetting.entity.GirlResult;
import com.example.cyh.smartsetting.utils.PicassoUtils;
import com.example.cyh.smartsetting.view.CustomDialog;
import com.github.chrisbanes.photoview.PhotoView;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class GirlFragment extends Fragment {

    private GridView mGridView;

    private List<String> mList = new ArrayList<>();
    private GirlAdapter mAdapter;

    private CustomDialog dialog;

    private PhotoView mPhotoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View girlView = inflater.inflate(R.layout.fragment_girl, container, false);
        findView(girlView);
        return girlView;
    }

    private void findView(View view) {
        mGridView = (GridView) view.findViewById(R.id.mGridView);

        getGirlData();

        dialog = new CustomDialog(getActivity(),
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                R.layout.dialog_girl,
                R.style.theme_dialog,
                Gravity.CENTER,
                R.style.pop_anim_style);

        mPhotoView = (PhotoView) dialog.findViewById(R.id.mPhotoView);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PicassoUtils.loadImage(getActivity(), mList.get(i), mPhotoView);
                dialog.show();
            }
        });
    }

    private void getGirlData() {
        String welfare = null;
        try {
            welfare = URLEncoder.encode(getString(R.string.text_welfare), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://gank.io/api/search/query/listview/category/" + welfare +"/count/50/page/1";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                try {
                    GirlResult girlResult = new GirlResult().getParseJson(t);
                    for (int i = 0; i < girlResult.getResults().size(); i++) {
                        mList.add(girlResult.getResults().get(i).getUrl());
                    }
                    mAdapter = new GirlAdapter(getActivity(), mList);
                    mGridView.setAdapter(mAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
