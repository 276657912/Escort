package com.cn.android.ui.activity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.bean.MyBarBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.ui.adapter.ImgAdapter;
import com.hjq.image.ImageLoader;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class MyDataPublishActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    MyBarBean barBean;
    ImgAdapter adapter;
    List<String> data = new ArrayList<>();
    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.adr)
    TextView adr;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.btn_test_login)
    AppCompatButton btnTestLogin;
    private String[] strs;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_data_publish;
    }

    @Override
    protected void initView() {
        presenetr=new PublicInterfaceePresenetr(this);
        barBean = (MyBarBean) getIntent().getSerializableExtra("bar_detail");

    }

    @Override
    protected void initData() {
        ImageLoader.with(this).load(userBean().getHeadimg()).into(headImg);
        name.setText(userBean().getNickname());
        adr.setText(barBean.getCity());
        content.setText(barBean.getContent());
        time.setText(String.format("发布时间：%s",barBean.getCtime()));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        ImgAdapter adapter = new ImgAdapter(this);
        recyclerView.setAdapter(adapter);
        if (barBean.getImgUrls().contains(",")) {
            strs = barBean.getImgUrls().split(",");
            data = Arrays.asList(strs);
        } else {
            data.add(barBean.getImgUrls());
        }
        adapter.replaceData(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test_login)
    public void onViewClicked() {
        showLoading();
        presenetr.getPostTokenRequest(getActivity(), ServerUrl.deleteBarById, Constant.deleteBarById);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> map=new HashMap<>();
        map.put("id",barBean.getId());
        return map;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        ToastUtils.show("删除成功");
        finish();

    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        Log.e("sss",error);
    }
}