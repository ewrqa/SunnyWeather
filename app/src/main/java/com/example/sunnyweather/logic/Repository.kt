package com.example.sunnyweather.logic


import android.util.Log
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.bean.WeatherBean
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext


/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic</p>
 * <p>简述:仓库单利类 </p>
 *又有数据就行本地获取没有从线上进行获取
 *  先 创建apiservice 然后 创建apiservice构建器  在网络请求调用的时候进行成功与失败的返回
 *  在这里调用  网络请求  得到响应状态之后  根据得到的响应状态再判断接口的返回值 通过emit  livedata 发送高职viewmodel
 *  作出响应
 * @author 张凯涛
 * @date 2024/7/14
 */
object Repository {
    //搜索城市的方法
    fun  searchPlaces(query:String)= fire(Dispatchers.IO){
            //调用网络获取的方法
        val await = SunnyWeatherNetwork.getPlaceService(query).await()
        //根据返回的状态进行判断
            if (await.status == "ok") {
                val places = await.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is${await.status}"))
            }
    }
    //功能需要 获取实时天气与未来几天的消息 所以将他们合成一个请求方法  获取城市实实时天气数据
    fun getRealtimeWeather(lat:String,lng:String) = fire(Dispatchers.IO){
       Log.d("Tag",lat)
        coroutineScope {
           val dailyWeather= async {
                SunnyWeatherNetwork.getDailyWeather(lat,lng)
            }
            val realWeatherr= async {
                SunnyWeatherNetwork.getRealWeather(lat,lng)
            }
            val dailyWeatheraWait = dailyWeather.await()
            val realWeatherrawait = realWeatherr.await()
            if (dailyWeatheraWait.status == "ok"&&realWeatherrawait.status=="ok") {
                val weratherBean =
                    WeatherBean(realWeatherrawait.result.realtime, dailyWeatheraWait.result.daily)
                Result.success(weratherBean)
            } else {
                Log.d("Tag","状态失败")
                Result.failure(RuntimeException("response status is${dailyWeatheraWait.status} is${realWeatherrawait.status}"))
            }
        }
    }
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        //使用livedata  进行数据的刷新
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            //每当获取数据 有新数据的时候 调用emit  类似于 setValues 通知levedata进行刷新
            //发送给livedata 所以他的返回值就是 await.places的类型
            emit(result)
        }
}