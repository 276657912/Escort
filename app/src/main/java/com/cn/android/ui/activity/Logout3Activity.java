package com.cn.android.ui.activity;

import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;

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
 * desc   : 登录界面
 */
public final class Logout3Activity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.checkbox)
    AppCompatCheckBox checkbox;
    @BindView(R.id.button)
    AppCompatButton button;
    private String code;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logout3;
    }

    @Override
    protected void initView() {
        code=getIntent().getStringExtra("code");
        presenetr = new PublicInterfaceePresenetr(this);
    }

    @Override
    protected void initData() {
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    button.setEnabled(true);
                }else{
                    button.setEnabled(false);
                }
            }
        });
    }


    @OnClick(R.id.button)
    public void onViewClicked() {
        showLoading();
        presenetr.getPostTokenRequest(this, ServerUrl.cancelAppUser, Constant.cancelAppUser);
    }

    @Override
    protected ImmersionBar statusBarConfig() {
        // 不要把整个布局顶上去
        return super.statusBarConfig().keyboardEnable(true);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userid", userBean().getId());
        paramsMap.put("smscode", code);
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        toast(msg);
        if(code==500){
            return;
        }
        this.finish();
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
    }
}