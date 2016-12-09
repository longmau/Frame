package com.yunwei.frame.function.mainFuncations.recordModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.frame.R;
import com.yunwei.frame.function.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.recordFuncation
 * @Description:历史模块基类
 * @date 2016/12/7 14:15
 */

public class RecordBaseFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.record_base_recyclerView)
    RecyclerView recordBaseRecyclerView;
    @BindView(R.id.record_base_swipeRefresh)
    SwipeRefreshLayout recordBaseSwipeRefresh;
    @BindView(R.id.record_base_empty_textView)
    TextView recordBaseEmptyTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.record_base_layout, null);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView(){
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        manager.setOrientation(OrientationHelper.VERTICAL);
        recordBaseRecyclerView.setLayoutManager(manager);
        recordBaseSwipeRefresh.setOnRefreshListener(this);
    }

    /**
     * 设置adapter
     * @param adapter
     */
    public void setAdapter(RecyclerView.Adapter adapter){
        recordBaseRecyclerView.setAdapter(adapter);
    }

    /**
     * 刷新
     */
    public void refresh(){
        recordBaseSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                recordBaseSwipeRefresh.setRefreshing(true);
            }
        });
        onRefresh();
    }

    /**
     * 关闭刷新
     */
    protected void closeRefresh(){
        recordBaseSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                recordBaseSwipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {

    }
}
