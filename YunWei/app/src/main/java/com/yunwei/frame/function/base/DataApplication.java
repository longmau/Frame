package com.yunwei.frame.function.base;

import android.app.Application;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.google.gson.Gson;
import com.yunwei.frame.common.Constant;
import com.yunwei.frame.function.account.data.UserInfoEntity;
import com.yunwei.frame.service.MonitorService;
import com.yunwei.frame.utils.ILog;
import com.yunwei.frame.utils.ISpfUtil;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.base
 * @Description:Application类
 * @date 2016/11/22 14:59
 */

public class DataApplication extends Application {
    private String TAG = getClass().getSimpleName();

    private static DataApplication instance;

    /*定位Client*/
    private AMapLocationClient mLocationClient;
    /*定位option*/
    private AMapLocationClientOption locationClientOption;
    /*监控服务*/
    private MonitorService monitorService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLocationClient();
    }

    public static DataApplication getInstance() {
        return instance;
    }

    /**
     * 初始化定位参数
     */
    private void initLocationClient() {
        mLocationClient = new AMapLocationClient(getApplicationContext());

        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度
        locationClientOption.setNeedAddress(true);
        locationClientOption.setInterval(4 * 1000);

        mLocationClient.setLocationOption(locationClientOption);
        mLocationClient.setLocationListener(new MonitorService());
    }

    /**
     * 启动定位
     */
    public void startLocation() {
        mLocationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocationService() {
        mLocationClient.stopLocation();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfoEntity getUserInfoEntity() {
        String string = ISpfUtil.getValue(Constant.USERINFO_KEY, "").toString();
        UserInfoEntity entity = new Gson().fromJson(string, UserInfoEntity.class);
        return entity;
    }

    /**
     * 销毁定位服务
     */
    public void destoryLocation() {
        mLocationClient.onDestroy();
        mLocationClient = null;
        locationClientOption = null;
    }

    public MonitorService getMonitorService() {
        return monitorService;
    }

    public void setMonitorService(MonitorService monitorService) {
        this.monitorService = monitorService;
    }
}
