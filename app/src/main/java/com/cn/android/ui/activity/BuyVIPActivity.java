package com.cn.android.ui.activity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.ui.adapter.BuyVIPAdapter;
import com.hjq.image.ImageLoader;

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
public final class BuyVIPActivity extends MyActivity {

    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.buy)
    Button buy;
    BuyVIPAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_vip;
    }

    @Override
    protected void initView() {
        ImageLoader.with(this).load(R.mipmap.test_1).circle().into(headImage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        adapter=new BuyVIPAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        List<String> data = new ArrayList<>();
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        adapter.replaceData(data);
    }
}