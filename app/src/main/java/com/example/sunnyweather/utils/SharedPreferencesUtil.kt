package com.example.sunnyweather.utils

import android.content.SharedPreferences

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.utils</p>
 * <p>简述:</p>
 *
 * @author 张凯涛
 * @date 2024/7/16
 */
fun SharedPreferences.open(block:SharedPreferences.Editor.()->Unit){
    val edit = edit()
    edit.block()
    edit.apply()
}