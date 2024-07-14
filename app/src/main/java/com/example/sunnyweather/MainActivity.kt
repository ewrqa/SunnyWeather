package com.example.sunnyweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sunnyweather.logic.network.PlaceService
import com.example.sunnyweather.logic.network.ServiceCreator
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}