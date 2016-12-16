package com.example.devel.jweather.bean;

import java.util.List;

/**
 * Description:今天的天气预报（心知天气）
 * Created by devel on 12/13/2016.
 */

public class WeatherInTime {

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * last_update : 2016-12-13T09:50:00+08:00
         * location : {"country":"CN","id":"WWMT5Q64CR3G","name":"青岛","path":"青岛,青岛,山东,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"}
         * now : {"code":"9","temperature":"3","text":"阴"}
         */

        private String last_update;
        private LocationBean location;
        private NowBean now;

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

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
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

        public static class NowBean {
            /**
             * code : 9
             * temperature : 3
             * text : 阴
             */

            private String code;
            private String temperature;
            private String text;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
