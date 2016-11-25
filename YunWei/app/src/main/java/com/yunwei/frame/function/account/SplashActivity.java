package com.yunwei.frame.function.account;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunwei.frame.R;
import com.yunwei.frame.common.Constant;
import com.yunwei.frame.common.handler.HandlerValue;
import com.yunwei.frame.function.base.BaseActivity;
import com.yunwei.frame.function.mainFuncations.MainActivity;
import com.yunwei.frame.utils.ISkipActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account
 * @Description:启动界面
 * @date 2016/11/22 16:46
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_iv)
    ImageView splashIv;

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case HandlerValue.START_PAGE_DELAYED:
                ISkipActivityUtil.startIntent(this, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_splash);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        ButterKnife.bind(this);
        Glide.with(this).load(R.mipmap.default_welcom).into(splashIv);
        /**
         * 延时启动
         */
        mHandler.sendEmptyMessageDelayed(HandlerValue.START_PAGE_DELAYED, Constant.THREE_SECONDES);
    }

    @Override
    protected void onPause() {
        super.onPause();
        super.finish();
    }
}
