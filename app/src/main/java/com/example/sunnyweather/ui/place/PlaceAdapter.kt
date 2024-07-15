package com.example.sunnyweather.ui.place
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.R
import com.example.sunnyweather.logic.model.bean.Place
import com.example.sunnyweather.ui.weather.WeahterActivity
import com.example.sunnyweather.utils.startActivity

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
        val viewHolder = ViewHolder(inflate)
        //点击事件的跳转
        viewHolder.itemView.setOnClickListener{
            //获取该该城市的 经度纬度
            val adapterPosition = viewHolder.adapterPosition
            val place = list[adapterPosition+1]
            val lng = place.location.lng
            val lat = place.location.lat
            val name = place.name
            startActivity<WeahterActivity>(fragment.context!!){
                putExtra("lng",lng)
                putExtra("lat",lat)
                putExtra("name",name)
            }
        }
        return ViewHolder(inflate)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = list[position]
        holder.placeName.text=place.name.toString()
        holder.placeAddress.text=place.formatted_address
    }
    override fun getItemCount(): Int {
       return list.size
    }

}