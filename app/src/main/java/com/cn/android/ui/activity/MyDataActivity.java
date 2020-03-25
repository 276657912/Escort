package com.cn.android.ui.activity;


import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.ui.adapter.FaBuAdapter;
import com.cn.android.ui.adapter.FollowAdapter;
import com.hjq.bar.TitleBar;
import com.hjq.dialog.MessageDialog;

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
public final class MyDataActivity extends MyActivity implements
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title)
    TitleBar title;
    FaBuAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initView() {
        title.setTitle("我的");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FaBuAdapter(this);
        adapter.setDel(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void initData() {
        List<String> data = new ArrayList<>();
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        adapter.replaceData(data);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(MyDataPublishActivity.class);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.del:
                new MessageDialog.Builder(getActivity())
                        .setTitle("删除")
                        .setMessage("确定删除该草稿吗？")
                        .setConfirm("确定")
                        .setCancel("取消")
                        .show();
                break;
        }
    }
}