package com.cn.android.ui.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.cn.android.R;
import com.cn.android.bean.BillBean;
import com.cn.android.common.MyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class PayDetailsActivity extends MyActivity {
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.pay_money)
    TextView payMoney;
    @BindView(R.id.pay_type)
    TextView payType;
    @BindView(R.id.u_money)
    TextView uMoney;
    @BindView(R.id.time)
    TextView time;
    private BillBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_details;
    }

    @Override
    protected void initView() {
        bean = (BillBean) getIntent().getSerializableExtra("bean");
        switch (bean.getType()) {
            case  1:
                type.setText("支出");
                break;
            case  2:
                type.setText("收入");
                break;
        }
        switch (bean.getPayType()) {
            case  1:
                payType.setText("支付宝");
                break;
            case  2:
                payType.setText("微信");
                break;
            case  3:
                payType.setText("提现");
                break;
        }
        payMoney.setText("￥"+bean.getPayMoney());
        uMoney.setText(bean.getUmoney());
        time.setText(bean.getCtime());
    }

    @Override
    protected void initData() {

    }

}