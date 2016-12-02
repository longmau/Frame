package com.yunwei.frame.common;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.common
 * @Description:常量类
 * @date 2016/11/22 15:03
 */

public class Constant {
    /**
     * 时间常量
     */
    public static final int ONE_H_MILLSECONDS = 100;// 100ms
    public static final int THREE_H_MILLSECONDES = 300;// 300ms
    public static final int ONE_SECONDE = 1 * 1000;// 1s
    public static final int TWO_SECONDES = 2 * 1000;// 2s
    public static final int THREE_SECONDES = 3 * 1000;// 3s
    public static final int FOUR_SECONDES = 4 * 1000;// 4s
    public static final int FIVE_SECONDES = 5 * 1000;// 5s
    public static final int TEN_SECONDES = 10 * 1000;// 10s
    public static final int TWENTY_SECONDS = 20 * 1000;// 20s
    public static final int THIRTY_SENCONDS = 30 * 1000;// 30s
    public static final int FORTY_SECONDS = 40 * 1000; // 40s
    public static final int FIVTY_SECONDS = 50 * 1000; // 50s
    public static final int ONE_MINUTE = 60 * 1000;// 1minute
    public static final long FIVE_MINUTES_TO_MILLSECONDS = 5 * 60 * 1000;// 5minutes
    public static final long TEN_MINUTES_TO_MILLSECONDS = 10 * 60 * 1000;// 10minutes
    public static final long FIVTEEN_MINUTES_TO_MILLSECONDS = 15 * 60 * 1000;// 15minutes
    public static final long TIMESHIFT_TOTAL_TO_MILLSECONDS = 1 * 60 * 60 * 1000; // 1hour
    public static final long LIVEBACK_TOTAL_TO_MILLSECONDS = 3 * 24 * 60 * 60 * 1000;// 3d

    /**
     * 设置通知相关
     */
    public static final String MESSAGE_VOICE_SIGN = "message_voice";/*消息任务语音*/
    public static final String MESSAGE_NOTICE_SIGN = "message_notice";/*消息任务通知*/
    public static final String TRACK_RECORD_MODE_KEY = "TRACK_RECORD_MODE";/*足迹记录方式*/
    /**
     * User
     */
    public static final String ACCESS_TOKEN_KEY = "access_token";/*Token*/
    public static final String ACCOUNT_KEY = "account";/*用户名*/
    public static final String PSSWORD_KEY = "password";/*密码*/
    public static final String USERINFO_KEY = "userInfo";/*登录信息*/
    /**
     * 七牛Token key
     */
    public static final String QINIU_TOKEN_KEY = "qiniu_token";

    /**
     * Http code
     */
    public static final int HTTP_SUCESS_CODE = 200;/*Http请求成功返回码*/
    public static final int HTTP_PASSWORD_ERROR_CODE = 400;/*登录账号或密码失败*/

    /**
     * 足迹采集方式
     */
    public enum TRACK_RECORD_MODE {
        WALK(0), RIDING(1), DRIVE(2);
        private int value;

        public int getValue() {
            return value;
        }

        TRACK_RECORD_MODE(int value) {
            this.value = value;
        }
    }
}
