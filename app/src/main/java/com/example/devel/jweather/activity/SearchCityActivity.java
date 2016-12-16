package com.example.devel.jweather.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.devel.jweather.R;
import com.example.devel.jweather.adapter.MyAdapter;
import com.example.devel.jweather.bean.City;
import com.example.devel.jweather.dao.ProCityZoneDao;
import com.example.devel.jweather.db.Constant;
import com.example.devel.jweather.db.WeatherInTimeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:搜索城市并添加的界面
 * Created by devel on 12/12/2016.
 */

public class SearchCityActivity extends Activity {
    private String TAG = "TAG";
    private SearchView searchView;
    private Button cancelsearch;
    private ListView citiesInSearch;
    private List<String> cities;
    private Context mContext;
    private CityAdapter cityAdapter;
    private WeatherInTimeHelper weatherInTimeHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_city);
        mContext = this;
        initView();

        weatherInTimeHelper = new WeatherInTimeHelper(mContext);
        database = weatherInTimeHelper.getWritableDatabase();
        Log.d(TAG, "onCreate: 来自SearchCityActivity");

    }

    private void initView() {
        cities = new ArrayList<>();
        cancelsearch = (Button) findViewById(R.id.cancel_search);
        searchView = (SearchView) findViewById(R.id.searchView);
        citiesInSearch = (ListView) findViewById(R.id.citiesInSearch);
        searchView.setQueryHint("输入要查询的城市");
        searchView.setIconifiedByDefault(false);
        if (cityAdapter == null) {
            cityAdapter = new CityAdapter(mContext, cities);
            citiesInSearch.setAdapter(cityAdapter);
        } else {
            cityAdapter.notifyDataSetChanged();
        }

        cancelsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //            private String cityNameInList;
            /**
             * 当输入内容时调用此方法
             * @param newText 输入的内容
             * @return
             */
            /**
             * 有内容输入时，就遍历数据库，以输入内容开始的城市(地级市+县级市+直辖市)，都在下面的listView中显示
             * 点击城市的名字，会添加到fragment中，然后跳转到主界面，主界面切换成该城市的天气
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                cities.clear();
                Log.d(TAG, "onQueryTextChange: 用户输入了" + newText);
                if (!TextUtils.isEmpty(newText)) {
                    //遍历数据库,进行实时显示
                   /* List<Province> provinceList = ProCityZoneDao.queryPartProvince(mContext, newText);
                    for (int i = 0; i < provinceList.size(); i++) {
                        String proName = provinceList.get(i).getProName();
                        Log.d(TAG, "onQueryTextChange: " + proName);
                        cities.add(proName);
                        cityAdapter.notifyDataSetChanged();
                    }*/
                    List<City> citiesList = ProCityZoneDao.queryPartCity(mContext, newText);
                    for (int i = 0; i < citiesList.size(); i++) {
                        String cityName = citiesList.get(i).getCityName();
                        int proID = citiesList.get(i).getProID();
                        //                        City cityInList = citiesList.get(i);
                        //                        cityNameInList = cityInList.getCityName();
                        //                        if (cityNameInList.equals(cityName)) {
                        //                            List<Province> provinces = ProCityZoneDao.queryProFromCity(mContext, cityName);
                        //                            for (int j = 0; j < provinces.size(); j++) {
                        //                                String proName = provinces.get(i).getProName();
                        //                                cityName = cityName + " " + proName;
                        //                                //                                citiesList.remove(i);
                        //                                //                                cityNameInList = cityNameInList + " " + proName;
                        //                                //                                cityInList.setCityName(cityNameInList);
                        //                                //                                citiesList.add(cityInList);
                        //                                cities.add(cityName);
                        //                            }
                        //                            //                            citiesList.add(cityInList);
                        //                        }
                        cities.add(cityName);
                        cityAdapter.notifyDataSetChanged();
                        Log.d(TAG, "onQueryTextChange: " + cityName + "  地级市 " + proID);
                    }
                    //                    List<Zone> zoneList = ProCityZoneDao.queryPartZone(mContext, newText);
                    //                    for (int i = 0; i < zoneList.size(); i++) {
                    //                        String zoneName = zoneList.get(i).getZoneName();
                    //                        int cityID = zoneList.get(i).getCityID();
                    //                        //                        Zone zoneInList = zoneList.get(i);
                    //                        //                        String zoneNameInList = zoneInList.getZoneName();
                    //                        //                        if (zoneNameInList.equals(zoneName)) {
                    //                        //                            zoneList.remove(i);
                    //                        //                            List<Province> proList1 = ProCityZoneDao.queryProFromZone(mContext, zoneName);
                    //                        //                            for (int j = 0; j < proList1.size(); j++) {
                    //                        //                                String proName = proList1.get(i).getProName();
                    //                        //                                zoneName = zoneName + " " + proName;
                    //                        //                                cities.add(zoneName);
                    //                        //                            }
                    //                        //                            //                            zoneName = zoneName + " " + proName1;
                    //                        //                            //                            String proName2 = ProCityZoneDao.queryProFromZone(mContext, zoneNameInList);
                    //                        //                            //                            zoneNameInList = zoneNameInList + " " + proName2;
                    //                        //                            //                            zoneInList.setZoneName(zoneNameInList);
                    //                        //                            //                            zoneList.add(zoneInList);
                    //                        //                        }
                    //                        cities.add(zoneName);
                    //                        cityAdapter.notifyDataSetChanged();
                    //                    }
                } else {
                    //如果搜索框的内容为空，就清空集合并刷新
                    cities.clear();
                    cityAdapter.notifyDataSetChanged();
                }
                return false;
            }

            /**
             * 点击搜索按钮时点用此方法
             * @param query 要搜索的内容
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: 搜索" + query);
                return true;
            }
        });

        citiesInSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: 点击了城市");
                Intent intent = new Intent();
                intent.putExtra("CITY", cities.get(i));
                database.delete(Constant.TABLE_NAME, Constant.CITY + "=?", new String[]{cities.get(i)});
                Log.d(TAG, "onItemClick: 删除了" + cities.get(i));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private class CityAdapter extends MyAdapter {
        public CityAdapter(Context context, List list) {
            super(context, list);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(mContext, R.layout.lv_item, null);
            }
            TextView text = (TextView) view.findViewById(R.id.tv_item);
            text.setText(cities.get(i));
            return view;
        }
    }
}
