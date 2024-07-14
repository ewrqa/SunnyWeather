package com.example.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.network</p>
 * <p>简述:uri的根</p>
 * 
 * @author 张凯涛
 * @date 2024/7/14
 */
object ServiceCreator {
   private  const val  BASE_URI="https://api.caiyunapp.com/"


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URI)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //通过  内联函数  在调用的时候更方便 只用传递 泛型即可

    inline  fun<reified  T>create()= retrofit.create(T::class.java)
}