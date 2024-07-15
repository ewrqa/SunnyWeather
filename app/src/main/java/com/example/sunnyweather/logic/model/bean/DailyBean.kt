package com.example.sunnyweather.logic.model.bean

import java.util.*

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.model.bean</p>
 * <p>简述:</p>
 *  未来几天天气的状况
 * @author 张凯涛
 * @date 2024/7/15
 */
data class DailyBean(
    val result: Result,
    val status: String
){
    data class Result(
        val daily: Daily
    )

    data class Daily(
        val life_index: LifeIndex,
        val skycon: List<Skycon>,
        val temperature: List<Temperature>
    )
  data  class Temperature(val max: Float, val min: Float)

 data   class Skycon(val value: String, val date: Date)

    data class LifeIndex(
        val carWashing: List<LifeDescription>,
        val coldRisk: List<LifeDescription>,
        val dressing: List<LifeDescription>,
        val ultraviolet: List<LifeDescription>
    )

    class LifeDescription(val desc: String)
}
