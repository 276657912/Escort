package com.cn.android.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

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
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class BindAliAccountActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.button)
    AppCompatButton button;
    @BindView(R.id.number)
    AppCompatEditText number;
    @BindView(R.id.name)
    AppCompatEditText name;

    private String alipayno, realname;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_ali_account;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        if(TextUtils.isEmpty(number.getText())||TextUtils.isEmpty(name.getText())){
            toast("请输入内容");
            return;
        }
        alipayno=number.getText().toString();
        realname=name.getText().toString();
        showLoading();
        presenetr.getPostTokenRequest(this, ServerUrl.bangdingAlipay,Constant.bangdingAlipay);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.bangdingAlipay:
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("alipayno", alipayno);
                paramsMap.put("realname", realname);
                return paramsMap;
        }
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        toast(msg);
        if (code == 500) {
            return;
        }
        UserBean bean=userBean();
        bean.setAlipayNo(alipayno);
        bean.setRealname(realname);
        bean.setIsAccount(1);
        saveUserBean(bean);
        this.finish();
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }
}