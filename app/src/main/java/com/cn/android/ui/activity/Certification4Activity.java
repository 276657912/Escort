package com.cn.android.ui.activity;


import com.cn.android.R;
import com.cn.android.bean.UserBean;
import com.cn.android.common.MyActivity;

import butterknife.OnClick;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class Certification4Activity extends MyActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_certification4;
    }

    @Override
    protected void initView() {
        UserBean userBean=userBean();
        userBean.setIsReal(1);
        saveUserBean(userBean);
    }

    @Override
    protected void initData() {

    }

}