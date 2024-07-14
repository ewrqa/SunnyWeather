package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather</p>
 * <p>简述:application</p>
 * 获取全局的application
 * @author 张凯涛
 * @date 2024/7/13
 */
class SunnyWeatherApplication :Application(){
    companion object{
        //进行代码注释
        @SuppressLint("StaticFieldLeak")
        lateinit var  context :Context
        const val  TOKEN="iGAf5etRZHumxzQn"
    }
    override fun onCreate() {
        super.onCreate()
        context =applicationContext
    }
}