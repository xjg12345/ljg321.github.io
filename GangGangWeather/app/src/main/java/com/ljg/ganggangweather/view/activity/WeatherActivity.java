package com.ljg.ganggangweather.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ljg.ganggangweather.R;
import com.ljg.ganggangweather.bean.txbean.CityDetailBean;
import com.ljg.ganggangweather.db.CityDatabaseHelper;
import com.ljg.ganggangweather.txweather.TxWeatherHelper;
import com.ljg.ganggangweather.view.adapter.CityWeatherFragmentAdapter;
import com.ljg.ganggangweather.view.fragment.CityFragment;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CHANGE_NETWORK_STATE;
import static android.Manifest.permission.CHANGE_WIFI_STATE;
import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_PHONE_STATE;

public class WeatherActivity extends AppCompatActivity {
    private Toolbar tb_main;
    private TextView tv_notice_empty_city;
    private LinearLayout ll_vp_point;
    private RelativeLayout rl_main;
    private ViewPager vp_main;
    private CityWeatherFragmentAdapter cityWeatherFragmentAdapter;

    private List<Fragment> cityFragmentList=new ArrayList<>();
    private List<ImageView> imageViewList=new ArrayList<>();
    private List<CityDetailBean> cityDetailBeanList=new ArrayList<>();

    private CityDatabaseHelper dbHelper=new CityDatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        checkPermission();

