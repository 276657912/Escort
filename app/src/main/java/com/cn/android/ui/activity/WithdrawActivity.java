package com.cn.android.ui.activity;


import android.os.Bundle;
import android.view.View;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.ui.dialog.MenuDialog;
import com.cn.android.utils.SPUtils;
import com.hjq.dialog.base.BaseDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class WithdrawActivity extends MyActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        startActivity(PayDetailsActivity.class);
        this.finish();
    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        List<String> str=new ArrayList<>();
        str.add("确定要解绑支付宝账号吗？");
        str.add("确定");
        new MenuDialog.Builder(this)
                .setList(str)
                .setListener(new MenuDialog.OnListener() {
                    @Override
                    public void onSelected(BaseDialog dialog, int position, Object o) {
                        if(position==1){
                        }
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {

                    }
                })
                .show();
    }
}