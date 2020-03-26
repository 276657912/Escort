package com.cn.android.ui.activity;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.bean.CityNameBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.GsonUtils;
import com.cn.android.ui.adapter.CityAdapter;
import com.cn.android.ui.adapter.CityNameAdapter;
import com.cn.android.utils.SPUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class AddressActivity extends MyActivity {
    @BindView(R.id.city_recyclerView)
    RecyclerView cityRecyclerView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<Integer> imgData = new ArrayList<>();
    List<String> cityName = new ArrayList<>();
    List<CityNameBean> cityList = new ArrayList<>();
    CityAdapter cityAdapter;
    CityNameAdapter nameAdapter;
    @BindView(R.id.city_name)
    TextView mcityName;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private String city;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adr;
    }

    @Override
    protected void initView() {
        cityRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter = new CityAdapter(this);
        nameAdapter = new CityNameAdapter(this);
        cityRecyclerView.setAdapter(cityAdapter);
        recyclerView.setAdapter(nameAdapter);
        nameAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(cityName.size()!=0){
                    mcityName.setText(cityName.get(position));
                    SPUtils.putString("city",cityName.get(position));
                }
            }
        });
        mLocationClient = new AMapLocationClient(this);
        mLocationClient.setLocationListener(mAMapLocationListener);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setNeedAddress(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }
    AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    city = amapLocation.getCity();
                    mcityName.setText(city);
                    SPUtils.putString("city",city);
                    Log.e("123", "onLocationChanged = " + amapLocation.getCity());
                } else {
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };
    @Override
    protected void initData() {
        imgData.add(R.mipmap.test_5);
        imgData.add(R.mipmap.test_6);
        imgData.add(R.mipmap.test_7);
        cityAdapter.replaceData(imgData);
        cityList = GsonUtils.getPersons(getAssetsString(this, "province.json"), CityNameBean.class);
        for (int i = 0; i < cityList.size(); i++) {
            for (int j = 0; j < cityList.get(i).getCity().size(); j++) {
                cityName.add(cityList.get(i).getCity().get(j).getName());
            }
        }
        nameAdapter.replaceData(cityName);
    }

    /**
     * 获取资产目录下面文件的字符串
     */
    private static String getAssetsString(Context context, String file) {
        try {
            InputStream inputStream = context.getAssets().open(file);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, length);
            }
            outStream.close();
            inputStream.close();
            return outStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}