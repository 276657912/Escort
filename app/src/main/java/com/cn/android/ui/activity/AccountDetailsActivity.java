package com.cn.android.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.bean.BillBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.GsonUtils;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.ui.adapter.AccountDetailsAdapter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

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
public final class AccountDetailsActivity extends MyActivity implements PublicInterfaceView , OnRefreshLoadMoreListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private AccountDetailsAdapter adapter;
    private List<BillBean> list=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_details;
    }

    @Override
    protected void initView() {
        smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        smartRefreshLayout.setOnRefreshLoadMoreListener(this);
        presenetr = new PublicInterfaceePresenetr(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AccountDetailsAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(AccountDetailsActivity.this,PayDetailsActivity.class).putExtra("bean",list.get(position)));
            }
        });
    }

    @Override
    protected void initData() {
        showLoading();
        presenetr.getPostRequest(this, ServerUrl.selectAccountList, Constant.selectAccountList);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.selectAccountList:
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("page", page);
                paramsMap.put("rows", "10");
                return paramsMap;
        }
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        smartRefreshLayout.closeHeaderOrFooter();
        showComplete();
        if (code == 500) {
            return;
        }
        switch (tag) {
            case Constant.selectAccountList:
                if(page==1){
                    list.clear();
                }
                list.addAll(GsonUtils.getPersons(data, BillBean.class));
                adapter.replaceData(list);
                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        toast(error);
    }

    private int page=1;
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page=1;
        initData();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page+=1;
        initData();
    }
}