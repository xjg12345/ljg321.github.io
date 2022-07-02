package com.ljg.ganggangweather.txweather;

import com.ljg.ganggangweather.bean.txbean.CityShip;

public interface TxCityLocationListener {
    void onSuccess(CityShip cityShip);
    void onFailed(String reason);
}
