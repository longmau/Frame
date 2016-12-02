package com.yunwei.frame.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.yunwei.frame.R;
import com.yunwei.frame.function.mainFuncations.MainActivity;
import com.yunwei.frame.utils.ILog;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.service
 * @Description:
 * @date 2016/12/1 20:54
 */

public class MonitorService extends Service implements AMapLocationListener {
    private final String TAG = getClass().getSimpleName();

    private final IBinder binder=new MonitorBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        ILog.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MonitorBinder extends Binder {
        public MonitorBinder getService() {
            return MonitorBinder.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*开启前台服务 新线程，获取定位信息，保持发送数据给后台*/
        startForeground(17, createNotification());
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ILog.d(TAG, "onDestroy");
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        ILog.d(TAG, "onLocationChanged");
    }

    /**
     * 发起前台通知
     *
     * @return
     */
    private Notification createNotification() {
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, intent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                .setContentTitle(getString(R.string.app_name)) // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText("定位服务正在运行") // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
        return builder.build();
    }
}
