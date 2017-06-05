package com.example.cyh.smartsetting.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.adapter.WechatAdapter;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.entity.WeChatBean;
import com.example.cyh.smartsetting.entity.WeChatResult;
import com.example.cyh.smartsetting.ui.WebViewActivity;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import java.util.ArrayList;
import java.util.List;

public class WeChatFragment extends BaseFragment {

    private ListView lv_wechat;
    private WechatAdapter adapter;
    private List<WeChatBean> mList = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    private SwipeRefreshLayout swipe_refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View weChatView = inflater.inflate(R.layout.fragment_wechat, null);
        findView(weChatView);
        return weChatView;
    }

    private void findView(View view) {
        lv_wechat = (ListView) view.findViewById(R.id.lv_wechat);

        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWeChatData();
                swipe_refresh.setRefreshing(false);
            }
        });

        getWeChatData();

        lv_wechat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                WebViewActivity.actionStart(getActivity(), mListTitle, mListUrl, position);
            }
        });
    }


    public void getWeChatData() {
        String url = "http://v.juhe.cn/weixin/query?key=" + StaticClass.WECHAT_APP_KEY + "&ps=50";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                try {
                    WeChatResult result = new WeChatResult().getParseJson(t);
                    for (int i = 0; i < result.getResult().getList().size(); i++) {
                        WeChatBean data = new WeChatBean();
                        String title = result.getResult().getList().get(i).getTitle();
                        data.setTitle(title);
                        data.setSource(result.getResult().getList().get(i).getSource());
                        data.setFirstImg(result.getResult().getList().get(i).getFirstImg());
                        mList.add(data);
                        mListTitle.add(title);
                        mListUrl.add(result.getResult().getList().get(i).getUrl());
                    }
                    adapter = new WechatAdapter(getActivity(), mList);
                    lv_wechat.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        return super.hideSoftByEditViewIds();
    }

    @Override
    public View[] filterViewByIds() {
        return super.filterViewByIds();
    }
}
