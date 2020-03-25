package com.cn.android.ui.fragment;


import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.common.MyLazyFragment;
import com.cn.android.ui.activity.CopyActivity;
import com.cn.android.ui.activity.HomeActivity;
import com.cn.android.ui.activity.HomePageActivity;
import com.cn.android.ui.adapter.BAdapter;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class FragmentB extends MyLazyFragment<CopyActivity> {


    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private BAdapter adapter;

    public static FragmentB newInstance() {
        return new FragmentB();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_b;
    }

    @Override
    protected void initView() {
        ((HomeActivity)getActivity()).showGif(true);
        ImmersionBar.setTitleBar(getAttachActivity(), title);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter=new BAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(HomePageActivity.class);
            }
        });
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
        data.add(new String());
        data.add(new String());
        data.add(new String());
        adapter.replaceData(data);
    }
}