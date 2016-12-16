package com.example.devel.jweather.utils;

import android.util.Log;

/**
 * Description:打log的工具类，可以通过标示进行控制输出
 * Created by devel on 12/14/2016.
 */

public class LogUtils {
    private static boolean FLAG = true;
    private static String TAG = "TAG";

    public static void logD(Object data) {
        if (FLAG) {
            Log.d(TAG, "logD: " + data);
        }
    }
}
