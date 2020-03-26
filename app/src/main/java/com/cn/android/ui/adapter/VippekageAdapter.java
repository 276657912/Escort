package com.cn.android.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.cn.android.bean.VipPeackge;

import java.util.List;

public class VippekageAdapter extends BaseQuickAdapter<VipPeackge, BaseViewHolder> {
    public VippekageAdapter(@Nullable List<VipPeackge> data) {
        super(R.layout.item_vip_paeackge,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VipPeackge item) {
        helper.setText(R.id.paeckge_type,item.getName());
        helper.setText(R.id.packge_price,String.format("（%s/月）",item.getPreferMoney()));
        helper.setText(R.id.packge_money,String.format("￥%s",item.getMoney()));

    }
}
