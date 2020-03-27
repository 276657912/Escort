package com.cn.android.ui.adapter;


import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.cn.android.bean.BillBean;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/11/05
 *    desc   : 可进行拷贝的副本
 */
public final class AccountDetailsAdapter extends BaseQuickAdapter<BillBean, BaseViewHolder> {

    private Context context;
    RecyclerView recyclerView;

    public AccountDetailsAdapter(Context context) {
        super(R.layout.item_account_details);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BillBean item) {
        helper.setText(R.id.pay_money,"￥"+item.getPayMoney());
        helper.setText(R.id.u_money,"余额："+item.getUmoney());
        helper.setText(R.id.time,item.getCtime());
        switch (item.getType()) {
            case  1:
                helper.setText(R.id.name,"支出");
                break;
            case  2:
                helper.setText(R.id.name,"收入");
                break;
        }
    }
}