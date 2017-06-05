package com.example.cyh.smartsetting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cyh.smartsetting.R;
import com.example.cyh.smartsetting.adapter.ButlerAdapter;
import com.example.cyh.smartsetting.entity.ButlerResult;
import com.example.cyh.smartsetting.entity.Msg;
import com.example.cyh.smartsetting.entity.StaticClass;
import com.example.cyh.smartsetting.utils.L;
import com.example.cyh.smartsetting.utils.ShareUtil;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ButlerFragment extends BaseFragment implements View.OnClickListener {

    private ListView lv_butler;
    private EditText et_send_content;
    private Button btn_send;

    private List<Msg> mList = new ArrayList<>();
    private ButlerAdapter mAdapter;

    //TTS
    private SpeechSynthesizer mTts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View butlerView = inflater.inflate(R.layout.fragment_butler, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findView(butlerView);
        return butlerView;
    }

    private void findView(View v) {

        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mTts = SpeechSynthesizer.createSynthesizer(getActivity(), null);
        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaomei");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在SD卡需要在AndroidManifest.xml添加写SD卡权限
        //如果不需要保存合成音频，注释该行代码
        //mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");

        lv_butler = (ListView) v.findViewById(R.id.lv_butler);
        et_send_content = (EditText) v.findViewById(R.id.et_send_content);
        btn_send = (Button) v.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        mAdapter = new ButlerAdapter(getActivity(), mList);
        lv_butler.setAdapter(mAdapter);

        String defString = "你好，我是小乖，和我聊聊天吧！";
        addLeftItem(defString);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                String send = et_send_content.getText().toString().trim();
                //将字符串转换为utf-8
                String strUTF8 = null;
                try {
                    strUTF8 = URLEncoder.encode(send, "UTF-8");
                    L.d("strUTF8:" + strUTF8);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //判断是否为空
                if (!TextUtils.isEmpty(send)) {
                    addRightItem(send);
                    et_send_content.setText("");
                    //判断是否大于30
                    if (send.length() < 30) {
                        //拼接url
                        String url = "http://op.juhe.cn/robot/index?info="+ strUTF8 + "&key=" + StaticClass.CHAT_APP_KEY;
                        //数据请求
                        RxVolley.get(url, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                L.d(t);
                                try {
                                    ButlerResult butlerResult = new ButlerResult().getParseJson(t);
                                    String text = butlerResult.getResult().getText();
                                    addLeftItem(text);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                    } else {
                        et_send_content.setText("");
                        String fail = "小乖目前只认识30个字以内的句子。";
                        addLeftItem(fail);
                    }
                } else {
                    Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                    et_send_content.startAnimation(shake);
                    Toast.makeText(getActivity(), R.string.text_tost_empty, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //将文本添加到左边布局
    public void addLeftItem(String string) {

        boolean speak = ShareUtil.getBoolean(getActivity(), StaticClass.SPEAK, false);
        if (speak) {
            startSpeak(string);
        }

        Msg msg = new Msg(string, StaticClass.TYPE_LEFT);
        mList.add(msg);
        mAdapter.notifyDataSetChanged();
        lv_butler.setSelection(lv_butler.getBottom());
    }
    //将文本添加到右边布局
    public void addRightItem(String string) {
        Msg msg = new Msg(string, StaticClass.TYPE_RIGHT);
        mList.add(msg);
        mAdapter.notifyDataSetChanged();
        lv_butler.setSelection(lv_butler.getBottom());
    }

    //开始说话
    private void startSpeak(String text) {
        //3.开始合成
        mTts.startSpeaking(text, mSynListener);
    }

    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {
        }

        //暂停播放
        public void onSpeakPaused() {
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };

    @Override
    public int[] hideSoftByEditViewIds() {
        int[] ids = {R.id.et_send_content};
        return ids;
    }

    @Override
    public View[] filterViewByIds() {
        View[] views = {et_send_content, btn_send};
        return views;
    }
}
