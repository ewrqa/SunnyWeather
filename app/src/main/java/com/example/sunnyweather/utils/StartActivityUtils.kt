package com.example.sunnyweather.utils

import android.content.Context
import android.content.Intent

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.utils</p>
 * <p>简述:</p>
 *
 * @author 张凯涛
 * @date 2024/7/15
 */

//泛型实例化
inline  fun<reified  T> startActivity(context: Context,block:Intent.()->Unit){
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}