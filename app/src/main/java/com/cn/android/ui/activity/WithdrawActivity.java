package com.cn.android.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cn.android.R;
import com.cn.android.bean.UserBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.ui.dialog.MenuDialog;
import com.hjq.dialog.base.BaseDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public final class WithdrawActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.alipay_no)
    TextView alipayNo;
    @BindView(R.id.u_money)
    TextView uMoney;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.money)
    EditText money;
    private String getMoney;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
        alipayNo.setText("支付宝账号：" + userBean().getAlipayNo());
        uMoney.setText("可用余额：" + userBean().getUmoney() + "元");
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        showLoading();
        if (TextUtils.isEmpty(money.getText())) {
            toast("请输入内容");
            return;
        }
        getMoney = money.getText().toString();
        presenetr.getPostTokenRequest(this, ServerUrl.tixian, Constant.tixian);
    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        List<String> str = new ArrayList<>();
        str.add("确定要解绑支付宝账号吗？");
        str.add("确定");
        new MenuDialog.Builder(this)
                .setList(str)
                .setListener(new MenuDialog.OnListener() {
                    @Override
                    public void onSelected(BaseDialog dialog, int position, Object o) {
                        if (position == 1) {
                            showLoading();
                            presenetr.getPostTokenRequest(WithdrawActivity.this, ServerUrl.jiebangAlipay, Constant.jiebangAlipay);
                        }
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {

                    }
                })
                .show();
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.tixian:
                paramsMap.put("userid", userBean().getId());
                paramsMap.put("money", getMoney);
                return paramsMap;
            case Constant.jiebangAlipay:
                paramsMap.put("userid", userBean().getId());
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
        switch (tag) {
            case Constant.jiebangAlipay:
                UserBean bean2=userBean();
                bean2.setAlipayNo("");
                bean2.setRealname("");
                bean2.setIsAccount(2);
                saveUserBean(bean2);
                this.finish();
                break;
            case Constant.tixian:
                double d = Double.valueOf(data);
                DecimalFormat df = new DecimalFormat("#.0");
                String str = df.format(d);
                UserBean bean=userBean();
                bean.setUmoney(str);
                saveUserBean(bean);
                this.finish();
                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        toast(error);
//        countdown.resetState();
    }

}