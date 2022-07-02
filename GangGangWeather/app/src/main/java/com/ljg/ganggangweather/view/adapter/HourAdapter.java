package com.ljg.ganggangweather.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ljg.ganggangweather.bean.txbean.WeatherBean;
import com.ljg.ganggangweather.txweather.TxWeatherHelper;
import com.ljg.ganggangweather.R;

import java.util.List;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.ViewHolder> {
    private static final String TAG="HourAdapter";
    private List<WeatherBean.ForecastHour> forecastHourList;
    private Context context;

    public HourAdapter(List<WeatherBean.ForecastHour> forecastHourList) {
        this.forecastHourList = forecastHourList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder");
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.item_adapter_hour,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG,"onBindViewHolder:"+position);
        WeatherBean.ForecastHour hour=forecastHourList.get(position);
        holder.tv_weather_hour_temp.setText(hour.getDegree()+"℃");
        String hourText=hour.getUpdateTime().substring(8,10)+":00";
        holder.tv_weather_hour.setText(hourText);
        String baseIconUrl= TxWeatherHelper.getWeatherStateIcon(hour.getWeatherCode(),false);
        if(Integer.valueOf(hour.getUpdateTime().substring(8,10))<18&&Integer.valueOf(hour.getUpdateTime().substring(8,10))>6){
            baseIconUrl=TxWeatherHelper.getWeatherStateIcon(hour.getWeatherCode(),true);
        }
        Glide.with(context)
                .load(baseIconUrl)
                .override(50)
                .placeholder(R.mipmap.night_cloud)
                .into(holder.iv_weather_hour_icon);
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        Log.i(TAG,"onViewRecycled");
        super.onViewRecycled(holder);
        if(holder!=null){
            Glide.with(context).clear(holder.iv_weather_hour_icon);
        }
    }

    @Override
    public int getItemCount() {
        return forecastHourList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_weather_hour,tv_weather_hour_temp;
        private ImageView iv_weather_hour_icon;
        public ViewHolder(@NonNull View v) {
            super(v);
            tv_weather_hour=v.findViewById(R.id.tv_weather_hour);
            iv_weather_hour_icon=v.findViewById(R.id.iv_weather_hour_icon);
            tv_weather_hour_temp=v.findViewById(R.id.tv_weather_hour_temp);
            Log.i(TAG,"onViewRecycled");
        }
    }
}
