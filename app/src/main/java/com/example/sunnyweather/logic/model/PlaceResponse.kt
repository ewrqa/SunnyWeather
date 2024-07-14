package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.model</p>
 * <p>简述:</p>
 *
 * @author 张凯涛
 * @date 2024/7/14
 */

class PlaceResponse(val status: String, val places: List<Place>)

class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

class Location(val lng: String, val lat: String)


