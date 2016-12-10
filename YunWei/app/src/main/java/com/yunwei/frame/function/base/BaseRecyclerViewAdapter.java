package com.yunwei.frame.function.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunwei.frame.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.history.adapter
 * @Description:RecyclerView.Adapter基类
 * @date 2016/10/09 17:04
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static String TAG = "BaseRecyclerViewAdapter";
    /**
     * 正在加载状态值
     */
    public final static int LOADING = 0x10;
    /**
     * 加载结束状态值
     */
    public final static int LOADING_END = 0x20;
    /**
     * 加载状态 LOADING or LOADING_END
     */
    private int LODING_STATE;
    /**
     * 普通Item View
     */
    private static final int TYPE_ITEM = 0;
    /**
     * 顶部FootView
     */
    private static final int TYPE_FOOTER = 1;

    protected Context mContent;
    protected LayoutInflater inflater;

    protected List<T> mLists = new ArrayList<>();

    protected OnRecyclerViewItemClickListener listener;
    protected OnRecyclerViewItemLongClickListener longListener;

    public BaseRecyclerViewAdapter(Context context) {
        this.mContent = context;
        this.LODING_STATE = LOADING_END;
        if (context != null) {
            this.inflater = LayoutInflater.from(context);
        }
    }

    @Override
    public int getItemViewType(int position) {
        /* 最后一个item设置为footerView*/
        if (getItemCount() >= 20 && position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return onCreateBaseViewHolder(parent, viewType);
        } else if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(inflater.inflate(R.layout.item_base_recycler_view_adapter_foot, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_ITEM) {
            onBindBaseViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        int count = getList().size();
        if (count >= 20) { 
            return ++count;
        } else if (count > 0) {
            return count;
        } else {
            return 0;
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * 添加资源
     *
     * @param list
     */
    public void addItems(List<T> list) {
        mLists.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加资源
     *
     * @param list
     * @param position
     */
    public void addItems(List<T> list, int position) {
        mLists.addAll(position, list);
        notifyDataSetChanged();
    }

    /**
     * 添加资源
     *
     * @param t
     */
    public void addItem(T t) {
        mLists.add(t);
        notifyDataSetChanged();
    }

    /**
     * 添加资源
     *
     * @param t
     * @param position
     */
    public void addItem(T t, int position) {
        mLists.add(position, t);
        notifyDataSetChanged();
    }

    /**
     * 返回资源
     *
     * @return
     */
    public List<T> getList() {
        return mLists;
    }

    /**
     * 清除资源
     */
    public void clearList() {
        mLists.clear();
        notifyDataSetChanged();
    }

    public void setLODING_STATE(int LODING_STATE) {
        this.LODING_STATE = LODING_STATE;
        notifyDataSetChanged();
    }

    public int getLODING_STATE() {
        return LODING_STATE;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener longListener) {
        this.longListener = longListener;
    }
}
