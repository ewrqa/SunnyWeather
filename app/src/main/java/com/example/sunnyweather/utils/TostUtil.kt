package com.example.sunnyweather.utils

import android.content.Context
import android.widget.Toast

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.utils</p>
 * <p>简述:简化TostUtil</p>
 *
 * @author 张凯涛
 * @date 2024/7/15
 */

fun  String.showToast(context : Context ,duration:Int=Toast.LENGTH_LONG){
    Toast.makeText(context,this, duration).show()
}
fun Int.showToast(context: Context,duration:Int=Toast.LENGTH_LONG){
    Toast.makeText(context,this,duration).show()
}