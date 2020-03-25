package com.cn.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.cn.android.R;
import com.cn.android.bean.UserBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.gyf.immersionbar.ImmersionBar;
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
 * desc   : 注册界面
 */
public final class Logout2Activity extends MyActivity  implements PublicInterfaceView , TextWatcher {


    @BindView(R.id.yz1)
    AppCompatTextView yz1;
    @BindView(R.id.yz2)
    AppCompatTextView yz2;
    @BindView(R.id.yz3)
    AppCompatTextView yz3;
    @BindView(R.id.yz4)
    AppCompatTextView yz4;
    @BindView(R.id.yz)
    AppCompatEditText yz;
    @BindView(R.id.cv_phone_verify_countdown)
    CountdownView countdown;
    private String userphone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logout2;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
    }

    @Override
    protected void initData() {
        yz.addTextChangedListener(this);
    }

    @Override
    protected ImmersionBar statusBarConfig() {
        // 不要把整个布局顶上去
        return super.statusBarConfig().keyboardEnable(true);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    private StringBuffer str = new StringBuffer();

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() == 0) {
            return;
        }
        if (s.length() > str.length()) {
            switch (s.length()) {
                case 1:
                    yz1.setText(s.toString().substring(0, 1));
                    str.append(s.toString().substring(0, 1));
                    break;
                case 2:
                    yz2.setText(s.toString().substring(1, 2));
                    str.append(s.toString().substring(1, 2));
                    break;
                case 3:
                    yz3.setText(s.toString().substring(2, 3));
                    str.append(s.toString().substring(1, 2));
                    break;
                case 4:
                    yz4.setText(s.toString().substring(3, 4));
                    str.append(s.toString().substring(1, 2));
                    startActivity(new Intent(this, Logout3Activity.class).putExtra("code", str.toString()));
                    this.finish();
                    break;
            }
        } else {
            str = new StringBuffer();
            yz.setText("");
            yz1.setText("");
            yz2.setText("");
            yz3.setText("");
            yz4.setText("");
        }
    }

    @OnClick(R.id.cv_phone_verify_countdown)
    public void onViewClicked() {
        showLoading();
        userphone = userBean().getUserphone();
        presenetr.getPostRequest(this, ServerUrl.sendSms, Constant.sendSms);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
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
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        countdown.resetState();
    }
}