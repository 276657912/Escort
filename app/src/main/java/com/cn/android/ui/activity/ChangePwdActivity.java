package com.cn.android.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
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
public final class ChangePwdActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.old_password)
    EditText oldPassword;
    @BindView(R.id.new_password1)
    EditText newPassword1;
    @BindView(R.id.new_password2)
    EditText newPassword2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_pwd;
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
        if(TextUtils.isEmpty(oldPassword.getText())||TextUtils.isEmpty(newPassword1.getText())||TextUtils.isEmpty(newPassword2.getText())){
            return;
        }
        if(!newPassword1.getText().equals(newPassword2.getText())){
            return;
        }
        showLoading();
        presenetr.getPostTokenRequest(this, ServerUrl.updatePassword, Constant.updatePassword);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userid", userBean().getId());
        paramsMap.put("oldPassword", oldPassword.getText().toString());
        paramsMap.put("newpassword", newPassword1.getText().toString());
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        UserBean bean = userBean();
        bean.setPassword(newPassword1.getText().toString());
        saveUserBean(bean);
        toast(msg);
        this.finish();
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}