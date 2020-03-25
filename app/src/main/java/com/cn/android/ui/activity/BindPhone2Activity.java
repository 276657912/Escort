package com.cn.android.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

import com.cn.android.R;
import com.cn.android.bean.UserBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.hjq.widget.view.CountdownView;

import java.util.HashMap;
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
public final class BindPhone2Activity extends MyActivity implements PublicInterfaceView {
    @BindView(R.id.phone)
    AppCompatEditText phone;
    @BindView(R.id.cv_phone_verify_countdown)
    CountdownView countdown;
    @BindView(R.id.code)
    EditText code;
    private String userphone, smscode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone2;
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
        if(TextUtils.isEmpty(phone.getText())||TextUtils.isEmpty(code.getText())){
            toast("请输入内容");
            return;
        }
        userphone = phone.getText().toString();
        smscode = code.getText().toString();
        presenetr.getPostTokenRequest(this, ServerUrl.updatephone, Constant.updatephone);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.updatephone:
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("userphone", userphone);
                paramsMap.put("smscode", smscode);
                return paramsMap;
            case Constant.sendSms:
                paramsMap.put("loginName", userphone);
                return paramsMap;
        }
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        toast(msg);
        if(code==500){
            return;
        }
        switch (tag) {
            case Constant.updatephone:
                UserBean bean=userBean();
                bean.setUserphone(userphone);
                saveUserBean(bean);
                this.finish();
                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        countdown.resetState();
    }

    @OnClick(R.id.cv_phone_verify_countdown)
    public void onViewClicked() {
        if(TextUtils.isEmpty(phone.getText())){
            toast("请输入内容");
            countdown.resetState();
            return;
        }
        showLoading();
        userphone = phone.getText().toString();
        presenetr.getPostRequest(this, ServerUrl.sendSms, Constant.sendSms);
    }
}