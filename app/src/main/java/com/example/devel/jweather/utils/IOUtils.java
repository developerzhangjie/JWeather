package com.example.devel.jweather.utils;

import android.database.Cursor;

import java.io.Closeable;

/**
 * Description:复制的别人的，进行关流操作
 * Created by devel on 12/10/2016.
 */

public class IOUtils {

    private IOUtils() {
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
            }
        }
    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable e) {
            }
        }
    }
}
