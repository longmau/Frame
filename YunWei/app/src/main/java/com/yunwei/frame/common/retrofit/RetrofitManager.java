package com.yunwei.frame.common.retrofit;

import com.yunwei.frame.BuildConfig;
import com.yunwei.frame.common.Constant;
import com.yunwei.frame.utils.ISpfUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.common.retrofit
 * @Description:
 * @date 2016/11/29 19:59
 */

public class RetrofitManager {

    private static RetrofitManager instance;

    private static Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        if (instance == null) {
            instance = new RetrofitManager();
        }
        return instance;
    }

    /**
     * 返回Service
     *
     * @return
     */
    public APIService getService() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .client(getClient())
                    .baseUrl(BuildConfig.DOMAI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        APIService service = mRetrofit.create(APIService.class);
        return service;
    }

    /**
     * 设置Client请求头
     *
     * @return
     */
    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "bearer " + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString())
                        .build();
                return chain.proceed(request);
            }
        }).build();
        return client;
    }
}
