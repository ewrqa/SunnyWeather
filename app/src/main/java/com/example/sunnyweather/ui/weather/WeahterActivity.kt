package com.example.sunnyweather.ui.weather
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sunnyweather.R
import com.example.sunnyweather.logic.model.bean.WeatherBean
import com.example.sunnyweather.logic.model.bean.getSky
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class WeahterActivity : AppCompatActivity() {
    val viewmode by lazy { ViewModelProvider(this).get(WeatherViewMolde::class.java) }
    lateinit var  placeName :  TextView
    lateinit var  currentTemp :TextView
    lateinit var   currentSky :TextView
    lateinit var currentAQI:TextView
    lateinit var nowLayout:RelativeLayout
    lateinit var forecastLayout:LinearLayout
     lateinit var coldRiskText :TextView
     lateinit var dressingText:TextView
     lateinit var ultravioletText:TextView
     lateinit var carWashingText:TextView

     lateinit var weatherLayout: ScrollView
     lateinit var swipeRefresh :SwipeRefreshLayout
     lateinit var  navBtn:Button
    lateinit var drawerLayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weahter)
        initview()
        if(viewmode.locationLng.isEmpty()){
            viewmode.locationLng = intent.getStringExtra("lng") ?: ""
        }
        if(viewmode.locationLat.isEmpty()){
            viewmode.locationLat = intent.getStringExtra("lat") ?: ""
        }
        if(viewmode.placeName.isEmpty()){
            viewmode.placeName = intent.getStringExtra("name") ?: ""
        }
        //发送  详情页数据的请求
        //livedata 观察者
        viewmode.switchMap.observe(this, Observer {
           result->
           val orNull = result.getOrNull()
            if(orNull!=null){
                shoWeatherInfo(orNull)
            }else{
                Toast.makeText(this, "无法成功获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
            //  得到响应之后将刷新加载的动画 隐藏
            swipeRefresh.isRefreshing=false
        })
        //设置刷新进度球的颜色
       refreshWeather()
        //下拉刷新  刷新的响应
        swipeRefresh.setOnRefreshListener {
            refreshWeather()
        }
        //按钮点击 打开左侧抽屉
        navBtn.setOnClickListener {
            //抽屉布局
            drawerLayout.openDrawer(GravityCompat.START)
        }
        drawerLayout.addDrawerListener(object :DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }
            override fun onDrawerOpened(drawerView: View) {
            }
            override fun onDrawerClosed(drawerView: View) {
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(drawerView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
            override fun onDrawerStateChanged(newState: Int) {
            }
        })
    }
    //进入页面即刻刷新该页面
    fun refreshWeather() {
        viewmode.getWather(viewmode.locationLng, viewmode.locationLat)
        swipeRefresh.isRefreshing = true
    }
    fun shoWeatherInfo(weather:WeatherBean){
        //城市名字
        placeName.text=viewmode.placeName
        Log.e("Tag",viewmode.placeName)
        val realtime = weather.realtime
        val daily = weather.daily
        val currentTempText = "${realtime.temperature.toInt()} ℃"
        currentTemp.text = currentTempText
        currentSky.text = getSky(realtime.skycon).info
        val currentPM25Text = "空气指数 ${realtime.air_quality.aqi.chn.toInt()}"
        currentAQI.text=currentPM25Text
        nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
        forecastLayout.removeAllViews()

        val days = daily.skycon.size
        for (i in 0 until days) {
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false)
            val dateInfo = view.findViewById(R.id.dateInfo) as TextView
            val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
            val skyInfo = view.findViewById(R.id.skyInfo) as TextView
            val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
            temperatureInfo.text = tempText
            forecastLayout.addView(view)
        }
//         填充life_index.xml布局中的数据
        val lifeIndex = daily.life_index
        coldRiskText.text = lifeIndex.coldRisk[0].desc
        dressingText.text = lifeIndex.dressing[0].desc
        ultravioletText.text = lifeIndex.ultraviolet[0].desc
        carWashingText.text = lifeIndex.carWashing[0].desc
        weatherLayout.visibility = View.VISIBLE

    }
    fun initview(){
        placeName = findViewById<TextView>(R.id.placeName)
        currentTemp = findViewById<TextView>(R.id.currentTemp)
        currentSky = findViewById<TextView>(R.id.currentSky)
        currentAQI = findViewById<TextView>(R.id.currentAQI)
        navBtn = findViewById<Button>(R.id.navBtn)
        coldRiskText    = findViewById<TextView>(R.id.coldRiskText)
        dressingText     = findViewById<TextView>(R.id.dressingText)
        ultravioletText = findViewById<TextView>(R.id.ultravioletText)
        carWashingText  = findViewById<TextView>(R.id.carWashingText)

        nowLayout = findViewById<RelativeLayout>(R.id.nowLayout)
        forecastLayout = findViewById<LinearLayout>(R.id.forecastLayout)

        weatherLayout = findViewById<ScrollView>(R.id.weatherLayout)

        swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
    }

}