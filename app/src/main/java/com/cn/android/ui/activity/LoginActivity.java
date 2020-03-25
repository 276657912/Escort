package com.cn.android.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.cn.android.R;
import com.cn.android.bean.UserBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.GsonUtils;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.utils.SPUtils;
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
 * desc   : 登录界面
 */
public final class LoginActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.phone)
    AppCompatTextView mPhone;
    @BindView(R.id.passWord)
    AppCompatEditText passWord;
    @BindView(R.id.btn_commit)
    AppCompatTextView btnCommit;
    private String userphone;
    private String password;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        presenetr=new PublicInterfaceePresenetr(this);
        userphone = getIntent().getStringExtra("phone");
        mPhone.setText(userphone);
    }

    @Override
    protected ImmersionBar statusBarConfig() {
        // 不要把整个布局顶上去
        return super.statusBarConfig().keyboardEnable(true);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                password=passWord.getText().toString();
                if(TextUtils.isEmpty(passWord.getText())){
                    toast("请输入内容");
                    return;
                }
                showLoading();
                presenetr.getPostRequest(this, ServerUrl.judgePassword, Constant.judgePassword);
                break;
        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case  Constant.judgePassword:
                paramsMap.put("userphone",userphone);
                paramsMap.put("password",password);
                return paramsMap;
        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        switch (tag) {
            case  Constant.judgePassword:
                toast(msg);
                if(code==500){
                    return;
                }
                UserBean bean = GsonUtils.getPerson(data, UserBean.class);
                SPUtils.putString("token", bean.getToken());
                saveUserBean(bean);
                this.finish();
                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        toast(error);

    }
}