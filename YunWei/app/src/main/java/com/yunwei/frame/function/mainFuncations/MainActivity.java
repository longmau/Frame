package com.yunwei.frame.function.mainFuncations;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.amap.api.location.AMapLocation;
import com.esri.core.geometry.Point;
import com.yunwei.frame.R;
import com.yunwei.frame.common.Constant;
import com.yunwei.frame.function.base.BaseActivity;
import com.yunwei.frame.function.base.DataApplication;
import com.yunwei.frame.function.mainFuncations.data.soure.MainRemoteRepo;
import com.yunwei.frame.function.mainFuncations.homeFuncation.HomeFragment;
import com.yunwei.frame.function.mainFuncations.mineFuncation.MineFragment;
import com.yunwei.frame.function.mainFuncations.missionFuncation.MissionFragment;
import com.yunwei.frame.function.mainFuncations.recordFuncation.RecordFragment;
import com.yunwei.frame.function.mainFuncations.trackFuncation.MainPresenter;
import com.yunwei.frame.function.mainFuncations.trackFuncation.TrackFragment;
import com.yunwei.frame.service.LocationService;
import com.yunwei.frame.service.MonitorService;
import com.yunwei.frame.utils.ILog;
import com.yunwei.frame.utils.ISpfUtil;
import com.yunwei.frame.view.MainBottomNavigationBar;
import com.yunwei.map.MapView;
import com.yunwei.map.entity.MPointEntity;

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

    private MonitorService monitorService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        setToolbarCenterTitle(R.string.main_home_tab);
        setSwipeEnabled(false);
        ButterKnife.bind(this);

        init();
        ILog.d(TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.unpause();
        ILog.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.pause();
        ILog.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.recycle();
        stopLocationService();
        ILog.d(TAG, "onDestroy");
    }

    /**
     * 初始化
     */
    private void init() {
        initBottomNavigationBar();
        initPresenter();
        startLocationServer();
        DataApplication.getInstance().startLocationService();
    }

    /**
     * 初始化BottomNavigationBar
     */
    private void initBottomNavigationBar() {
        mainBottomNavigationBar.initConfig(this, R.id.main_container_FrameLayout);
        mainBottomNavigationBar.addTabItem(R.mipmap.ic_home_white_24dp, R.string.main_home_tab).addTabItem(R.mipmap.main_tab_mission_n, R.string.main_mission_tab).addTabItem(R.mipmap.ic_location_on_white_24dp, R.string.main_track_tab).addTabItem(R.mipmap.main_tab_record_n, R.string.main_record_tab).addTabItem(R.mipmap.main_tab_mine_n, R.string.main_mine_tab);
        mainBottomNavigationBar.addFragment(HomeFragment.newInstance()).addFragment(MissionFragment.newInstance()).addFragment(TrackFragment.newInstance()).addFragment(RecordFragment.newInstance()).addFragment(MineFragment.newInstance());
        mainBottomNavigationBar.setDefaultFragment(2);
        mainBottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mMainPresenter = new MainPresenter(MainRemoteRepo.newInstance(), this);
        mMainPresenter.reqQiNiuToken();
    }

    /**
     * 启动定位服务
     */
    private void startLocationServer() {
//        Intent intent = new Intent(this, LocationService.class);
//        startService(intent);

        Intent intent1 = new Intent(this, MonitorService.class);
        startService(intent1);
        bindService(intent1, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                monitorService = ((MonitorService.MonitorBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    /**
     * 停止定位服务
     */
    private void stopLocationService() {
//        Intent intent = new Intent(this, LocationService.class);
//        stopService(intent);

//        Intent intent1= new Intent(this, MonitorService.class);
//        stopService(intent1);
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
    public void refreshLocation(AMapLocation location, MPointEntity point) {
        super.refreshLocation(location, point);
        mapView.updateCurrentLocation(new Point(point.getX(), point.getY()));
    }
}
