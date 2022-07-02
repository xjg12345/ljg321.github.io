package com.ljg.ganggangweather.net.netlocal;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.ljg.ganggangweather.bean.userbean.UserData;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private static OkHttpUtils instance = new OkHttpUtils();
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler(Looper.myLooper());

    private OkHttpUtils() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public static OkHttpUtils getInstance() {
        return instance;
    }

    public void doGet(String url, CallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        extracted(callBack, call);
    }

    public void doPost(String url, CallBack callBack, UserData userData) {
        Gson gson = new Gson();
        String userJson = gson.toJson(userData);
        RequestBody body = RequestBody.create(JSON, userJson);
        Request request = new Request.Builder().post(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        extracted(callBack, call);
    }

//    public void doPost(String url, CallBack callBack, HashMap<String, String> hashMap) {
//        FormBody.Builder formBody = new FormBody.Builder();
//        if (hashMap != null) {
//            for (String param : hashMap.keySet()){
//                formBody.add(param, hashMap.get(param));
//            }
//        }
//        Request request = new Request.Builder().post(formBody.build()).url(url).build();
//        Call call = okHttpClient.newCall(request);
//        extracted(callBack, call);
//    }

    private void extracted(CallBack callBack, Call call) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.getStackTrace();
                }
                String finalString = string;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(finalString);
                    }
                });
            }
        });
    }
}
