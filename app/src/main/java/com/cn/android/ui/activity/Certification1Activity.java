package com.cn.android.ui.activity;


import com.cn.android.R;
import com.cn.android.common.MyActivity;

import butterknife.OnClick;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class Certification1Activity extends MyActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_certification1;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        startActivity(Certification2Activity.class);
        this.finish();
    }
}