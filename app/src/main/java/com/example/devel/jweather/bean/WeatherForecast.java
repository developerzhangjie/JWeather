package com.example.devel.jweather.bean;

import java.util.List;

/**
 * Description:今天和未来两天的天气预报（心知天气）
 * Created by devel on 12/14/2016.
 */

public class WeatherForecast {
    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * daily : [{"code_day":"4","code_night":"0","date":"2016-12-14","high":"3","low":"-3","precip":"","text_day":"多云","text_night":"晴","wind_direction":"北","wind_direction_degree":"0","wind_scale":"4","wind_speed":"20"},{"code_day":"0","code_night":"0","date":"2016-12-15","high":"4","low":"-3","precip":"","text_day":"晴","text_night":"晴","wind_direction":"北","wind_direction_degree":"0","wind_scale":"3","wind_speed":"15"},{"code_day":"0","code_night":"0","date":"2016-12-16","high":"6","low":"0","precip":"","text_day":"晴","text_night":"晴","wind_direction":"北","wind_direction_degree":"0","wind_scale":"3","wind_speed":"15"}]
         * last_update : 2016-12-14T08:00:00+08:00
         * location : {"country":"CN","id":"WWMT5Q64CR3G","name":"青岛","path":"青岛,青岛,山东,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"}
         */

        private String last_update;
        private LocationBean location;
        private List<DailyBean> daily;

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public List<DailyBean> getDaily() {
            return daily;
        }

        public void setDaily(List<DailyBean> daily) {
            this.daily = daily;
        }

        public static class LocationBean {
            /**
             * country : CN
             * id : WWMT5Q64CR3G
             * name : 青岛
             * path : 青岛,青岛,山东,中国
             * timezone : Asia/Shanghai
             * timezone_offset : +08:00
             */

            private String country;
            private String id;
            private String name;
            private String path;
            private String timezone;
            private String timezone_offset;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getTimezone_offset() {
                return timezone_offset;
            }

            public void setTimezone_offset(String timezone_offset) {
                this.timezone_offset = timezone_offset;
            }
        }

        public static class DailyBean {
            /**
             * code_day : 4
             * code_night : 0
             * date : 2016-12-14
             * high : 3
             * low : -3
             * precip :
             * text_day : 多云
             * text_night : 晴
             * wind_direction : 北
             * wind_direction_degree : 0
             * wind_scale : 4
             * wind_speed : 20
             */

            private String code_day;
            private String code_night;
            private String date;
            private String high;
            private String low;
            private String precip;
            private String text_day;
            private String text_night;
            private String wind_direction;
            private String wind_direction_degree;
            private String wind_scale;
            private String wind_speed;

            public String getCode_day() {
                return code_day;
            }

            public void setCode_day(String code_day) {
                this.code_day = code_day;
            }

            public String getCode_night() {
                return code_night;
            }

            public void setCode_night(String code_night) {
                this.code_night = code_night;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getPrecip() {
                return precip;
            }

            public void setPrecip(String precip) {
                this.precip = precip;
            }

            public String getText_day() {
                return text_day;
            }

            public void setText_day(String text_day) {
                this.text_day = text_day;
            }

            public String getText_night() {
                return text_night;
            }

            public void setText_night(String text_night) {
                this.text_night = text_night;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_direction_degree() {
                return wind_direction_degree;
            }

            public void setWind_direction_degree(String wind_direction_degree) {
                this.wind_direction_degree = wind_direction_degree;
            }

            public String getWind_scale() {
                return wind_scale;
            }

            public void setWind_scale(String wind_scale) {
                this.wind_scale = wind_scale;
            }

            public String getWind_speed() {
                return wind_speed;
            }

            public void setWind_speed(String wind_speed) {
                this.wind_speed = wind_speed;
            }
        }
    }
}
