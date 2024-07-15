package com.example.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic.network</p>
 * <p>简述:网络获取了</p>
 *
 * @author 张凯涛
 * @date 2024/7/14
 */
object SunnyWeatherNetwork {
    //生成获取城市接口动态代理
      val placeService=ServiceCreator.create<PlaceService>()
    suspend fun  getPlaceService(queue:String) = placeService.searchPlaces(queue)
      val  weatherService=ServiceCreator.create<WeatherService>()
    suspend fun getDailyWeather(lat: String,lng: String) = weatherService.getDailyWeather( lat,lng).await()

    suspend fun getRealWeather(lat: String,lng: String) = weatherService.getRealtimeWeather(lat,lng).await()

    //获取某城市实时天气的接口
    ///网络请求之后的请求响应结果
  private  suspend fun<T> Call<T>.await():T{
        return  suspendCoroutine {
            continuation ->enqueue(object :Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if(body!=null) continuation.resume(body)else{
                    continuation.resumeWithException(RuntimeException("得到的数据为空"))
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
        }
    }
}