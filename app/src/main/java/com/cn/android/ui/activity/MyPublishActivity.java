package com.cn.android.ui.activity;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.ui.adapter.BYImgAdapter;

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
public final class MyPublishActivity extends MyActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_publish;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        BYImgAdapter adapter=new BYImgAdapter(this);
        recyclerView.setAdapter(adapter);
        List<String> data=new ArrayList<>();
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        adapter.replaceData(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}