package com.example.devel.jweather.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.devel.jweather.R;
import com.example.devel.jweather.bean.WeatherForecast;
import com.example.devel.jweather.bean.WeatherInTime;
import com.example.devel.jweather.db.Constant;
import com.example.devel.jweather.db.WeatherInTimeHelper;
import com.example.devel.jweather.fragment.RightCityFragment;
import com.example.devel.jweather.utils.LogUtils;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;


/**
 * Description:主界面
 * Created by devel on 12/9/2016.
 */

public class MainActivity extends SlidingFragmentActivity {
    private Context mContext;
    private String TAG = "TAG";
    private SlidingMenu mSlidingMenu;
    private RightCityFragment rightCityFragment;
    private String cityFromSearch;
    private String encCityNameFromSearch;
    private TextView showWeather;
    private int[] weatherColors = new int[]{R.color.sun, R.color.rain, R.color.windy, R.color.cloud, R.color.snow, R.color.haze, R.color.fog, R.color.otherWeather};
    private LinearLayout weatherLayout;
    private TextView address;
    private int year;
    private int month;
    private int day;
    private TextView date;
    private String textInTime;
    private String temperatureInTime;
    private SQLiteDatabase database;
    private ContentValues contentValues;
    private String LINE_SEPARATOR = System.getProperty("line.separator", "/n");
    private TextView highTem;
    private TextView lowTem;
    private TextView precipInfo;
    private TextView windInfo;
    private String city;
    private TextView showTemperature;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        Log.d(TAG, "onCreate: ----------------");
        WeatherInTimeHelper weatherInTimeHelper = new WeatherInTimeHelper(mContext);
        database = weatherInTimeHelper.getReadableDatabase();
        Cursor cursor = database.query(Constant.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToLast()) {
                String string = cursor.getString(cursor.getColumnIndex(Constant.CITY));
                flushWeatherByCityName(string);
            }
        }
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        weatherLayout = (LinearLayout) findViewById(R.id.weatherLayout);
        address = (TextView) findViewById(R.id.address);
        date = (TextView) findViewById(R.id.date);
        date.setText(year + "/" + month + "/" + day);
        highTem = (TextView) findViewById(R.id.highTem);
        lowTem = (TextView) findViewById(R.id.lowTem);
        precipInfo = (TextView) findViewById(R.id.nightWeather);
        windInfo = (TextView) findViewById(R.id.windInfo);
        //设置旁边的菜单页面
        setBehindContentView(R.layout.slidingmenu_layout);
        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.RIGHT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setBehindOffsetRes(R.dimen.sm_width);
        rightCityFragment = new RightCityFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu, rightCityFragment, "RIGHT").commit();
        showWeather = (TextView) findViewById(R.id.showWeather);
        showTemperature = (TextView) findViewById(R.id.showTemperature);

    }

    public void queryWeatherByCityName(final String cityFromSearch) {
        Log.d(TAG, "queryWeatherByCityName: 执行了查询天气");
        if (null != cityFromSearch) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            address.setText(cityFromSearch);
                        }
                    });
                    try {
                        encCityNameFromSearch = URLEncoder.encode(cityFromSearch, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                    final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    String url = "https://api.thinkpage.cn/v3/weather/now.json?key=mtylzwv6lqiqdqa5&location=" + encCityNameFromSearch + "&language=zh-Hans&unit=c";
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            WeatherInTime weatherInTime = gson.fromJson(response.toString(), WeatherInTime.class);
                            textInTime = weatherInTime.getResults().get(0).getNow().getText();
                            temperatureInTime = weatherInTime.getResults().get(0).getNow().getTemperature();
                            if (textInTime.contains("晴")) {
                                weatherLayout.setBackgroundResource(weatherColors[0]);
                            } else if (textInTime.contains("雨")) {
                                weatherLayout.setBackgroundResource(weatherColors[1]);
                            } else if (textInTime.contains("风")) {
                                weatherLayout.setBackgroundResource(weatherColors[2]);
                            } else if (textInTime.contains("云")) {
                                weatherLayout.setBackgroundResource(weatherColors[3]);
                            } else if (textInTime.contains("雪")) {
                                weatherLayout.setBackgroundResource(weatherColors[4]);
                            } else if (textInTime.contains("霾")) {
                                weatherLayout.setBackgroundResource(weatherColors[5]);
                            } else if (textInTime.contains("雾")) {
                                weatherLayout.setBackgroundResource(weatherColors[6]);
                            } else {
                                weatherLayout.setBackgroundResource(weatherColors[7]);
                            }
                            //向Fragment的ListView中添加城市、天气信息，并刷新
                            contentValues.put(Constant.TEMPERATURE, temperatureInTime);
                            showTemperature.setText(temperatureInTime + "℃");

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "onErrorResponse: ");
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                    String URL2 = "https://api.thinkpage.cn/v3/weather/daily.json?key=mtylzwv6lqiqdqa5&location=" + encCityNameFromSearch + "&language=zh-Hans&unit=c&start=0&days=3";
                    RequestQueue requestQueue2 = Volley.newRequestQueue(MainActivity.this);
                    JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(URL2, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Gson gson = new Gson();
                            WeatherForecast weatherForecast = gson.fromJson(response.toString(), WeatherForecast.class);
                            String highTemp0 = weatherForecast.getResults().get(0).getDaily().get(0).getHigh();
                            String lowTemp0 = weatherForecast.getResults().get(0).getDaily().get(0).getLow();
                            String date0 = weatherForecast.getResults().get(0).getDaily().get(0).getDate();
                            String text_day0 = weatherForecast.getResults().get(0).getDaily().get(0).getText_day();
                            String text_night0 = weatherForecast.getResults().get(0).getDaily().get(0).getText_night();
                            String wind_direction0 = weatherForecast.getResults().get(0).getDaily().get(0).getWind_direction();
                            String wind_scale0 = weatherForecast.getResults().get(0).getDaily().get(0).getWind_scale();

                            if (text_day0.contains("晴")) {
                                weatherLayout.setBackgroundResource(weatherColors[0]);
                            } else if (text_day0.contains("雨")) {
                                weatherLayout.setBackgroundResource(weatherColors[1]);
                            } else if (text_day0.contains("风")) {
                                weatherLayout.setBackgroundResource(weatherColors[2]);
                            } else if (text_day0.contains("云")) {
                                weatherLayout.setBackgroundResource(weatherColors[3]);
                            } else if (text_day0.contains("雪")) {
                                weatherLayout.setBackgroundResource(weatherColors[4]);
                            } else if (text_day0.contains("霾")) {
                                weatherLayout.setBackgroundResource(weatherColors[5]);
                            } else if (text_day0.contains("雾")) {
                                weatherLayout.setBackgroundResource(weatherColors[6]);
                            } else {
                                weatherLayout.setBackgroundResource(weatherColors[7]);
                            }

                            showWeather.setText(text_day0);
                            highTem.setText(highTemp0 + "℃");
                            lowTem.setText(lowTemp0 + "℃");
                            precipInfo.setText(text_night0);
                            windInfo.setText(wind_direction0 + "风" + LINE_SEPARATOR + wind_scale0 + "级");
                            Log.d(TAG, "onResponse: 当前温度" + temperatureInTime);

                            //向Fragment的ListView中添加城市、天气信息，并刷新

                            contentValues.put(Constant.WEATHER, text_day0);
                            Log.d(TAG, "onResponse:天气 " + text_day0);
                            contentValues.put(Constant.HIGH_TEMPERATURE, highTemp0);
                            Log.d(TAG, "onActivityResult: 最高温度 " + highTemp0);
                            contentValues.put(Constant.LOW_TEMPERATURE, lowTemp0);
                            Log.d(TAG, "onResponse: 最低气温 " + lowTemp0);
                            database.insert(Constant.TABLE_NAME, null, contentValues);

                            rightCityFragment.addCityWeatherInfo();
                            String highTemp1 = weatherForecast.getResults().get(0).getDaily().get(1).getHigh();
                            String lowTemp1 = weatherForecast.getResults().get(0).getDaily().get(1).getLow();
                            String date1 = weatherForecast.getResults().get(0).getDaily().get(1).getDate();
                            String text_day1 = weatherForecast.getResults().get(0).getDaily().get(1).getText_day();
                            String text_night1 = weatherForecast.getResults().get(0).getDaily().get(1).getText_night();
                            String wind_direction1 = weatherForecast.getResults().get(0).getDaily().get(1).getWind_direction();
                            String wind_scale1 = weatherForecast.getResults().get(0).getDaily().get(1).getWind_scale();

                            String highTemp2 = weatherForecast.getResults().get(0).getDaily().get(2).getHigh();
                            String lowTemp2 = weatherForecast.getResults().get(0).getDaily().get(2).getLow();
                            String date2 = weatherForecast.getResults().get(0).getDaily().get(2).getDate();
                            String text_day2 = weatherForecast.getResults().get(0).getDaily().get(2).getText_day();
                            String text_night2 = weatherForecast.getResults().get(0).getDaily().get(2).getText_night();
                            String wind_direction2 = weatherForecast.getResults().get(0).getDaily().get(2).getWind_direction();
                            String wind_scale2 = weatherForecast.getResults().get(0).getDaily().get(2).getWind_scale();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            LogUtils.logD("错误");
                        }
                    });
                    requestQueue2.add(jsonObjectRequest2);
                }
            }).start();
        }
    }

    public void flushWeatherByCityName(final String cityFromSearch) {
        Log.d(TAG, "flushWeatherByCityName: 执行了刷新天气");
        if (null != cityFromSearch) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            address.setText(cityFromSearch);
                        }
                    });
                    try {
                        encCityNameFromSearch = URLEncoder.encode(cityFromSearch, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    String url = "https://api.thinkpage.cn/v3/weather/now.json?key=mtylzwv6lqiqdqa5&location=" + encCityNameFromSearch + "&language=zh-Hans&unit=c";
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            WeatherInTime weatherInTime = gson.fromJson(response.toString(), WeatherInTime.class);
                            temperatureInTime = weatherInTime.getResults().get(0).getNow().getTemperature();
                            showTemperature.setText(temperatureInTime + "℃");
                            Log.d(TAG, "onResponse: 当前温度  " + temperatureInTime);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "onErrorResponse: ");
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                    String URL2 = "https://api.thinkpage.cn/v3/weather/daily.json?key=mtylzwv6lqiqdqa5&location=" + encCityNameFromSearch + "&language=zh-Hans&unit=c&start=0&days=3";
                    RequestQueue requestQueue2 = Volley.newRequestQueue(MainActivity.this);
                    JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(URL2, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            WeatherForecast weatherForecast = gson.fromJson(response.toString(), WeatherForecast.class);
                            String highTemp0 = weatherForecast.getResults().get(0).getDaily().get(0).getHigh();
                            String lowTemp0 = weatherForecast.getResults().get(0).getDaily().get(0).getLow();
                            String date0 = weatherForecast.getResults().get(0).getDaily().get(0).getDate();
                            String text_day0 = weatherForecast.getResults().get(0).getDaily().get(0).getText_day();
                            String text_night0 = weatherForecast.getResults().get(0).getDaily().get(0).getText_night();
                            String wind_direction0 = weatherForecast.getResults().get(0).getDaily().get(0).getWind_direction();
                            String wind_scale0 = weatherForecast.getResults().get(0).getDaily().get(0).getWind_scale();
                            if (text_day0.contains("晴")) {
                                weatherLayout.setBackgroundResource(weatherColors[0]);
                            } else if (text_day0.contains("雨")) {
                                weatherLayout.setBackgroundResource(weatherColors[1]);
                            } else if (text_day0.contains("风")) {
                                weatherLayout.setBackgroundResource(weatherColors[2]);
                            } else if (text_day0.contains("云")) {
                                weatherLayout.setBackgroundResource(weatherColors[3]);
                            } else if (text_day0.contains("雪")) {
                                weatherLayout.setBackgroundResource(weatherColors[4]);
                            } else if (text_day0.contains("霾")) {
                                weatherLayout.setBackgroundResource(weatherColors[5]);
                            } else if (text_day0.contains("雾")) {
                                weatherLayout.setBackgroundResource(weatherColors[6]);
                            } else {
                                weatherLayout.setBackgroundResource(weatherColors[7]);
                            }
                            showWeather.setText(text_day0);
                            Log.d(TAG, "onResponse: temperatureInTime  " + temperatureInTime);
                            highTem.setText(highTemp0 + "℃");
                            lowTem.setText(lowTemp0 + "℃");
                            precipInfo.setText(text_night0);
                            windInfo.setText(wind_direction0 + "风" + LINE_SEPARATOR + wind_scale0 + "级");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            LogUtils.logD("错误");

                        }
                    });
                    requestQueue2.add(jsonObjectRequest2);
                }
            }).start();
        }
    }

    public void selectFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu, fragment).commit();
        mSlidingMenu.toggle();
    }

    public RightCityFragment getRightCityFragment() {
        return (RightCityFragment) getSupportFragmentManager().findFragmentByTag("RIGHT");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ------------");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: -----------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: -----------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: --------------");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mSlidingMenu.toggle();
        if (requestCode == 0 && resultCode == RESULT_OK) {
            city = data.getStringExtra("CITY");
            /**
             * 调用接口，根据城市名查询天气
             */
            queryWeatherByCityName(city);
            /**
             * 在这里将天气数据写入数据库中
             */
            contentValues = new ContentValues();
            Log.d(TAG, "onActivityResult: 要添加的城市 " + city);
            contentValues.put(Constant.CITY, city);
        }
    }
}
