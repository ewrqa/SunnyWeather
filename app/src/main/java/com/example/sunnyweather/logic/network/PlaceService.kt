package com.example.sunnyweather.logic.network

import android.telecom.Call
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.network</p>
 * <p>简述:接口的请求</p>
 *
 * @author 张凯涛
 * @date 2024/7/14
 */
interface PlaceService {
    //查询城市信息
   @GET("/v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    //返回的值为 类似  java当中的bean类
// 这样Retrofit就会将服务器返回的JSON数据自动解析成PlaceResponse对象了。
    fun searchPlaces(@Query("query")query:String) :retrofit2.Call<PlaceResponse>

}