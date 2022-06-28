package com.ljg.ganggangweather.util;

public interface AsyncHttpRequestListener {
    void onSuccess(String result);
    void onFailed(String reason);
}
