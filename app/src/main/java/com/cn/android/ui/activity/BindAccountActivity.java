package com.cn.android.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

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
public final class BindAccountActivity extends MyActivity {

    @BindView(R.id.to_next)
    LinearLayout toNext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_account;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.to_next)
    public void onViewClicked() {
        startActivity(BindAliAccountActivity.class);
        this.finish();
    }
}