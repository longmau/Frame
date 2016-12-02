package com.yunwei.frame.function.mainFuncations.data.soure;

import com.yunwei.frame.common.Constant;
import com.yunwei.frame.common.retrofit.RetrofitManager;
import com.yunwei.frame.entity.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.data.soure
 * @Description:
 * @date 2016/11/30 13:59
 */

public class MainRemoteRepo implements MainDataSource {
    private final String TAG = getClass().getSimpleName();

    private static MainRemoteRepo instance;

    public static MainRemoteRepo newInstance() {
        if (instance == null) {
            instance = new MainRemoteRepo();
        }
        return instance;
    }

    @Override
    public void reqQiNiuToken(final RequestQiNiuTokenCallBack callBack) {
        Call<ResponseModel<String>> call = RetrofitManager.getInstance().getService().reqQiniuToken();
        call.enqueue(new Callback<ResponseModel<String>>() {
            @Override
            public void onResponse(Call<ResponseModel<String>> call, Response<ResponseModel<String>> response) {
                if (response.code() == Constant.HTTP_SUCESS_CODE) {
                    if (response.isSuccessful()) {
                        callBack.getQiNiuTokenSuccess(call.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<String>> call, Throwable t) {

            }
        });
    }
}
