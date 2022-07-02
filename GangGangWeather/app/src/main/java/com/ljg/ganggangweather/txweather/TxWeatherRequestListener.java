package com.ljg.ganggangweather.txweather;

import com.ljg.ganggangweather.bean.txbean.CityShip;
import com.ljg.ganggangweather.bean.txbean.WeatherBean;

public interface TxWeatherRequestListener {
    void onSuccess(CityShip cityShip, WeatherBean weatherBean);
    void onFailed(String reason);
}
