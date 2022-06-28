package com.ljg.ganggangweather.txweather;

import com.ljg.ganggangweather.bean.CityShip;

import java.util.List;

public interface TxCityRequestListener {
    void onSuccess(List<CityShip> cityShipList);
    void onFailed(String reason);
}
