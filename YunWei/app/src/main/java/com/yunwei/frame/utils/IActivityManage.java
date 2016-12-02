package com.yunwei.frame.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.yunwei.zaina.utils
 * @Description: Activity类管工具类
 * @author: Aaron
 * @date: 2016-05-31
 * @Time: 11:59
 * @version: V1.0
 */
public class IActivityManage {
    private List<Activity> activities = new ArrayList<Activity>();

    private static IActivityManage mActivityManage;

    public static IActivityManage getInstance() {
        if (mActivityManage == null) {
            mActivityManage = new IActivityManage();
        }
        return mActivityManage;
    }

    public void addActivity(Activity activity) {
        if (activities == null)
            return;
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activities == null)
            return;
        activities.remove(activity);
    }

    public void exit() {
        if (activities == null)
            return;
        for (Activity activity : activities) {
            if (activity != null)
                activity.finish();
                System.gc();
        }
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
