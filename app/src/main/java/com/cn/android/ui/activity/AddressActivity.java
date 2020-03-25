package com.cn.android.ui.activity;


import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.bean.CityNameBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.GsonUtils;
import com.cn.android.ui.adapter.CityAdapter;
import com.cn.android.ui.adapter.CityNameAdapter;

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
    List<Integer> imgData=new ArrayList<>();
    List<String> cityName=new ArrayList<>();
    List<CityNameBean> cityList=new ArrayList<>();
    CityAdapter cityAdapter;
    CityNameAdapter nameAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adr;
    }

    @Override
    protected void initView() {
        cityRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter=new CityAdapter(this);
        nameAdapter=new CityNameAdapter(this);
        cityRecyclerView.setAdapter(cityAdapter);
        recyclerView.setAdapter(nameAdapter);
    }

    @Override
    protected void initData() {
        imgData.add(R.mipmap.test_5);
        imgData.add(R.mipmap.test_6);
        imgData.add(R.mipmap.test_7);
        cityAdapter.replaceData(imgData);
        cityList= GsonUtils.getPersons(getAssetsString(this,"province.json"),CityNameBean.class);
        for (int i = 0; i <cityList.size() ; i++) {
            for (int j = 0; j <cityList.get(i).getCity().size() ; j++) {
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
}