package com.yunwei.frame.function.mainFuncations.recordModule.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.yunwei.frame.function.account.data.UserInfoEntity;
import com.yunwei.frame.function.base.BaseRecyclerViewAdapter;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.recordModule.adapter
 * @Description:
 * @date 2016/12/8 15:14
 */

public class DeviceRecyclerViewAdater extends BaseRecyclerViewAdapter<UserInfoEntity> {

    public DeviceRecyclerViewAdater(Context context){
        super(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

}
