package com.yunwei.frame.function.mainFuncations;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.amap.api.location.AMapLocation;
import com.esri.core.geometry.Point;
import com.yunwei.frame.BuildConfig;
import com.yunwei.frame.R;
import com.yunwei.frame.common.dialog.DialogFactory;
import com.yunwei.frame.common.handler.HandlerValue;
import com.yunwei.frame.function.base.BaseActivity;
import com.yunwei.frame.function.base.DataApplication;
import com.yunwei.frame.function.mainFuncations.data.soure.MainRemoteRepo;
import com.yunwei.frame.function.mainFuncations.homeModule.HomeFragment;
import com.yunwei.frame.function.mainFuncations.mineModule.MineFragment;
import com.yunwei.frame.function.mainFuncations.missionModule.MissionFragment;
import com.yunwei.frame.function.mainFuncations.recordModule.RecordFragment;
import com.yunwei.frame.function.mainFuncations.trackModule.MainPresenter;
import com.yunwei.frame.function.mainFuncations.trackModule.TrackFragment;
import com.yunwei.frame.service.MonitorService;
import com.yunwei.frame.utils.IActivityManage;
import com.yunwei.frame.view.MainBottomNavigationBar;
import com.yunwei.map.MapView;
import com.yunwei.map.entity.MPointEntity;
import com.yunwei.map.utils.ILngLatMercator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations
 * @Description:主界面
 * @date 2016/11/22 15:40
 */

public class MainActivity extends BaseActivity implements MainBottomNavigationBar.BottomTabSelectedListener, MainContract.MainView {
    private final String TAG = getClass().getSimpleName();

    /*模块标记*/
    private final int TAB_HOME = 0;
    private final int TAB_MISSION = 1;
    private final int TAB_TRACK = 2;
    private final int TAB_RECORD = 3;
    private final int TAB_MINE = 4;

    @BindView(R.id.main_container_FrameLayout)
    FrameLayout mainContainerFl;
    @BindView(R.id.main_bottom_navigationBar)
    MainBottomNavigationBar mainBottomNavigationBar;
    @BindView(R.id.main_mapView)
    MapView mapView;

    private MainPresenter mMainPresenter;

    /*Service*/
    private MonitorService monitorService;
    private ServiceConnection monitorConnection;
    /*TODO Service与Activity通讯通过静态调用处理，此处待优化*/
    public static Messenger mServiceMessenger;

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            /*定位回调处理*/
            case HandlerValue.LOCATION_SUCCESS_KEY:
                AMapLocation location = (AMapLocation) msg.obj;
                if (location == null) {
                    return;
                }
                 /*转成墨卡托坐标*/
                MPointEntity point = ILngLatMercator.lonLat2WebMercator(location.getLongitude(), location.getLatitude());
                /*定位点刷新*/
                mapView.updateCurrentLocation(new Point(point.getX(), point.getY()));
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        setToolbarCenterTitle(R.string.main_home_tab);
        setSwipeEnabled(false);
        ButterKnife.bind(this);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.unpause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.recycle();
        unbindMonitorService();
    }

    /**
     * 初始化
     */
    private void init() {
        mServiceMessenger = new Messenger(mHandler);

        initBottomNavigationBar();
        initPresenter();
        addMapLayer();
        bindMonitorServer();
    }

    /**
     * 初始化BottomNavigationBar
     */
    private void initBottomNavigationBar() {
        mainBottomNavigationBar.initConfig(this, R.id.main_container_FrameLayout);
        mainBottomNavigationBar.addTabItem(R.mipmap.icon_main_tab_home_pr, R.string.main_home_tab).addTabItem(R.mipmap.icon_main_tab_mission_pr, R.string.main_mission_tab).addTabItem(R.mipmap.icon_main_tab_loc_pr, R.string.main_track_tab).addTabItem(R.mipmap.icon_main_tab_record_pr, R.string.main_record_tab).addTabItem(R.mipmap.icon_main_tab_mine_pr, R.string.main_mine_tab);
        mainBottomNavigationBar.addFragment(HomeFragment.newInstance()).addFragment(MissionFragment.newInstance()).addFragment(TrackFragment.newInstance()).addFragment(RecordFragment.newInstance()).addFragment(MineFragment.newInstance());
        mainBottomNavigationBar.setTabSelectedListener(this);
        mainBottomNavigationBar.setFirstSelectedTab(TAB_TRACK);
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mMainPresenter = new MainPresenter(MainRemoteRepo.newInstance(), this);
        mMainPresenter.reqQiNiuToken();
    }

    /**
     * 添加图层
     */
    public void addMapLayer() {
        /*设置图层*/
        mapView.addFeatureLayer(BuildConfig.DEVICE_MAP_LAYER);
    }

    /**
     * 启动定位服务
     */
    private void bindMonitorServer() {
        Intent intent = new Intent(this, MonitorService.class);
        monitorConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                monitorService = ((MonitorService.MonitorBinder) service).getService();
                DataApplication.getInstance().setMonitorService(monitorService);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                monitorService = null;
            }
        };
        bindService(intent, monitorConnection, BIND_AUTO_CREATE);
        /*服务启动*/
        DataApplication.getInstance().startLocation();
    }

    /**
     * 停止定位服务
     */
    private void unbindMonitorService() {
        DataApplication.getInstance().stopLocationService();
        DataApplication.getInstance().destoryLocation();
        unbindService(monitorConnection);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
          /*将这一行注释掉，阻止activity保存fragment的状态*/
//        super.onSaveInstanceState(outState);
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case TAB_HOME:
                setToolbarCenterTitle(R.string.main_home_tab);
                mapView.setVisibility(View.VISIBLE);
                break;
            case TAB_MISSION:
                setToolbarCenterTitle(R.string.main_mission_tab);
                mapView.setVisibility(View.GONE);
                break;
            case TAB_TRACK:
                setToolbarCenterTitle(R.string.main_track_tab);
                mapView.setVisibility(View.VISIBLE);
                break;
            case TAB_RECORD:
                setToolbarCenterTitle(R.string.main_record_tab);
                mapView.setVisibility(View.GONE);
                break;
            case TAB_MINE:
                setToolbarCenterTitle(R.string.main_mine_tab);
                mapView.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showExitDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出Dialog
     */
    private void showExitDialog() {
        DialogFactory.showMsgDialog(this, getString(R.string.dialog_title_exit), getString(R.string.exit_msg) + getString(R.string.app_name) + "?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.gc();
                IActivityManage.getInstance().exit();
                System.exit(0);
            }
        });
    }
}
