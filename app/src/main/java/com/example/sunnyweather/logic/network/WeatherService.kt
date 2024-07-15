package com.example.sunnyweather.logic.network

import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.bean.DailyBean
import com.example.sunnyweather.logic.model.bean.RealtimeBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.network</p>
 * <p>简述:</p>
 * 城市天气的 apiserice
 * @author 张凯涛
 * @date 2024/7/15
 */
interface WeatherService {
    //具体城市天气实时情况
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<RealtimeBean>
    //未来几天天气情况
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<DailyBean>
}