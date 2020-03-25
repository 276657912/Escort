package com.cn.android.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class BindPhone1Activity extends MyActivity implements PublicInterfaceView {
    @BindView(R.id.pwd)
    EditText pwd;
    private String password;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone1;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        showLoading();
        password=pwd.getText().toString();
        presenetr.getPostTokenRequest(this, ServerUrl.panduanPassword, Constant.panduanPassword);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userid", userBean().getId());
        paramsMap.put("password", password);
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        toast(msg);
        if(code==500){
            return;
        }
        startActivity(BindPhone2Activity.class);
        this.finish();
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
    }

}