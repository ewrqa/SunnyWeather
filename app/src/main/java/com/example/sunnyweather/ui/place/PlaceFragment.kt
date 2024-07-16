package com.example.sunnyweather.ui.place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.MainActivity
import com.example.sunnyweather.R
import com.example.sunnyweather.ui.weather.WeahterActivity
import com.example.sunnyweather.utils.showToast

class PlaceFragment : Fragment() {
    //进行属性委托    获取到该类的实例   进行懒加载处理
    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    lateinit var searchPlaceEdit: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var bgImageView: ImageView
    lateinit var placeAdapter: PlaceAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val inflate =
            LayoutInflater.from(context).inflate(R.layout.fragment_place, container, false)
        searchPlaceEdit = inflate.findViewById<EditText>(R.id.searchPlaceEdit)
        recyclerView = inflate.findViewById<RecyclerView>(R.id.recyclerView)
        bgImageView = inflate.findViewById<ImageView>(R.id.bgImageView)

        return inflate
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.e("ww", "mylog::::${activity is MainActivity}")
        if (activity is MainActivity && viewModel.isSavedPlace()) {
            val savedPlace = viewModel.getSavedPlace()
            Log.e("ww", savedPlace.name + "===================")
            com.example.sunnyweather.utils.startActivity<WeahterActivity>(context!!) {
                putExtra("lng", savedPlace.location.lng)
                putExtra("lat", savedPlace.location.lat)
                putExtra("name", savedPlace.name)
            }
            activity?.finish()
            return
        }
        //获取适配器
        placeAdapter = PlaceAdapter(this, viewModel.placeList)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = placeAdapter
        //监听 输入框的文字变化
        searchPlaceEdit.addTextChangedListener { editable ->
            val toString = editable.toString()
            //不为空的时候发起请求
            if (toString.isNotEmpty()) {
                viewModel.searchOlaces(toString)
            } else {
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
            }
        }
        //当数据产生变化的时候 更新
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val orNull = result.getOrNull()
            if (orNull != null) {
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(orNull)
                placeAdapter.notifyDataSetChanged()
            } else {
                "未能查询到任何地点".showToast(context!!, Toast.LENGTH_LONG)
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}