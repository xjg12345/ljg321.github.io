package com.ljg.ganggangweather.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljg.ganggangweather.R;
import com.ljg.ganggangweather.net.nettxAPI.CallBackTXAPT;
import com.ljg.ganggangweather.net.nettxAPI.OkHttpUtilsTXAPI;
import com.ljg.ganggangweather.bean.noticebean.ArrayData;

import java.util.List;

public class NoticeActivity extends AppCompatActivity {
    private TextView responseText1;
    private TextView responseText2;
    private TextView responseText3;
    private TextView responseText4;
    private TextView responseText5;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        //responseText = (TextView) findViewById(R.id.response_text);
        //sendRequestWithHttpURLConnection();
        initView();
        initEvent();


    }

    private void initView() {
        responseText1 = findViewById(R.id.response_text1);
        responseText2 = findViewById(R.id.response_text2);
        responseText3 = findViewById(R.id.response_text3);
        responseText4 = findViewById(R.id.response_text4);
        responseText5 = findViewById(R.id.response_text5);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
    }

    private void initEvent() {
        OkHttpUtilsTXAPI.getInstance().doGet("http://10.0.2.2:8001/Db01", new CallBackTXAPT() {
            @Override
            public void onSuccess(String result) {
                Log.i("dataljg", "notice：" + result);
                parseJSONWithGSON(result);
                if (result != null) {
                    imageView1.setImageDrawable(getDrawable(R.mipmap.background2));
                    imageView2.setImageDrawable(getDrawable(R.mipmap.background3));
                    imageView3.setImageDrawable(getDrawable(R.mipmap.background4));
                    imageView4.setImageDrawable(getDrawable(R.mipmap.background5));
                    imageView5.setImageDrawable(getDrawable(R.mipmap.background6));
                }
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(NoticeActivity.this, "网络操作失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<ArrayData> arrayDataList = gson.fromJson(jsonData, new TypeToken<List<ArrayData>>() {
        }.getType());
        responseText1.setText(arrayDataList.get(0).toString());
        responseText2.setText(arrayDataList.get(1).toString());
        responseText3.setText(arrayDataList.get(2).toString());
        responseText4.setText(arrayDataList.get(3).toString());
        responseText5.setText(arrayDataList.get(4).toString());
    }
//        for(ArrayData arrayData : arrayDataList){
//            textView.setText(arrayData.getId());
//        }


//    private void sendRequestWithHttpURLConnection() {
//        // 开启线程来发起网络请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection = null;
//                BufferedReader reader = null;
//                try {
//                    URL url = new URL("http://10.0.2.2:8001/Db01");
//                    //URL url = new URL("http://www.baidu.com");
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    InputStream in = connection.getInputStream();
//                    // 下面对获取到的输入流进行读取
//                    reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//                    showResponse(response.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (reader != null) {
//                        try {
//                            reader.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }
//    private void showResponse(final String response) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                // 在这里进行UI操作，将结果显示到界面上
//                responseText.setText(response);
//            }
//        });
//    }
}