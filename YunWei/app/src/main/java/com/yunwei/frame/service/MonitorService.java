package com.yunwei.frame.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.yunwei.frame.R;
import com.yunwei.frame.common.handler.HandlerValue;
import com.yunwei.frame.function.mainFuncations.MainActivity;
import com.yunwei.frame.utils.NotificationUtil;
import com.yunwei.frame.utils.PollingUtils;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.service
 * @Description:监控服务 高德定位回调 设置前台服务提高进程的级别
 * @date 2016/12/1 20:54
 */

public class MonitorService extends Service implements AMapLocationListener {
    private final String TAG = getClass().getSimpleName();

    private final IBinder binder = new MonitorBinder();

    private AMapLocation location;

    @Override
    public void onCreate() {
        super.onCreate();
        /*开启前台服务*/
        startForeground(17, NotificationUtil.sendNotification(getString(R.string.app_name), getString(R.string.foregroun_content_text), MainActivity.class));
        /*开始轮询*/
        PollingUtils.startPollingService(this, 1);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MonitorBinder extends Binder {
        public MonitorService getService() {
            return MonitorService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*停止前台服务*/
        stopForeground(true);
        /*停止轮询*/
        PollingUtils.stopPollingService(this);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                this.location = aMapLocation;
                Message message = new Message();
                message.what = HandlerValue.LOCATION_SUCCESS_KEY;
                message.obj = aMapLocation;
                try {
                    MainActivity.mServiceMessenger.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 返回定位信息
     *
     * @return
     */
    public AMapLocation getLocation() {
        return location;
    }
}
