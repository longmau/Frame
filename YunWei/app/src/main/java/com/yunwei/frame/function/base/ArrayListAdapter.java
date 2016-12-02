package com.yunwei.frame.function.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @Package: com.yunwei.zaina.ui.adapter
 * @Description:Adapter基类
 * @author: Aaron
 * @date: 2016-05-30
 * @Time: 11:31
 * @version: V1.0
 */
public abstract  class ArrayListAdapter<T> extends BaseAdapter {
    protected List<T> mList = new LinkedList<T>();
    protected Activity mContext;

    public ArrayListAdapter(Activity context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public void setList(List<T> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void appendPositionToList(int pos, T t) {
        if (mList == null) {
            return;
        }
        mList.add(pos, t);
        notifyDataSetChanged();
    }

    public void appendPositionToList(int pos, List<T> lists) {
        if (lists == null) {
            return;
        }
        if (mList == null) {
            return;
        }
        mList.addAll(pos, lists);
        notifyDataSetChanged();
    }

    public void appendToList(List<T> lists) {

        if (lists == null) {
            return;
        }
        mList.addAll(lists);
        notifyDataSetChanged();
    }

    public void clearList() {
        if (mList != null && !mList.isEmpty()) {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void removePos(int pos) {
        if (mList == null) {
            return;
        }
        mList.remove(pos);
        notifyDataSetChanged();
    }

    public void removeObject(T t) {
        if (t == null) {
            return;
        }
        if (mList == null) {
            return;
        }
        mList.remove(t);
        notifyDataSetChanged();
    }

    public void appendToList(T t) {
        if (t == null) {
            return;
        }
        if (mList == null) {
            return;
        }
        mList.add(t);
        notifyDataSetChanged();
    }

    public void appendToList(T[] lists) {

        if (lists == null) {
            return;
        }
        List<T> arrayList = new ArrayList<T>(lists.length);
        for (T t : lists) {
            arrayList.add(t);
        }
        appendToList(arrayList);
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(T[] list) {
        List<T> arrayList = new ArrayList<T>(list.length);
        for (T t : list) {
            arrayList.add(t);
        }
        setList(arrayList);
    }
}
