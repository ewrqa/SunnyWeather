package com.example.sunnyweather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.R
import com.example.sunnyweather.logic.model.Place

/**
 * <p>项目名称:SunnyWeather</p>
 * <p>包名:com.example.sunnyweather.ui.place</p>
 * <p>简述:</p>
 *适配器  需要连接的布局与数据
 * @author 张凯涛
 * @date 2024/7/15
 */
class PlaceAdapter(val fragment:Fragment,val list: List<Place> ): RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    inner  class ViewHolder(view:View):RecyclerView.ViewHolder(view){
     val placeName : TextView = view.findViewById(R.id.placeName)
      val placeAddress:TextView = view.findViewById(R.id.placeAddress)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //连接 条目布局
        val inflate = LayoutInflater.from(parent.context).
        inflate(R.layout.place_item, parent,false)

        return ViewHolder(inflate)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = list[position]
        holder.placeName.text=place.name.toString()
        holder.placeAddress.text=place.address
    }
    override fun getItemCount(): Int {
       return list.size
    }

}