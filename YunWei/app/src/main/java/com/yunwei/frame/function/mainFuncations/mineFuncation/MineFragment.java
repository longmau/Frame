package com.yunwei.frame.function.mainFuncations.mineFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.frame.R;
import com.yunwei.frame.function.base.BaseFragment;
import com.yunwei.frame.function.mainFuncations.homeFuncation.HomeFragment;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:我的主界面
 * @date 2016/11/22 18:12
 */

public class MineFragment extends BaseFragment {

    private static MineFragment fragment;
    public static MineFragment newInstance() {
        if (fragment == null) {
            fragment = new MineFragment();
        }
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_mine, null);
        return rootView;
    }
}
