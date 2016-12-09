package com.yunwei.frame.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.frame.R;
import com.yunwei.frame.utils.ILog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.view
 * @Description:
 * @date 2016/12/9 11:06
 */

public class PullToRefreshRecyclerView extends LinearLayout implements SwipeRefreshLayout.OnRefreshListener {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.pullToRefresh_recyclerView)
    RecyclerView pullToRefreshRecyclerView;
    @BindView(R.id.pullToRefresh_swipeRefreshLayout)
    SwipeRefreshLayout pullToRefreshSwipeRefreshLayout;
    @BindView(R.id.pullToRefresh_empty_textView)
    TextView pullToRefreshEmptyTextView;

    private LinearLayoutManager linearLayoutManager;
    private boolean isLoadingMore;
    private PullToRefreshRecyclerViewListener listener;

    public PullToRefreshRecyclerView(Context context) {
        super(context, null);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attri) {
        super(context, attri);
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.pulltorefresh_recyclerview, null);
        ButterKnife.bind(this, rootView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        pullToRefreshRecyclerView.setLayoutManager(linearLayoutManager);
        pullToRefreshRecyclerView.addOnScrollListener(new RecyclerViewOnScrollListener());
        pullToRefreshSwipeRefreshLayout.setOnRefreshListener(this);
        addView(rootView);
    }

    /**
     * 设置RecyclerView adapter
     *
     * @param adapter
     */
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        pullToRefreshRecyclerView.setAdapter(adapter);
    }

    /**
     * 设置内容为空
     */
    public void setEmptyTextView() {
        pullToRefreshEmptyTextView.setVisibility(GONE);
    }

    public void setLoadingMore(boolean loadingMore) {
        isLoadingMore = loadingMore;
    }

    /**
     * 设置监听
     *
     * @param listener
     */
    public void setListener(PullToRefreshRecyclerViewListener listener) {
        this.listener = listener;
    }

    /**
     * 取消下拉刷新
     */
    public void closeDownRefresh() {
        pullToRefreshSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if (listener != null) {
            listener.onDownRefresh();
        }
    }

    /**
     * RecyclerView滑动监听
     */
    private class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            int totalItemCount = linearLayoutManager.getItemCount();
            //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
            // dy>0 表示向下滑动
            ILog.d(TAG, "lastVisibleItem==" + lastVisibleItem + ", totalItemCount==" + totalItemCount);
            if (lastVisibleItem == totalItemCount - 2 && dy > 0) {
                if (isLoadingMore) {
                    ILog.d("Aaron", "ignore manually update!");
                } else {
                    if (listener != null) {
                        listener.onPullRefresh();
                    }
                    isLoadingMore = false;
                }
            }
        }
    }


    public interface PullToRefreshRecyclerViewListener {
        void onDownRefresh();

        void onPullRefresh();
    }
}
