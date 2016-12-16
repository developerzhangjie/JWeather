package com.example.devel.jweather.bean;

/**
 * Description:县级市的描述
 * Created by devel on 12/10/2016.
 */

public class Zone {
    private int id;
    private int CityID;
    private String ZoneName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityID() {
        return CityID;
    }

    public void setCityID(int cityID) {
        CityID = cityID;
    }

    public String getZoneName() {
        return ZoneName;
    }

    public void setZoneName(String zoneName) {
        ZoneName = zoneName;
    }
}
