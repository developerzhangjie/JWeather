package com.example.devel.jweather.bean;

/**
 * Description:地级市的描述
 * Created by devel on 12/10/2016.
 */

public class City {
    private int ProID;
    private int CitySort;
    private String CityName;

    public int getProID() {
        return ProID;
    }

    public void setProID(int proID) {
        ProID = proID;
    }

    public int getCitySort() {
        return CitySort;
    }

    public void setCitySort(int citySort) {
        CitySort = citySort;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
}
