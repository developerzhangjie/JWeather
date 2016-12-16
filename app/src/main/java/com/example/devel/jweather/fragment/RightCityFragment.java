package com.example.devel.jweather.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devel.jweather.R;
import com.example.devel.jweather.activity.MainActivity;
import com.example.devel.jweather.activity.SearchCityActivity;
import com.example.devel.jweather.bean.WeatherCity;
import com.example.devel.jweather.db.Constant;
import com.example.devel.jweather.db.WeatherInTimeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:右侧slidingMenu
 * Created by devel on 12/12/2016.
 */

public class RightCityFragment extends Fragment {
    private String TAG = "TAGA";
    /**
     * 这个list里面需要放包含天气信息的城市对象
     */
    private List<WeatherCity> cityList;
    private ListView cityLv;
    private Button addCity;
    private MyAdapter myAdapter;
    private WeatherInTimeHelper weatherInTimeHelper;
    private SQLiteDatabase database;
    private String LINE_SEPARATOR = System.getProperty("line.separator", "/n");
    private int[] weatherColors = new int[]{R.color.sun, R.color.rain, R.color.windy, R.color.cloud, R.color.snow, R.color.haze, R.color.fog, R.color.otherWeather};
    private String high_temperature;
    private String low_temperature;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach:+++++++++++++++++++= ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherInTimeHelper = new WeatherInTimeHelper(getContext());
        database = weatherInTimeHelper.getReadableDatabase();
        cityList = new ArrayList<>();
        Log.d(TAG, "onCreate: +++++++==");

    }

    /**
     * 向ListView中添加服务器返回的城市、天气等信息，并进行刷新显示
     */
    public void addCityWeatherInfo() {
        cityList.clear();
        Cursor cursor = database.query(Constant.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String city = cursor.getString(cursor.getColumnIndex(Constant.CITY));
                String weather = cursor.getString(cursor.getColumnIndex(Constant.WEATHER));
                String temperature = cursor.getString(cursor.getColumnIndex(Constant.TEMPERATURE));
                low_temperature = cursor.getString(cursor.getColumnIndex(Constant.LOW_TEMPERATURE));
                high_temperature = cursor.getString(cursor.getColumnIndex(Constant.HIGH_TEMPERATURE));
                Log.d(TAG, "addCityWeatherInfo: 数据库城市" + city);
                Log.d(TAG, "addCityWeatherInfo: 数据库天气" + weather);
                Log.d(TAG, "addCityWeatherInfo: 数据库最高温度" + high_temperature);

                WeatherCity weatherCity = new WeatherCity();
                weatherCity.setCityName(city);
                weatherCity.setWeather(weather);
                weatherCity.setTemperature(temperature);
                weatherCity.setHighTemperature(high_temperature);
                weatherCity.setLowTemperature(low_temperature);
                cityList.add(weatherCity);
            }
        }
        if (myAdapter == null) {
            myAdapter = new MyAdapter();
            Log.d(TAG, "addCityWeatherInfo: new MyAdapter()");
        } else {
            myAdapter.notifyDataSetChanged();
            Log.d(TAG, "addCityWeatherInfo: notifyDataSetChanged");
        }
        cityLv.setAdapter(myAdapter);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_added, null);
        cityLv = (ListView) view.findViewById(R.id.lv_city);
        addCity = (Button) view.findViewById(R.id.addCity);
        Log.d(TAG, "onCreateView: +++++++++++++++++++++++++==");
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchCityActivity.class);
                getActivity().startActivityForResult(intent, 0);
            }
        });
        /**
         * 调用此方法是解决初始化时，listView不显示数据的bug
         */
        addCityWeatherInfo();
        cityLv.setAdapter(myAdapter);
        cityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switchFragment(((MainActivity) getActivity()).getRightCityFragment());
                ((MainActivity) getActivity()).flushWeatherByCityName(cityList.get(i).getCityName());
            }
        });

        cityLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final String s = cityList.get(i).getCityName();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示").setMessage("确定删除该城市？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        database.delete(Constant.TABLE_NAME, Constant.CITY + "=?", new String[]{s});
                        cityList.remove(i);
                        myAdapter.notifyDataSetChanged();
                        switchFragment(((MainActivity) getActivity()).getRightCityFragment());
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        switchFragment(((MainActivity) getActivity()).getRightCityFragment());
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated:+++++++== ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:***************************");


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume:***************************");


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause:+++++++== ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: +++++++==");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: +++++++==");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:+++++++== ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach:+++++++== ");
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cityList.size();
        }

        @Override
        public Object getItem(int i) {
            return cityList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            View view;
            if (convertView == null) {
                view = View.inflate(getContext(), R.layout.city_added_iem, null);
                viewHolder = new ViewHolder();
                viewHolder.cityName = (TextView) view.findViewById(R.id.cityName);
                viewHolder.cityWeather = (TextView) view.findViewById(R.id.cityWeather);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            String cityName = cityList.get(i).getCityName();
            String highTemperature = cityList.get(i).getHighTemperature();
            String lowTemperature = cityList.get(i).getLowTemperature();
            String weather = cityList.get(i).getWeather();
            if (weather.contains("晴")) {
                viewHolder.cityWeather.setBackgroundResource(weatherColors[0]);
            } else if (weather.contains("雨")) {
                viewHolder.cityWeather.setBackgroundResource(weatherColors[1]);
            } else if (weather.contains("风")) {
                viewHolder.cityWeather.setBackgroundResource(weatherColors[2]);
            } else if (weather.contains("云")) {
                viewHolder.cityWeather.setBackgroundResource(weatherColors[3]);
            } else if (weather.contains("雪")) {
                viewHolder.cityWeather.setBackgroundResource(weatherColors[4]);
            } else if (weather.contains("霾")) {
                viewHolder.cityWeather.setBackgroundResource(weatherColors[5]);
            } else if (weather.contains("雾")) {
                viewHolder.cityWeather.setBackgroundResource(weatherColors[6]);
            } else {
                viewHolder.cityWeather.setBackgroundResource(weatherColors[7]);
            }
            viewHolder.cityName.setText(cityName);
            viewHolder.cityWeather.setText(weather + LINE_SEPARATOR + lowTemperature + "/" + highTemperature);
            return view;
        }
    }

    class ViewHolder {
        TextView cityName;
        TextView cityWeather;
    }

    private void switchFragment(Fragment fragment) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).selectFragment(fragment);
        }
    }
}