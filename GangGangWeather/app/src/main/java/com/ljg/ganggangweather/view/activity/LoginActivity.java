package com.ljg.ganggangweather.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljg.ganggangweather.R;
import com.ljg.ganggangweather.net.netlocal.CallBack;
import com.ljg.ganggangweather.net.netlocal.OkHttpUtils;
import com.ljg.ganggangweather.bean.userbean.UserData;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewRegister;
    private List listUsername = new ArrayList();
    private List listPassword = new ArrayList();
    private String tempPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        getData();
        initEvent();
    }

    private void getData() {
        //"http://10.0.2.2:8001/Db01"
        OkHttpUtils.getInstance().doGet("http://10.0.2.2:8001/User/getMethod", new CallBack() {
            @Override
            public void onSuccess(String result) {
                parseJSONWithGSON(result);
                Log.d("dataljg", "username" + listUsername);
                Log.d("dataljg", "password" + listPassword);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(LoginActivity.this, "网络操作失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEvent() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                Log.d("dataljg", "Index" + listUsername.indexOf(username));
                if (listUsername.indexOf(username) != -1) {
                    tempPassword = (String) listPassword.get(listUsername.indexOf(username));
                }
                //Log.d("aa", "passwordIndex" + tempPassword);
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();

                } else if (!listUsername.contains(username)) {
                    Toast.makeText(LoginActivity.this, "用户不存在，请注册", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(tempPassword)) {
                    Toast.makeText(LoginActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    toWeatherActivity();
                }
            }
        });


        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterActivity();
            }
        });
    }


    private void toRegisterActivity() {
        listPassword.clear();
        listUsername.clear();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void toWeatherActivity() {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        editTextUsername = findViewById(R.id.id_et_username);
        editTextPassword = findViewById(R.id.id_et_password);
        buttonLogin = findViewById(R.id.id_btn_login);
        textViewRegister = findViewById(R.id.id_tv_register);
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        //List<ArrayData> arrayDataList = gson.fromJson(jsonData, new TypeToken<List<ArrayData>>(){}.getType());
        //textView.setText(arrayDataList.get(0).toString());
//        for(ArrayData arrayData : arrayDataList){
//            textView.setText(arrayData.getId());
//        }

        List<UserData> userDataList = gson.fromJson(jsonData, new TypeToken<List<UserData>>() {
        }.getType());
        for (UserData userData : userDataList) {
            // textView.setText(userData.getName());
            listUsername.add(userData.getUsername());
            listPassword.add(userData.getPassword());
        }
//        for (UserData userData : userDataList) {
//            listUsername.add(userData.getAddress());
//        }

    }
}