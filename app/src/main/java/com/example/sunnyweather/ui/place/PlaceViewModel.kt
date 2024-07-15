package com.example.sunnyweather.ui.place
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.sunnyweather.logic.Repository
import com.example.sunnyweather.logic.model.bean.Location
import com.example.sunnyweather.logic.model.bean.Place
/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.ui.place</p>
 * <p>简述:将界面上的数据放在viewmodel当中
 * 防止手机旋转的时候数据不会丢失</p>
 *place 的viewmodel
 * @author 张凯涛
 * @date 2024/7/14
 */
class PlaceViewModel :ViewModel(){
    //创建一个arraylist用于缓存存储 得到的数据
    var placeList=ArrayList<Place>()
    //得到 可检测的livedata
    private  val muLiveData=MutableLiveData<String>()

    //不是在viewmodel当中 创建的livedata对象的话
    // 是无法观察到的 需要通过switchmap转换
      var placeLiveData =  Transformations.switchMap(muLiveData){
              query-> Repository.searchPlaces(query)
      }
    //将参数赋值给lidata 那么每当参数发生变化的时候  livedata就会被观察到
    fun  searchOlaces(query:String){
        muLiveData.value=query
    }
}