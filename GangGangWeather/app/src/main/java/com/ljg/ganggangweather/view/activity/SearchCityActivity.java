package com.ljg.ganggangweather.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.ljg.ganggangweather.bean.txbean.CityShip;
import com.ljg.ganggangweather.bean.txbean.WeatherBean;
import com.ljg.ganggangweather.txweather.TxCityLocationListener;
import com.ljg.ganggangweather.txweather.TxCityRequestListener;
import com.ljg.ganggangweather.txweather.TxLocationHelper;
import com.ljg.ganggangweather.txweather.TxWeatherHelper;
import com.ljg.ganggangweather.txweather.TxWeatherRequestListener;
import com.ljg.ganggangweather.R;
import com.ljg.ganggangweather.view.adapter.SearchResultAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.ljg.ganggangweather.util.DefaultParams.OPCODE_ADD;
import static com.ljg.ganggangweather.util.DefaultParams.OPCODE_NONE;

public class SearchCityActivity extends AppCompatActivity {
    private static final String TAG="搜索结果";

    private TextView tv_current_position;
    private CityShip currentCityShip;

    private String[]hotCitys = {"北京","上海","广州","深圳","珠海","佛山","南京","苏州","厦门","长沙","成都","福州",
            "杭州","武汉","青岛","西安","太原","沈阳","重庆","天津","南宁"};
    private GridView gv_host_city;
    private ArrayAdapter<String> adapter;


    private RecyclerView rv_search_result;
    private List<CityShip> cityShipList=new ArrayList<>();
    private SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        viewInit();
        refreshView();
    }
    private void viewInit(){
        Toolbar tb_search=findViewById(R.id.tb_search);
        setSupportActionBar(tb_search);
        getSupportActionBar().setTitle("");

        tv_current_position=findViewById(R.id.tv_current_position);
        tv_current_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TxWeatherHelper.requestCity(currentCityShip,listener);
            }
        });

        gv_host_city=findViewById(R.id.gv_host_city);
        adapter=new ArrayAdapter<>(this,R.layout.item_city,hotCitys);
        gv_host_city.setAdapter(adapter);
        gv_host_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TxWeatherHelper.requestCity(hotCitys[position],listener);
            }
        });

        rv_search_result=findViewById(R.id.rv_search_result);
        searchResultAdapter=new SearchResultAdapter(cityShipList);
        rv_search_result.setLayoutManager(new LinearLayoutManager(this));
        rv_search_result.setAdapter(searchResultAdapter);
    }

    private void refreshView(){
        if(cityShipList.size()!=0){
            rv_search_result.setVisibility(View.VISIBLE);
            gv_host_city.setVisibility(View.GONE);
            searchResultAdapter.notifyDataSetChanged();
        }else {
            gv_host_city.setVisibility(View.VISIBLE);
            rv_search_result.setVisibility(View.GONE);
        }
        TxLocationHelper.getCurrentLocation(SearchCityActivity.this,txCityLocationListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item=menu.findItem(R.id.menu_search_search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setQueryHint("搜索城市名");
        searchView.setIconified(false);
        searchView.setQueryHint("北京");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchResultAdapter.setListener(listener);
                TxWeatherHelper.searchCity(query,cityRequestListener);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){
                    cityShipList.clear();
                    refreshView();
                }
                return true;
            }
        });
        return true;
    }


    private TxCityRequestListener cityRequestListener=new TxCityRequestListener() {
        @Override
        public void onSuccess(List<CityShip> cityShips) {
            cityShipList.clear();
            cityShipList.addAll(cityShips);
            tv_current_position.setVisibility(View.VISIBLE);
            refreshView();

        }

        @Override
        public void onFailed(String reason) {
            gv_host_city.setVisibility(View.VISIBLE);//请求失败，还显示热门城市
            Toast.makeText(SearchCityActivity.this,reason,Toast.LENGTH_SHORT).show();
            tv_current_position.setVisibility(View.GONE);
        }
    };




    private TxWeatherRequestListener listener=new TxWeatherRequestListener() {
        @Override
        public void onSuccess(CityShip cityShip, WeatherBean weatherBean) {

            AlertDialog.Builder builder=new AlertDialog.Builder(SearchCityActivity.this);
            builder.setMessage("搜索成功，点击确定添加到城市列表");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(SearchCityActivity.this, CityManageActivity.class);
                    intent.putExtra("cityShip",cityShip);
                    intent.putExtra("weatherBean",weatherBean);
                    intent.putExtra("opcode",OPCODE_ADD);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }

        @Override
        public void onFailed(String reason) {
            Toast.makeText(SearchCityActivity.this,"请求失败："+reason,Toast.LENGTH_SHORT).show();
            Log.i(TAG,reason);
        }
    };

    private TxCityLocationListener txCityLocationListener=new TxCityLocationListener() {
        @Override
        public void onSuccess(CityShip cityShip) {
            tv_current_position.setVisibility(View.VISIBLE);
            tv_current_position.setText("当前位置："+cityShip.getProvince()+"/"+cityShip.getCity()+"/"+cityShip.getCountry());
            currentCityShip=cityShip;
            Log.i("定位结果",cityShip.toString());
        }

        @Override
        public void onFailed(String reason) {
            Log.i("定位","定位失败");
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent=new Intent(SearchCityActivity.this, CityManageActivity.class);
            intent.putExtra("opcode",OPCODE_NONE);
            setResult(RESULT_OK,intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
