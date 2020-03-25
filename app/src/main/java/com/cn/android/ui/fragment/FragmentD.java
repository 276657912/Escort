package com.cn.android.ui.fragment;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.common.MyLazyFragment;
import com.cn.android.ui.activity.CopyActivity;
import com.cn.android.ui.activity.HomeActivity;
import com.cn.android.ui.adapter.DAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class FragmentD extends MyLazyFragment<CopyActivity> {
    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private DAdapter adapter;

    public static FragmentD newInstance() {
        return new FragmentD();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_d;
    }

    @Override
    protected void initView() {
        ((HomeActivity)getActivity()).showGif(true);
        ImmersionBar.setTitleBar(getAttachActivity(), title);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new DAdapter(getActivity());
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