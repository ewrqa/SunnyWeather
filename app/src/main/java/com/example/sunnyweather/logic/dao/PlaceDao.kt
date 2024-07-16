package com.example.sunnyweather.logic.dao

import android.content.Context
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.bean.Place
import com.google.gson.Gson

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.dao</p>
 * <p>简述:</p>
 *封装  存储用户 搜索城市的记录
 * @author 张凯涛
 * @date 2024/7/16
 */
class PlaceDao {
    //存储
    fun  savePlace(place:Place){
            sharedPreference().edit().putString("place",Gson().toJson(place)).apply()
    }
    //获取存储数据
    fun getSavedPlace():Place{
        val string = sharedPreference().getString("place", "")
        return   Gson().fromJson(string,Place::class.java)
    }

    //保存的状态
     fun  isPlaceSaved()=sharedPreference().contains("place")


        fun sharedPreference()=SunnyWeatherApplication.context.
         getSharedPreferences("sunny_weather",Context.MODE_PRIVATE)
}