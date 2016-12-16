package com.example.devel.jweather;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private String TAG = "TAG";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.devel.jweather", appContext.getPackageName());
    }

    /*public void testDb() {
        List<Province> list = ProCityZoneDao.queryProvince(getContext());
        String proName = list.get(0).getProName();
        Log.d(TAG, "testDb: " + proName);
    }*/
}
