package com.yunwei.frame.function.mainFuncations.recordFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.frame.R;
import com.yunwei.frame.function.base.BaseFragment;
import com.yunwei.frame.function.mainFuncations.missionFuncation.MissionFragment;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:历史记录主界面
 * @date 2016/11/22 18:12
 */

public class RecordFragment extends BaseFragment {

    private static RecordFragment fragment;

    public static RecordFragment newInstance() {
        if (fragment == null) {
            fragment = new RecordFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_record, null);
        return rootView;
    }
}
