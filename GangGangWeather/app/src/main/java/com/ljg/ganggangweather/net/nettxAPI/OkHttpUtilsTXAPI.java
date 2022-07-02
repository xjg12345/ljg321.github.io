package com.ljg.ganggangweather.net.nettxAPI;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtilsTXAPI {
    private OkHttpUtilsTXAPI(){}
    private static OkHttpUtilsTXAPI instance = new OkHttpUtilsTXAPI();
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Handler handler = new Handler(Looper.myLooper());
    public static OkHttpUtilsTXAPI getInstance(){
        return instance;
    }
    public void doGet(String url, CallBackTXAPT callBackTXAPT) {
        Request request= new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
       call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBackTXAPT.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String string = null;
                try{
                    string = response.body().string();
                }catch (IOException e){
                    e.getStackTrace();
                }
                String finalString = string;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBackTXAPT.onSuccess(finalString);
                    }
                });
            }
        });
    }
}
