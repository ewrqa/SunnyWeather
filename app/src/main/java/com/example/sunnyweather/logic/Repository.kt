package com.example.sunnyweather.logic


import android.util.Log
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.network.PlaceService
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext


/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.logic</p>
 * <p>简述:仓库单利类 </p>
 *又有数据就行本地获取没有从线上进行获取
 * 类似于  mvp的p层
 * @author 张凯涛
 * @date 2024/7/14
 */
object Repository {
    //搜索城市的方法
    fun  searchPlaces(query:String)= fire(Dispatchers.IO){
            //调用网络获取的方法
            val  placeServiceawait = SunnyWeatherNetwork.placeServiceawait(query)
            //根据返回的状态进行判断
        Log.d("tag",placeServiceawait.status)
            if (placeServiceawait.status == "ok") {
                val places = placeServiceawait.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is${placeServiceawait.status}"))
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
            emit(result)
        }
}