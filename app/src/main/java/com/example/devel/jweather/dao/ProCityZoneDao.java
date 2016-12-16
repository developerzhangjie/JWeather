package com.example.devel.jweather.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.devel.jweather.bean.City;
import com.example.devel.jweather.bean.Province;
import com.example.devel.jweather.bean.Zone;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:对省份，地级市，县级市的查询操作
 * Created by devel on 12/10/2016.
 */

public class ProCityZoneDao {

    /**
     * 查询省份
     *
     * @param context
     * @return 返回所有的省份对象
     */
    public static List<Province> queryProvince(Context context) {
        List<Province> list = new ArrayList<>();
        File file = new File(context.getFilesDir(), "china_province_city_zone.db");
        if (file.exists() && !file.isDirectory()) {
            SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = database.query("T_Province", null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String proName = cursor.getString(cursor.getColumnIndex("ProName"));
                    int proSort = cursor.getInt(cursor.getColumnIndex("ProSort"));
                    Province province = new Province();
                    province.setProName(proName);
                    province.setProSort(proSort);
                    list.add(province);
                }
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    /**
     * 查询省份
     *
     * @param context
     * @param inputProName 用户输入的文字
     * @return 返回以inputProName开头的省份
     */
    public static List<Province> queryPartProvince(Context context, String inputProName) {
        List<Province> list = new ArrayList<>();
        File file = new File(context.getFilesDir(), "china_province_city_zone.db");
        if (file.exists() && !file.isDirectory()) {
            SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = database.query("T_Province", null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String proName = cursor.getString(cursor.getColumnIndex("ProName"));
                    if (proName.startsWith(inputProName)) {
                        int proSort = cursor.getInt(cursor.getColumnIndex("ProSort"));
                        Province province = new Province();
                        province.setProName(proName);
                        province.setProSort(proSort);
                        list.add(province);
                    }
                }
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    /**
     * 根据省份查询相应的地级市
     *
     * @param context
     * @param provinceName 省份的名称
     * @return 返回相应的地级市对象
     */
    public static List<City> queryCity(Context context, String provinceName) {
        List<City> list = new ArrayList<>();
        File file = new File(context.getFilesDir(), "china_province_city_zone.db");
        if (file.exists() && !file.isDirectory()) {
            SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = database.rawQuery("select * from T_City where ProID=(select ProSort from T_Province where ProName=?)",
                    new String[]{provinceName});
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    City city = new City();
                    String cityName = cursor.getString(cursor.getColumnIndex("CityName"));
                    int proID = cursor.getInt(cursor.getColumnIndex("ProID"));
                    int citySort = cursor.getInt(cursor.getColumnIndex("CitySort"));
                    city.setCityName(cityName);
                    city.setProID(proID);
                    city.setCitySort(citySort);
                    list.add(city);
                }
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    /**
     * 查询以inputCityName开头的地级市的名字
     * @param context
     * @param inputCityName 用户输入的文字
     * @return 以inputCityName开头的地级市的名字
     */
    /**
     * 如果城市不重名，直接添加城市的名字
     * 如果有重名的，后面用括号注明省份的名字
     * 查一个城市，往数组里存储一次，存储之前需要跟数组中的所有元素比较，如果相等，则两者都需要注明省份
     */
    public static List<City> queryPartCity(Context context, String inputCityName) {
        List<City> list = new ArrayList<>();
        File file = new File(context.getFilesDir(), "china_province_city_zone.db");
        if (file.exists() && !file.isDirectory()) {
            SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = database.query("T_City", null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String cityName = cursor.getString(cursor.getColumnIndex("CityName"));
                    if (cityName.startsWith(inputCityName)) {
                        int proID = cursor.getInt(cursor.getColumnIndex("ProID"));
                        int citySort = cursor.getInt(cursor.getColumnIndex("CitySort"));
                        City city = new City();

                        city.setProID(proID);
                        city.setCitySort(citySort);
                        city.setCityName(cityName);
                        list.add(city);
                    }
                }
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    /**
     * 根据地级市查询相应的县级市
     *
     * @param context
     * @param cityName
     * @return
     */
    public static List<Zone> queryZone(Context context, String cityName) {
        List<Zone> list = new ArrayList<>();
        File file = new File(context.getFilesDir(), "china_province_city_zone.db");
        if (file.exists() && !file.isDirectory()) {
            SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = database.rawQuery("select * from T_Zone where CityID=(select CitySort from T_City where CityName=?)",
                    new String[]{cityName});
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    Zone zone = new Zone();
                    String zoneName = cursor.getString(cursor.getColumnIndex("ZoneName"));
                    int zoneID = cursor.getInt(cursor.getColumnIndex("ZoneID"));
                    int cityID = cursor.getInt(cursor.getColumnIndex("CityID"));
                    zone.setZoneName(zoneName);
                    zone.setId(zoneID);
                    zone.setCityID(cityID);
                    list.add(zone);
                }
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    /**
     * 查询以inputZoneName开头的县级市
     *
     * @param context
     * @param inputZoneName 用户输入的内容
     * @return 以inputZoneName开头的县级市
     */
    public static List<Zone> queryPartZone(Context context, String inputZoneName) {
        List<Zone> list = new ArrayList<>();
        File file = new File(context.getFilesDir(), "china_province_city_zone.db");
        if (file.exists() && !file.isDirectory()) {
            SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = database.query("T_Zone", null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    Zone zone = new Zone();
                    String zoneName = cursor.getString(cursor.getColumnIndex("ZoneName"));
                    if (zoneName.startsWith(inputZoneName)) {
                        int zoneID = cursor.getInt(cursor.getColumnIndex("ZoneID"));
                        int cityID = cursor.getInt(cursor.getColumnIndex("CityID"));
                        zone.setId(zoneID);
                        zone.setCityID(cityID);
                        zone.setZoneName(zoneName);
                        list.add(zone);
                    }
                }
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    /**
     * 根据地级市的名称查询所在的省
     *
     * @param context
     * @param inputCityName 地级市的名称
     * @return 地级市的名称查询所在的省
     */
    public static List<Province> queryProFromCity(Context context, String inputCityName) {
        List<Province> list = new ArrayList<>();
        List<Integer> proIds = new ArrayList<>();
        File file = new File(context.getFilesDir(), "china_province_city_zone.db");
        if (file.exists() && !file.isDirectory()) {
            SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            //            Cursor cursor = database.rawQuery("select ProName from T_Province where ProSort=(select ProID from T_City where CityName=?)",
            Cursor cursor = database.rawQuery("select ProID from T_City where CityName=?", new String[]{inputCityName});
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    proIds.add(cursor.getInt(cursor.getColumnIndex("ProID")));
                    for (int i = 0; i < proIds.size(); i++) {
                        String ProID = proIds.get(i).toString();
                        Cursor cursor1 = database.rawQuery("select * from T_Province where ProSort=?", new String[]{ProID});
                        if (cursor1 != null) {
                            while (cursor1.moveToNext()) {
                                Province province = new Province();
                                province.setProName(cursor.getString(cursor.getColumnIndex("ProSort")));
                                list.add(province);
                            }
                            cursor1.close();
                        }
                    }
                }
                cursor.close();
            }
            database.close();
        }
        return list;
    }

    /**
     * 根据县级市的名称查询所在的省
     *
     * @param context
     * @param inputZoneName 县级市的名称
     * @return 县级市的名称查询所在的省对象
     */
    public static List<Province> queryProFromZone(Context context, String inputZoneName) {
        List<Province> list = new ArrayList<>();
        List<Integer> cityIds = new ArrayList<>();
        List<Integer> proIds = new ArrayList<>();
        File file = new File(context.getFilesDir(), "china_province_city_zone.db");
        if (file.exists() && !file.isDirectory()) {
            SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            /*Cursor cursor = database.rawQuery("select proName from T_Province where ProSort=(select ProID from T_City where CitySort=(select CityID from T_Zone where ZoneName=?))",
                    new String[]{inputZoneName});*/
            Cursor cursor = database.rawQuery("select CityID from T_Zone where ZoneName", new String[]{inputZoneName});
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    cityIds.add(cursor.getInt(cursor.getColumnIndex("CityID")));
                    for (int i = 0; i < cityIds.size(); i++) {
                        String CityID = cityIds.get(i).toString();
                        Cursor cursor1 = database.rawQuery("select ProID from T_City where CitySort=?", new String[]{CityID});
                        if (cursor1 != null) {
                            while (cursor1.moveToNext()) {
                                proIds.add(cursor1.getInt(cursor1.getColumnIndex("ProID")));
                                for (int j = 0; j < proIds.size(); j++) {
                                    String ProID = proIds.get(i).toString();
                                    Cursor cursor2 = database.rawQuery("select ProName from T_Province where ProSort=?", new String[]{ProID});
                                    if (cursor2 != null) {
                                        while (cursor2.moveToNext()) {
                                            Province province = new Province();
                                            province.setProName(cursor.getString(cursor.getColumnIndex("ProName")));
                                            list.add(province);
                                        }
                                        cursor2.close();
                                    }

                                }
                            }
                            cursor1.close();
                        }
                    }
                }
                cursor.close();
            }
            database.close();
        }
        return list;
    }

}
