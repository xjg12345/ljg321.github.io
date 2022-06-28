package com.ljg.ganggangweather.txweather;

import com.ljg.ganggangweather.bean.CityShip;
import com.ljg.ganggangweather.bean.WeatherBean;

public interface TxWeatherRequestListener {
    void onSuccess(CityShip cityShip, WeatherBean weatherBean);
    void onFailed(String reason);
}
