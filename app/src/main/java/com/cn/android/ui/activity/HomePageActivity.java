package com.cn.android.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.ui.adapter.HomePageVideoAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.image.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class HomePageActivity extends MyActivity {

    @BindView(R.id.black)
    LinearLayout black;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.to_photo)
    LinearLayout toPhoto;
    private HomePageVideoAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_homepage;
    }

    @Override
    protected void initView() {
        ImmersionBar.setTitleBar(this, title);
        ImageLoader.with(this).load(R.mipmap.test_1).circle().into(headImage);
        videoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new HomePageVideoAdapter(getActivity());
        videoRecyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(BuyVideoActivity.class);
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


    @OnClick({R.id.title, R.id.to_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                this.finish();
                break;
            case R.id.to_photo:
                ImageActivity.start(this,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583411682541&di=bf12db2954338a06205dcf9a9c12d382&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170504%2F20170504131330_ffb5fd3b5f251f3a5340749b9c746914_2.jpeg");
                break;
        }
    }
}