package com.cn.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.gyf.immersionbar.ImmersionBar;

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
public final class RegisterActivity extends MyActivity implements PublicInterfaceView {


    @BindView(R.id.et_register_code)
    AppCompatEditText etRegisterCode;
    @BindView(R.id.btn_register_commit)
    AppCompatTextView btnRegisterCommit;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        presenetr=new PublicInterfaceePresenetr(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected ImmersionBar statusBarConfig() {
        // 不要把整个布局顶上去
        return super.statusBarConfig().keyboardEnable(true);
    }

    @OnClick({R.id.btn_register_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register_commit:
                if(TextUtils.isEmpty(etRegisterCode.getText())){
                    toast("请输入内容");
                    return;
                }
                showLoading();
                phone=etRegisterCode.getText().toString();
                presenetr.getPostRequest(this, ServerUrl.selectAppUserByPhone, Constant.selectAppUserByPhone);
                break;
        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case  Constant.selectAppUserByPhone:
                paramsMap.put("userphone",phone);
                return paramsMap;
        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        switch (tag) {
            case  Constant.selectAppUserByPhone:
                switch (code) {
                    case  200:
                        startActivity(new Intent(this,LoginActivity.class).putExtra("phone",phone));
                        break;
                    case  500:
                        startActivity(new Intent(this,Register2Activity.class).putExtra("phone",phone));
                        break;
                }
                this.finish();
                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        toast(error);
    }
}