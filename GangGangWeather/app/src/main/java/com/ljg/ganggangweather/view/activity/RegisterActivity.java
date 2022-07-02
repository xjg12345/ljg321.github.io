package com.ljg.ganggangweather.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljg.ganggangweather.R;
import com.ljg.ganggangweather.bean.userbean.UserData;
import com.ljg.ganggangweather.net.netlocal.CallBack;
import com.ljg.ganggangweather.net.netlocal.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextRePassword;
    private Button buttonRegister;
    private UserData userData = new UserData();
    private List listUsername = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        initView();
        getData();
        initEvent();
        setTitle("注册");
        setUpToolBar();
    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initEvent() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String rePassword = editTextRePassword.getText().toString();
                Log.d("dataljg", "usernameRegister" + listUsername);
                userData.setUsername(username);
                userData.setPassword(password);
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(RegisterActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();

                } else if (!password.equals(rePassword)) {
                    Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();

                } else if (listUsername.contains(username)) {
                    Toast.makeText(RegisterActivity.this, "你输入的账号已存在，请重新输入", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(RegisterActivity.this, "你输入的账号已存在，请重新输入", Toast.LENGTH_SHORT).show();
                else {
                    Log.i("dataljg", "postData：" + userData.toString());
                    postData(userData);
                }
            }
        });
    }

    private void postData(UserData userData) {
        OkHttpUtils.getInstance().doPost("http://10.0.2.2:8001/User/postMethod", new CallBack() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(RegisterActivity.this, "网络操作失败", Toast.LENGTH_SHORT).show();
            }
        }, userData);
    }

    private void getData() {
        //"http://10.0.2.2:8001/Db01"
        OkHttpUtils.getInstance().doGet("http://10.0.2.2:8001/User/getMethod", new CallBack() {
            @Override
            public void onSuccess(String result) {
                parseJSONWithGSON(result);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(RegisterActivity.this, "网络操作失败", Toast.LENGTH_SHORT).show();
            }
        });
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
        }
//        for (UserData userData : userDataList) {
//            listUsername.add(userData.getAddress());
//        }

    }

    private void initView() {
        editTextUsername = findViewById(R.id.id_et_username);
        editTextPassword = findViewById(R.id.id_et_password);
        editTextRePassword = findViewById(R.id.id_et_rePassword);
        buttonRegister = findViewById(R.id.id_btn_register);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_register, menu);
//        Log.d("RegisterActivity","ss");
//        return true;
//    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_register,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.item_main_manage:
//                Intent intent=new Intent(RegisterActivity.this, CityManageActivity.class);
//                startActivity(intent);
//                break;
//            default:
//                break;
//        }
//        return true;
//    }
}