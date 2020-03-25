package com.cn.android.ui.activity;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.ui.adapter.AccountDetailsAdapter;
import com.cn.android.ui.adapter.DAdapter;

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
public final class AccountDetailsActivity extends MyActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private AccountDetailsAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_details;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AccountDetailsAdapter(this);
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