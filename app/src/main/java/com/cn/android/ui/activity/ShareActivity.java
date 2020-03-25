package com.cn.android.ui.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class ShareActivity extends MyActivity {

    @BindView(R.id.bao_cun)
    TextView baoCun;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.bao_cun)
    public void onViewClicked() {
        toast("保存成功");
    }
}