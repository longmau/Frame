package com.yunwei.frame.function.mainFuncations.recordModule.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.frame.R;
import com.yunwei.frame.function.account.data.UserInfoEntity;
import com.yunwei.frame.function.base.BaseFragment;
import com.yunwei.frame.function.base.BaseRecyclerViewAdapter;
import com.yunwei.frame.function.mainFuncations.recordModule.adapter.MissionRecyclerViewAdapter;
import com.yunwei.frame.view.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.recordFuncation
 * @Description:任务上报历史记录
 * @date 2016/12/7 14:14
 */

public class MissionRecordFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener {

    @BindView(R.id.record_recyclerView)
    PullToRefreshRecyclerView recordRecyclerView;

    private MissionRecyclerViewAdapter adapter;
    private boolean loadingMore = false;

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case 0x401:
                List<UserInfoEntity> entities = (List<UserInfoEntity>) msg.obj;
                if (adapter.getLODING_STATE() == BaseRecyclerViewAdapter.LOADING) {
                    adapter.addItems(entities, adapter.getItemCount() - 1);
                    adapter.setLODING_STATE(BaseRecyclerViewAdapter.LOADING_END);
                } else {
                    adapter.clearList();
                    adapter.addItems(entities);
                }
                recordRecyclerView.closeDownRefresh();
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mission_record, null);
        ButterKnife.bind(this, rootView);
        adapter = new MissionRecyclerViewAdapter(getContext());
        recordRecyclerView.setRecyclerViewAdapter(adapter);
        recordRecyclerView.setListener(this);
        return rootView;
    }

    @Override
    public void onPullRefresh() {
        loadingMore = true;
        adapter.setLODING_STATE(BaseRecyclerViewAdapter.LOADING);
        List<UserInfoEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            UserInfoEntity entity = new UserInfoEntity();
            entity.setName("pullRefresh Item" + (i + 1));
            list.add(entity);
        }
        Message message = new Message();
        message.what = 0x401;
        message.obj = list;
        mHandler.sendMessageDelayed(message, 5000);
    }

    @Override
    public void onDownRefresh() {
        List<UserInfoEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            UserInfoEntity entity = new UserInfoEntity();
            entity.setName("downRefresh Item" + (i + 1));
            list.add(entity);
        }
        Message message = new Message();
        message.what = 0x401;
        message.obj = list;
        mHandler.sendMessageDelayed(message, 5000);
    }
}
