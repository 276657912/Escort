package com.cn.android.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cn.android.R;
import com.cn.android.bean.UserBean;
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
public final class AutographActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.sign_ature)
    EditText signAture;
    private String signature;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_autograph;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
    }

    @Override
    protected void initData() {
        signAture.setText(userBean().getSignature());
    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        showLoading();
        signature=signAture.getText().toString();
        presenetr.getPostTokenRequest(this, ServerUrl.updateSignature, Constant.updateSignature);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userid", userBean().getId());
        paramsMap.put("signature", signature);
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        UserBean bean = userBean();
        bean.setSignature(signature);
        saveUserBean(bean);
        toast(msg);
        this.finish();
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }
}