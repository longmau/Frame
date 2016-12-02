package com.yunwei.frame.function.base;

import android.app.Application;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.yunwei.frame.service.MonitorService;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.base
 * @Description:Application类
 * @date 2016/11/22 14:59
 */

public class DataApplication extends Application {

    private static DataApplication instance;

    /*定位Client*/
    private AMapLocationClient mLocationClient;
    /*定位option*/
    private AMapLocationClientOption locationClientOption;

    /*经度*/
    private double lng;
    /*纬度*/
    private double lat;
    /*墨卡托坐标*/
    private double x;
    private double y;
    private double z;
    /*当前位置信息*/
    private String currentAddr;
    /*当前路段*/
    private String street;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initLocationClient();
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

    public void startLocationService() {
        mLocationClient.startLocation();
    }

    private void stopLocationService() {
        mLocationClient.stopLocation();
    }

    private void destoryLocationService() {
        mLocationClient.onDestroy();
        mLocationClient = null;
        locationClientOption = null;
    }

    public static DataApplication getInstance() {
        return instance;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getCurrentAddr() {
        return currentAddr;
    }

    public void setCurrentAddr(String currentAddr) {
        this.currentAddr = currentAddr;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
