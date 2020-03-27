package com.cn.android.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class MyMoneyActivity extends MyActivity {

    @BindView(R.id.money)
    TextView money;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_money;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        money.setText(userBean().getUmoney());
    }

    @OnClick({R.id.ti_xian, R.id.ming_xi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ti_xian:
                startActivity(WithdrawActivity.class);
                break;
            case R.id.ming_xi:
                startActivity(AccountDetailsActivity.class);
                break;
        }
    }

}