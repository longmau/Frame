package com.yunwei.frame.view;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.yunwei.frame.R;
import com.yunwei.frame.function.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.view
 * @Description:主界面Bottom导航栏
 * @date 2016/11/22 18:06
 */

public class MainBottomNavigationBar extends BottomNavigationBar implements BottomNavigationBar.OnTabSelectedListener {
    /**
     * Fragment集合
     */
    private List<BaseFragment> fragments;
    /**
     * Activity
     */
    private AppCompatActivity activity;
    /**
     * 容器Id
     */
    private int containerId;

    private BottomTabSelectedListener tabSelectedListener;

    public MainBottomNavigationBar(Context context) {
        super(context);
        init();
    }

    public MainBottomNavigationBar(Context context, AttributeSet attri) {
        super(context, attri);
        init();
    }

    private void init() {
        setDefaultConfig();
        setTabSelectedListener(this);
    }


    /**
     * 初始配置
     */
    private void setDefaultConfig() {
        setMode(BottomNavigationBar.MODE_FIXED);
        setBackgroundResource(R.color.white);
    }

    /**
     * 初始化配置
     *
     * @param activity
     * @param containerId
     */
    public void initConfig(AppCompatActivity activity, int containerId) {
        this.activity = activity;
        this.containerId = containerId;
        this.fragments = new ArrayList<>();
    }

    /**
     * 添加Fragment
     *
     * @param fragment
     * @return
     */
    public MainBottomNavigationBar addFragment(BaseFragment fragment) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(fragment);

        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commit();
        return this;
    }

    /**
     * 添加TabItem
     *
     * @param iconResId
     * @param nameResId
     * @return
     */
    public MainBottomNavigationBar addTabItem(int iconResId, int nameResId) {
        addItem(new BottomNavigationItem(iconResId, nameResId)).initialise();

        return this;
    }

    /**
     * @param position
     */
    public void setDefaultFragment(int position) {
        switchTab(position);
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabSelected(int position) {
        switchTab(position);
        if (tabSelectedListener != null) {
            tabSelectedListener.onTabSelected(position);
        }
    }

    /**
     * Tab切换
     *
     * @param position
     */
    private void switchTab(int position) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        transaction.show(fragments.get(position));
        transaction.commit();
    }

    /**
     * 隐藏Fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        for (BaseFragment fragment : fragments) {
            transaction.hide(fragment);
        }
    }

    /**
     * 设置监听器
     *
     * @param listener
     */
    public void setTabSelectedListener(BottomTabSelectedListener listener) {
        this.tabSelectedListener = listener;
    }

    public interface BottomTabSelectedListener {
        void onTabSelected(int position);
    }
}
