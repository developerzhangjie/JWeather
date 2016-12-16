package com.example.devel.jweather.bean;

import java.util.List;

/**
 * Description:今天的天气预报（mob）
 * Created by devel on 12/9/2016.
 */

public class Weather {
    /**
     * msg : success
     * result : [{"airCondition":"良","city":"青岛","coldIndex":"多发期","date":"2016-12-09","distrct":"青岛","dressingIndex":"薄冬衣","exerciseIndex":"不适宜","future":[{"date":"2016-12-09","night":"多云","temperature":"1°C","week":"今天","wind":"北风 3～4级"},{"date":"2016-12-10","dayTime":"多云","night":"多云","temperature":"6°C / 2°C","week":"星期六","wind":"南风 3～4级"},{"date":"2016-12-11","dayTime":"晴","night":"晴","temperature":"9°C / 7°C","week":"星期日","wind":"南风 3～4级"},{"date":"2016-12-12","dayTime":"阴","night":"阴","temperature":"11°C / 1°C","week":"星期一","wind":"北风 3～4级"},{"date":"2016-12-13","dayTime":"多云","night":"多云","temperature":"7°C / -2°C","week":"星期二","wind":"北风 5～6级"},{"date":"2016-12-14","dayTime":"晴","night":"晴","temperature":"3°C / -3°C","week":"星期三","wind":"北风 4～5级"},{"date":"2016-12-15","dayTime":"多云","night":"多云","temperature":"3°C / -3°C","week":"星期四","wind":"北风 4～5级"},{"date":"2016-12-16","dayTime":"晴","night":"晴","temperature":"7°C / -1°C","week":"星期五","wind":"西风 2级"},{"date":"2016-12-17","dayTime":"晴","night":"晴","temperature":"10°C / 0°C","week":"星期六","wind":"西南偏南风 4级"},{"date":"2016-12-18","dayTime":"少云","night":"少云","temperature":"11°C / -1°C","week":"星期日","wind":"西南风 2级"}],"humidity":"湿度：57%","pollutionIndex":"58","province":"山东","sunrise":"06:58","sunset":"16:44","temperature":"3℃","time":"19:11","updateTime":"20161209192757","washIndex":"不太适宜","weather":"多云","week":"周五","wind":"北风3级"}]
     * retCode : 200
     */

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * airCondition : 良
         * city : 青岛
         * coldIndex : 多发期
         * date : 2016-12-09
         * distrct : 青岛
         * dressingIndex : 薄冬衣
         * exerciseIndex : 不适宜
         * future : [{"date":"2016-12-09","night":"多云","temperature":"1°C","week":"今天","wind":"北风 3～4级"},{"date":"2016-12-10","dayTime":"多云","night":"多云","temperature":"6°C / 2°C","week":"星期六","wind":"南风 3～4级"},{"date":"2016-12-11","dayTime":"晴","night":"晴","temperature":"9°C / 7°C","week":"星期日","wind":"南风 3～4级"},{"date":"2016-12-12","dayTime":"阴","night":"阴","temperature":"11°C / 1°C","week":"星期一","wind":"北风 3～4级"},{"date":"2016-12-13","dayTime":"多云","night":"多云","temperature":"7°C / -2°C","week":"星期二","wind":"北风 5～6级"},{"date":"2016-12-14","dayTime":"晴","night":"晴","temperature":"3°C / -3°C","week":"星期三","wind":"北风 4～5级"},{"date":"2016-12-15","dayTime":"多云","night":"多云","temperature":"3°C / -3°C","week":"星期四","wind":"北风 4～5级"},{"date":"2016-12-16","dayTime":"晴","night":"晴","temperature":"7°C / -1°C","week":"星期五","wind":"西风 2级"},{"date":"2016-12-17","dayTime":"晴","night":"晴","temperature":"10°C / 0°C","week":"星期六","wind":"西南偏南风 4级"},{"date":"2016-12-18","dayTime":"少云","night":"少云","temperature":"11°C / -1°C","week":"星期日","wind":"西南风 2级"}]
         * humidity : 湿度：57%
         * pollutionIndex : 58
         * province : 山东
         * sunrise : 06:58
         * sunset : 16:44
         * temperature : 3℃
         * time : 19:11
         * updateTime : 20161209192757
         * washIndex : 不太适宜
         * weather : 多云
         * week : 周五
         * wind : 北风3级
         */

        private String airCondition;
        private String city;
        private String coldIndex;
        private String date;
        private String distrct;
        private String dressingIndex;
        private String exerciseIndex;
        private String humidity;
        private String pollutionIndex;
        private String province;
        private String sunrise;
        private String sunset;
        private String temperature;
        private String time;
        private String updateTime;
        private String washIndex;
        private String weather;
        private String week;
        private String wind;
        private List<FutureBean> future;

        public String getAirCondition() {
            return airCondition;
        }

        public void setAirCondition(String airCondition) {
            this.airCondition = airCondition;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getColdIndex() {
            return coldIndex;
        }

        public void setColdIndex(String coldIndex) {
            this.coldIndex = coldIndex;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDistrct() {
            return distrct;
        }

        public void setDistrct(String distrct) {
            this.distrct = distrct;
        }

        public String getDressingIndex() {
            return dressingIndex;
        }

        public void setDressingIndex(String dressingIndex) {
            this.dressingIndex = dressingIndex;
        }

        public String getExerciseIndex() {
            return exerciseIndex;
        }

        public void setExerciseIndex(String exerciseIndex) {
            this.exerciseIndex = exerciseIndex;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPollutionIndex() {
            return pollutionIndex;
        }

        public void setPollutionIndex(String pollutionIndex) {
            this.pollutionIndex = pollutionIndex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWashIndex() {
            return washIndex;
        }

        public void setWashIndex(String washIndex) {
            this.washIndex = washIndex;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class FutureBean {
            /**
             * date : 2016-12-09
             * night : 多云
             * temperature : 1°C
             * week : 今天
             * wind : 北风 3～4级
             * dayTime : 多云
             */

            private String date;
            private String night;
            private String temperature;
            private String week;
            private String wind;
            private String dayTime;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getNight() {
                return night;
            }

            public void setNight(String night) {
                this.night = night;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getDayTime() {
                return dayTime;
            }

            public void setDayTime(String dayTime) {
                this.dayTime = dayTime;
            }
        }
    }
}
