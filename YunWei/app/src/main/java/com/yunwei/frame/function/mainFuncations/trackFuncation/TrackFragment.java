package com.yunwei.frame.function.mainFuncations.trackFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yunwei.frame.R;
import com.yunwei.frame.function.base.BaseFragment;
import com.yunwei.map.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:足迹主界面
 * @date 2016/11/22 18:12
 */

public class TrackFragment extends BaseFragment {

    private static TrackFragment fragment;

    public static TrackFragment newInstance() {
        if (fragment == null) {
            fragment = new TrackFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_track, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
