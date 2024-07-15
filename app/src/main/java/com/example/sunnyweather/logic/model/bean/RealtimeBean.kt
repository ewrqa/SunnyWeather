package com.example.sunnyweather.logic.model.bean

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.model.bean</p>
 * <p>简述:实时天气的数据</p>
 * @author 张凯涛
 * @date 2024/7/15
 */
data class RealtimeBean(
    val result: Result,
    val status: String
){
    data class Result(
        val realtime: Realtime
    )

    data class Realtime(
        val air_quality: AirQuality,
        val skycon: String,
        val temperature: Double
    )

    data class AirQuality(
        val aqi: Aqi
    )

    data class Aqi(
        val chn: Double
    )
}