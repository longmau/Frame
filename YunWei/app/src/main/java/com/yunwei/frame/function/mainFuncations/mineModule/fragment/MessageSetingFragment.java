package com.yunwei.frame.function.mainFuncations.mineModule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.yunwei.frame.R;
import com.yunwei.frame.common.Constant;
import com.yunwei.frame.function.base.BaseFragment;
import com.yunwei.frame.utils.ISpfUtil;
import com.yunwei.frame.utils.IStringUtils;
import com.yunwei.frame.view.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.mineFuncation.fragment
 * @Description:消息提醒设置界面
 * @date 2016/11/28 10:22
 */

public class MessageSetingFragment extends BaseFragment {

    public final static String FRAGMENT_FLAG = "MessageFragment";

    @BindView(R.id.messageSetingFragment_voice_switchButton)
    SwitchButton messageSetingFragmentVoiceSwitchButton;
    @BindView(R.id.messageSetingFragment_notice_switchButton)
    SwitchButton messageSetingFragmentNoticeSwitchButton;

    private static MessageSetingFragment instance;

    public static MessageSetingFragment newInstance() {
        if (instance == null) {
            instance = new MessageSetingFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mine_message_seting_fragment, null);
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        initUI();
        setListener();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        messageSetingFragmentNoticeSwitchButton.setChecked(IStringUtils.toBool(ISpfUtil.getValue(Constant.MESSAGE_NOTICE_SIGN, false).toString()));
        messageSetingFragmentVoiceSwitchButton.setChecked(IStringUtils.toBool(ISpfUtil.getValue(Constant.MESSAGE_VOICE_SIGN, false).toString()));
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        messageSetingFragmentVoiceSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ISpfUtil.setValue(Constant.MESSAGE_VOICE_SIGN, isChecked);
            }
        });

        messageSetingFragmentNoticeSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ISpfUtil.setValue(Constant.MESSAGE_NOTICE_SIGN, isChecked);
            }
        });
    }
}
