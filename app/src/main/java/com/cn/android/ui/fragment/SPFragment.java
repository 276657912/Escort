package com.cn.android.ui.fragment;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.common.MyLazyFragment;
import com.cn.android.ui.activity.CopyActivity;
import com.cn.android.ui.adapter.ImgAdapter;
import com.cn.android.ui.adapter.SPImgAdapter;

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
public final class SPFragment extends MyLazyFragment<CopyActivity> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SPImgAdapter adapter;

    public static SPFragment newInstance() {
        return new SPFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sp;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new SPImgAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        List<String> data = new ArrayList<>();
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        adapter.replaceData(data);
    }
}