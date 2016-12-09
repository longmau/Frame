package com.yunwei.frame.function.mainFuncations.recordModule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yunwei.frame.function.mainFuncations.recordModule.RecordBaseFragment;
import com.yunwei.frame.function.mainFuncations.recordModule.adapter.DeviceRecyclerViewAdater;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.recordFuncation
 * @Description:设施采集历史记录
 * @date 2016/12/7 14:15
 */

public class DeviceRecordFragment extends RecordBaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAdapter(new DeviceRecyclerViewAdater(getContext()));
//        refresh();
    }
}
