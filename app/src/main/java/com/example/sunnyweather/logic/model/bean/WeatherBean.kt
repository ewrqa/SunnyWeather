package com.example.sunnyweather.logic.model.bean

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.model.bean</p>
 * <p>简述:</p>
 * 城市未来几天的天气状况以及实时的天气状况
 * @author 张凯涛
 * @date 2024/7/15
 */
data class WeatherBean(val realtime: RealtimeBean.Realtime, val daily:DailyBean.Daily)
