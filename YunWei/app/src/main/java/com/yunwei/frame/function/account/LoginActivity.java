package com.yunwei.frame.function.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.yunwei.frame.R;
import com.yunwei.frame.function.base.BaseActivity;
import com.yunwei.frame.function.mainFuncations.MainActivity;
import com.yunwei.frame.utils.ISkipActivityUtil;
import com.yunwei.frame.widget.ResetEditView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account
 * @Description:登录界面
 * @date 2016/11/25 14:02
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.loginActivity_account_editView)
    ResetEditView loginActivityAccountEditView;
    @BindView(R.id.loginActivity_password_editView)
    ResetEditView loginActivityPasswordEditView;
    @BindView(R.id.loginActivity_login_button)
    Button loginActivityLoginButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginActivity_login_button)
    public void onClick() {
        ISkipActivityUtil.startIntent(this, MainActivity.class);
        this.finish();
    }
}
