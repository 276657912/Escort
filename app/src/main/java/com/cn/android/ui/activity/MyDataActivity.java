package com.cn.android.ui.activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.bean.MyBarBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.GsonUtils;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.ui.adapter.FaBuAdapter;
import com.hjq.bar.TitleBar;
import com.hjq.dialog.MessageDialog;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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
public final class MyDataActivity extends MyActivity implements
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, PublicInterfaceView, OnRefreshLoadMoreListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title)
    TitleBar title;
    FaBuAdapter adapter;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private int page = 1;

    String del_id = "";
    private List<MyBarBean> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initView() {
        title.setTitle("我的");
        presenetr = new PublicInterfaceePresenetr(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FaBuAdapter(this);
        adapter.setDel(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        getdata();
        refresh.setOnRefreshLoadMoreListener(this);

    }

    public void getdata() {
        showLoading();
        presenetr.getPostRequest(this, ServerUrl.selectBarList, Constant.selectBarList);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, MyDataPublishActivity.class);
        intent.putExtra("bar_detail", list.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.del:
                new MessageDialog.Builder(getActivity()).setListener(new MessageDialog.OnListener() {
                    @Override
                    public void onConfirm(Dialog dialog) {
                        del_id = list.get(position).getId();
                        showLoading();
                        presenetr.getPostTokenRequest(getActivity(), ServerUrl.deleteBarById, Constant.deleteBarById);
                    }

                    @Override
                    public void onCancel(Dialog dialog) {

                    }
                })
                        .setTitle("删除")
                        .setMessage("确定删除该草稿吗？")
                        .setConfirm("确定")
                        .setCancel("取消")
                        .show();
                break;
        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> map = new HashMap<>();
        switch (tag) {
            case Constant.selectBarList:
                map.put("userid", userBean().getId());
                map.put("page", page);
                map.put("rows", 10);
                return map;
            case Constant.deleteBarById:
                map.put("id", del_id);
                break;
        }
        return map;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        View view = View.inflate(getActivity(), R.layout.no_data_layout, null);
        switch (tag) {
            case Constant.selectBarList:
                list = GsonUtils.getPersons(data, MyBarBean.class);
                adapter.setNewData(list);
                if (list.size() == 0)
                    adapter.setEmptyView(view);
                break;
            case Constant.deleteBarById:
                ToastUtils.show("删除成功");
                getdata();
                onRefresh(refresh);
                break;
        }

    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        Log.e("sss", error);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        getdata();
        page++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenetr != null)
            getdata();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (list.size() > 0) {
            list.clear();
        }
        getdata();
        page = 1;
    }
}