package com.yunwei.frame.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yunwei.frame.common.eventbus.EventConstant;
import com.yunwei.frame.common.eventbus.NoticeEvent;
import com.yunwei.frame.utils.IGPSHelper;
import com.yunwei.frame.utils.ILog;

import org.greenrobot.eventbus.EventBus;

/**
 * @Package: com.yunwei.zaina.service
 * @Description:定位Service 1、google定位与高德定位SDK同是开启
 * 2、默认情况下会使用高德定位、在无网络情况下使用GPS定位[处理逻辑在HomeFragment onBackGroundUserEvent方法]
 * @author: Aaron
 * @date: 2016-06-02
 * @Time: 09:12
 * @version: V1.0
 */
public class LocationService extends Service implements AMapLocationListener {
    private final String TAG = getClass().getSimpleName();

    private LocationManager mLocationManager;
    private GPSLocationListener gpsLocationListener;
    private Location mCurrentLocation;

    // 声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption locationClientOption;
    /**
     * 定位相关参数
     */
    private final int LOC_INTERVEL = 1000;// GPS定位间隔
    private final int REPORT_SITE_DISTANCE = 1;// GPS距离设置

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        startGoogleLocation();
        startGaoDeMapService();
        ILog.d(TAG,"onCrate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ILog.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        ILog.d(TAG,"onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.onDestroy();
        }
        if (mLocationManager != null && gpsLocationListener != null) {
            mLocationManager.removeUpdates(gpsLocationListener);
        }
        ILog.d(TAG,"onDestroy");
    }

    /**
     * 启动google GPS定位方式
     */
    private void startGoogleLocation() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        //GPS定位
        gpsLocationListener = new GPSLocationListener();
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOC_INTERVEL, REPORT_SITE_DISTANCE, gpsLocationListener);
    }

    /**
     * @throws
     * @Title: startGaoDeMapService
     * @Description: 启用高德定位
     */

    private void startGaoDeMapService() {
        mLocationClient = new AMapLocationClient(getApplicationContext());

        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度
        locationClientOption.setNeedAddress(true);
        locationClientOption.setInterval(LOC_INTERVEL);
        mLocationClient.setLocationOption(locationClientOption);

        mLocationClient.setLocationListener(this);
        mLocationClient.startLocation();
    }

    /**
     * GPS定位监听器
     */
    private class GPSLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (mCurrentLocation == null) {
                mCurrentLocation = location;
            }

            if (!IGPSHelper.isBetterLocation(location, mCurrentLocation)) {
                mCurrentLocation = location;
            }
            sendGoogleLocationData(location);
            ILog.d(TAG, "onLocationChanged lat==" + location.getLatitude() + ", lng==" + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            ILog.d(TAG, "onStatuschanged provider==" + provider + ", status==" + status + ", extras==" + extras.toString());

        }

        @Override
        public void onProviderEnabled(String provider) {
            ILog.d(TAG, "onProviderEnabled==" + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            ILog.d(TAG, "onProviderDisabled==" + provider);
        }
    }

    /**
     * 發送google位置信息
     *
     * @param location
     */
    private void sendGoogleLocationData(Location location) {
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE1);
        event.setObj(location);
        EventBus.getDefault().post(event);
    }

    /**
     * 發送高德位置信息
     *
     * @param location
     */
    private void sendGaoDeLocationData(AMapLocation location) {
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE2);
        event.setObj(location);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                sendGaoDeLocationData(aMapLocation);
            } else {
                ILog.e(TAG, "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
            }
        }
    }
}
