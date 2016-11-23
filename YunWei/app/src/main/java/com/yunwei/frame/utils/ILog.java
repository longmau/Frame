package com.yunwei.frame.utils;

import android.util.Log;

import com.yunwei.frame.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Package: com.jinggan.dear.utils
 * @Description: 日志工具类
 * @author: Aaron
 * @date: 2015-11-19
 * @Time: 15:13
 * @version: V1.0
 */
public class ILog {

    /**
     * 用于自定义TAG
     */
    public static String LOG_TAG = "tag";
    /**
     * 日志前缀
     */
    public static String LOG_PRE = " <||> ";
    /**
     * 安全级别日志,true:不输出或保存日志，false:可输出或保存日志
     */
    public static boolean IS_SECURITY_LOG = true;
    /**
     * 是否输出Log的位置，true:输出；false:不输出
     */
    public static boolean IS_LOG_POSITION = true;
    /**
     * 日志写入文件开关
     */
    private static boolean WRITE_TO_FILE = true;
    /**
     * 日志分隔字符
     */
    private static final String LOG_SPLIT = "  \t<==>  ";

    /**
     * 输出调试Log
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void d(String tag, String msg) {
        if (!IS_SECURITY_LOG || BuildConfig.LOGSWITCH) {
            return;
        }
        tag = LOG_TAG == null ? tag : LOG_TAG;
        String logMsg = (msg == null ? "" : msg);

        if (IS_LOG_POSITION) {
            logMsg = getPositionInfo() + LOG_SPLIT + logMsg;
        }

        if (WRITE_TO_FILE) {
            writeLogtoFile(1 + "", tag, msg);
        }
        Log.d(LOG_PRE + tag, logMsg);
    }

    /**
     * 输出浏览级别Log
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void v(String tag, String msg) {
        if (!IS_SECURITY_LOG || BuildConfig.LOGSWITCH) {
            return;
        }
        tag = LOG_TAG == null ? tag : LOG_TAG;
        String logMsg = (msg == null ? "" : msg);

        if (IS_LOG_POSITION) {
            logMsg = getPositionInfo() + LOG_SPLIT + logMsg;
        }
        if (WRITE_TO_FILE) {
            writeLogtoFile(2 + "", tag, msg);
        }
        Log.v(LOG_PRE + tag, logMsg);
    }

    /**
     * 输出警告级别Log
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void w(String tag, String msg) {
        if (!IS_SECURITY_LOG || BuildConfig.LOGSWITCH) {
            return;
        }
        tag = LOG_TAG == null ? tag : LOG_TAG;
        String logMsg = (msg == null ? "" : msg);

        if (IS_LOG_POSITION) {
            logMsg = getPositionInfo() + LOG_SPLIT + logMsg;
        }
        if (WRITE_TO_FILE) {
            writeLogtoFile(3 + "", tag, msg);
        }
        Log.w(LOG_PRE + tag, logMsg);
    }

    /**
     * 输出错误级别Log
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void e(String tag, String msg) {
        if (!IS_SECURITY_LOG || BuildConfig.LOGSWITCH) {
            return;
        }
        tag = LOG_TAG == null ? tag : LOG_TAG;
        String logMsg = (msg == null ? "" : msg);

        if (IS_LOG_POSITION) {
            logMsg = getPositionInfo() + LOG_SPLIT + logMsg;
        }
        if (WRITE_TO_FILE) {
            writeLogtoFile(4 + "", tag, msg);
        }
        Log.e(LOG_PRE + tag, logMsg);
    }

    /**
     * 输出信息级别
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (!IS_SECURITY_LOG || BuildConfig.LOGSWITCH) {
            return;
        }
        tag = LOG_TAG == null ? tag : LOG_TAG;
        String logMsg = (msg == null ? "" : msg);

        if (IS_LOG_POSITION) {
            logMsg = getPositionInfo() + LOG_SPLIT + logMsg;
        }
        if (WRITE_TO_FILE) {
            writeLogtoFile(5 + "", tag, msg);
        }
        Log.i(LOG_PRE + tag, logMsg);
    }


    /**
     * 获取Log信息
     *
     * @return
     */
    private static String getPositionInfo() {
        StackTraceElement element = new Throwable().getStackTrace()[2];
        return element.getFileName() + " ；Line " + element.getLineNumber() + " ；Method: " + element.getMethodName();
    }

    /**
     * 打开日志文件并写入日志
     *
     * @return
     **/
    private static void writeLogtoFile(String mylogtype, String tag, String text) {// 新建或打开日志文件
    }
}
