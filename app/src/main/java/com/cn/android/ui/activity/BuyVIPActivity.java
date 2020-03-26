package com.cn.android.ui.activity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.bean.VipLogo;
import com.cn.android.bean.VipPeackge;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.GsonUtils;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.ui.adapter.BuyVIPAdapter;
import com.cn.android.ui.adapter.VippekageAdapter;
import com.hjq.image.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class BuyVIPActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.buy)
    Button buy;
    BuyVIPAdapter adapter;
    VippekageAdapter vippekageAdapter;
    PublicInterfaceePresenetr presenetr;
    @BindView(R.id.packge_recycle)
    RecyclerView packgeRecycle;
    private List<VipLogo> logos;
    private List<VipPeackge> packges;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_vip;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
        ImageLoader.with(this).load(R.mipmap.test_1).circle().into(headImage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapter = new BuyVIPAdapter(this);
        recyclerView.setAdapter(adapter);
        packgeRecycle.setLayoutManager(new LinearLayoutManager(this));
        vippekageAdapter=new VippekageAdapter(null);
        packgeRecycle.setAdapter(vippekageAdapter);
        getdata();
    }

    public void getdata() {
        showLoading();
        presenetr.getPostRequest(this, ServerUrl.selectVipLogoList, Constant.selectVipLogoList);
        presenetr.getPostRequest(this, ServerUrl.selectVipPackggeList, Constant.selectVipPackggeList);


    }

    @Override
    protected void initData() {
        List<String> data = new ArrayList<>();
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());

    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> map = new HashMap<>();
        switch (tag) {
            case Constant.selectVipPackggeList:
                return map;
            case Constant.selectVipLogoList:
                return map;
        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        switch (tag) {
            case Constant.selectVipPackggeList:
                packges=GsonUtils.getPersons(data, VipPeackge.class);
                vippekageAdapter.setNewData(packges);
                break;
            case Constant.selectVipLogoList:
                logos = GsonUtils.getPersons(data, VipLogo.class);
                adapter.setNewData(logos);

                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        Log.e("error", error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}