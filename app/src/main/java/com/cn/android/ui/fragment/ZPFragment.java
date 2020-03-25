package com.cn.android.ui.fragment;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.common.MyLazyFragment;
import com.cn.android.ui.activity.CopyActivity;
import com.cn.android.ui.adapter.FaBuAdapter;
import com.cn.android.ui.adapter.ImgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class ZPFragment extends MyLazyFragment<CopyActivity> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ImgAdapter adapter;

    public static ZPFragment newInstance() {
        return new ZPFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zp;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter=new ImgAdapter(getActivity());
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

    @Override
    protected void initData() {

    }
}