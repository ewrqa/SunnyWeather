package com.example.sunnyweather.ui.weather

import android.util.Log
import android.view.TouchDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.sunnyweather.logic.Repository
import com.example.sunnyweather.logic.model.bean.Location
import com.example.sunnyweather.logic.model.bean.Place
import com.example.sunnyweather.logic.model.bean.WeatherBean

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.ui.weather</p>
 * <p>简述:</p>
 *  实时天气与未来天气的viewmodel
 * @author 张凯涛
 * @date 2024/7/15
 */
class WeatherViewMolde :ViewModel(){

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

   private  val mutableLiveData =MutableLiveData<Location>()

  val switchMap = Transformations.switchMap(mutableLiveData) { location ->
            Repository.getRealtimeWeather(location.lat,location.lng)
    }
  //生成获取数据的方法
  fun  getWather(lng:String,lat:String){
     mutableLiveData.value=Location(lng,lat)
  }
}