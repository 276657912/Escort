package com.cn.android.ui.fragment;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.common.MyLazyFragment;
import com.cn.android.ui.activity.CopyActivity;
import com.cn.android.ui.activity.MyPublishActivity;
import com.cn.android.ui.adapter.AMenu1Adapter;
import com.cn.android.ui.adapter.AMenu2Adapter;
import com.cn.android.ui.adapter.FaBuAdapter;
import com.hjq.base.BaseRecyclerViewAdapter;
import com.hjq.dialog.MessageDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class BYFragment extends MyLazyFragment<CopyActivity> implements
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.fa_bu)
    TextView faBu;
    @BindView(R.id.cao_gao)
    TextView caoGao;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    FaBuAdapter adapter;

    public static BYFragment newInstance() {
        return new BYFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_by;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FaBuAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        List<String> data = new ArrayList<>();
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        adapter.replaceData(data);
        faBu.setBackgroundResource(R.drawable.bg_radius_white);
        caoGao.setBackground(null);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.fa_bu, R.id.cao_gao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fa_bu:
                faBu.setBackgroundResource(R.drawable.bg_radius_white);
                caoGao.setBackground(null);
                adapter.setDel(false);
                break;
            case R.id.cao_gao:
                caoGao.setBackgroundResource(R.drawable.bg_radius_white);
                faBu.setBackground(null);
                adapter.setDel(true);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(MyPublishActivity.class);
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