        refreshViewPage();




    }


    private void initView(){
        rl_main=findViewById(R.id.rl_main);
        rl_main.setBackgroundResource(R.mipmap.background);
        tb_main=findViewById(R.id.tb_main);
        tv_notice_empty_city=findViewById(R.id.tv_notice_empty_city);
        setSupportActionBar(tb_main);
        getSupportActionBar().setTitle("");
        tb_main.setBackgroundColor(0x40CAFF);
        ll_vp_point=findViewById(R.id.ll_vp_point);
        vp_main=findViewById(R.id.vp_main);
        buildVpPoint();
        cityWeatherFragmentAdapter=new CityWeatherFragmentAdapter(getSupportFragmentManager(),cityFragmentList);
        vp_main.setAdapter(cityWeatherFragmentAdapter);
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                for(ImageView iv:imageViewList){
                    iv.setImageResource(R.drawable.shape_point_green);
                }
                imageViewList.get(position).setImageResource(R.drawable.shape_point_pink);
                getSupportActionBar().setTitle(cityDetailBeanList.get(position).getCurrentName());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void refreshViewPage(){
        dbHelper.open();
        List<CityDetailBean> cityDetailBeans=dbHelper.getAllCity();
        cityDetailBeanList.clear();
        cityDetailBeanList.addAll(cityDetailBeans);
        dbHelper.close();
        buildFragments();
        buildVpPoint();
        cityWeatherFragmentAdapter.notifyDataSetChanged();
        if(cityFragmentList.size()>0){
            vp_main.setCurrentItem(cityFragmentList.size()-1);
            getSupportActionBar().setTitle(cityDetailBeanList.get(cityDetailBeanList.size()-1).getCurrentName());
        }
        if(cityFragmentList.isEmpty()){
            tv_notice_empty_city.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("刚刚天气");
        }else {
            tv_notice_empty_city.setVisibility(View.GONE);
        }


    }

    private void buildFragments(){
        cityFragmentList.clear();
        for (CityDetailBean bean:cityDetailBeanList){
            CityFragment weatherFragment=new CityFragment();
            Bundle bundle=new Bundle();
            String updateLink= TxWeatherHelper.txWeatherURLBuilder(bean);
            bundle.putString("updateLink",updateLink);
            bundle.putSerializable("cityDetailBean",bean);
            weatherFragment.setArguments(bundle);
            cityFragmentList.add(weatherFragment);
        }
    }


    private void buildVpPoint(){
        imageViewList.clear();
        ll_vp_point.removeAllViews();
        //        创建小圆点 ViewPager页面指示器的函数
        for (int i = 0; i < cityFragmentList.size(); i++) {
            ImageView iv_point = new ImageView(this);
            iv_point.setImageResource(R.drawable.shape_point_pink);
            iv_point.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_point.getLayoutParams();
            lp.setMargins(0,0,20,0);
            imageViewList.add(iv_point);
            ll_vp_point.addView(iv_point);
        }
        if(cityFragmentList.size()>0){
            imageViewList.get(imageViewList.size()-1).setImageResource(R.drawable.shape_point_green);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_main_manage:
                Intent intent=new Intent(WeatherActivity.this, CityManageActivity.class);
                startActivity(intent);
                break;
            case R.id.item_main_loginAgain:
                loginAgain();
                break;
            case R.id.item_main_opensource:
                noticeOpensource();
                break;
            case R.id.item_main_notice:
                weatherNoticeOpensource();
                break;
            case R.id.item_main_htmlView:
                htmlOpensource();
                break;
            default:
                break;
        }
        return true;
    }

    private void loginAgain() {
        AlertDialog.Builder builder=new AlertDialog.Builder(WeatherActivity.this);
        builder.setTitle("重新登录提示");
        builder.setMessage("点击确定进行重新登录账号");
        builder.setPositiveButton("确定", (dialog, which) -> {
            Toast.makeText(WeatherActivity.this,"重新登录",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(WeatherActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        builder.setNegativeButton("取消", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    private void weatherNoticeOpensource(){
        AlertDialog.Builder builder=new AlertDialog.Builder(WeatherActivity.this);
        builder.setTitle("天气注意事项进入提示");
        builder.setMessage("天气注意事项用于为各种天气类型提供温馨提示\n" +
                "点击确定访问天气注意事项");
        builder.setPositiveButton("确定", (dialog, which) -> {
            Toast.makeText(WeatherActivity.this,"天气注意事项",Toast.LENGTH_SHORT).show();
            Intent intentNotice = new Intent(WeatherActivity.this, NoticeActivity.class);
            startActivity(intentNotice);
        });
        builder.setNegativeButton("取消", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    private void htmlOpensource(){
        AlertDialog.Builder builder=new AlertDialog.Builder(WeatherActivity.this);
        builder.setTitle("刚刚天气网页进入提示");
        builder.setMessage("刚刚天气网页用于介绍本款软件\n" +
                "点击确定访问刚刚天气网页");
        builder.setPositiveButton("确定", (dialog, which) -> {
        Toast.makeText(WeatherActivity.this,"刚刚天气网页",Toast.LENGTH_SHORT).show();
        Intent intentHtml = new Intent(WeatherActivity.this, HtmlViewActivity.class);
        startActivity(intentHtml);
        });
        builder.setNegativeButton("取消", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    private void noticeOpensource(){
        AlertDialog.Builder builder=new AlertDialog.Builder(WeatherActivity.this);
        builder.setTitle("刚刚天气开源地址提示");
        builder.setMessage("刚刚天气是一个开源软件，地址：\n" +
                "https://github.com/xjg12345/ljg321.github.io\n" +
                "点击确定访问开源地址");
        builder.setPositiveButton("确定", (dialog, which) -> {
            Toast.makeText(WeatherActivity.this,"开源地址",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri=Uri.parse("https://github.com/xjg12345/ljg321.github.io");
            intent.setData(uri);
            startActivity(intent);
        });
        builder.setNegativeButton("取消", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();

    }



    @Override
    protected void onResume() {
        super.onResume();
        refreshViewPage();
    }


    private void checkPermission(){
        ArrayList<String> noPermissList=new ArrayList<>();
        List<String> permissions=new ArrayList<>();
        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(FOREGROUND_SERVICE);
        permissions.add(ACCESS_BACKGROUND_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);
        permissions.add(INTERNET);
        permissions.add(ACCESS_WIFI_STATE);

        permissions.add(CHANGE_WIFI_STATE);
        permissions.add(ACCESS_NETWORK_STATE);
        permissions.add(CHANGE_NETWORK_STATE);
        permissions.add(READ_PHONE_STATE);
        for(String permiss:permissions){
            if(ContextCompat.checkSelfPermission(this,permiss)!= PackageManager.PERMISSION_GRANTED){
                noPermissList.add(permiss);
            }
        }

        if(noPermissList.size()>0){
            ActivityCompat.requestPermissions(this,noPermissList.toArray(new String[noPermissList.size()]),0);
        }
    }

}
