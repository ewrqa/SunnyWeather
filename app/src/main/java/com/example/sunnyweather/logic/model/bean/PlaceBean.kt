package com.example.sunnyweather.logic.model.bean

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.model.bean</p>
 * <p>首页搜索的数据:</p>
 * @author 张凯涛
 * @date 2024/7/15
 */
data class PlaceBean(
    val places: List<Place>,
    val query: String,
    val status: String
)
data class Place(
    val formatted_address: String,
    val id: String,
    val location: Location,
    val name: String,
    val place_id: String
)

data class Location(
    val lat: String,
    val lng: String
)