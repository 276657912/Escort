package com.cn.android.ui.adapter;


import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.cn.android.bean.VipLogo;
import com.cn.android.bean.VipPeackge;
import com.hjq.image.ImageLoader;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/11/05
 *    desc   : 可进行拷贝的副本
 */
public final class BuyVIPAdapter extends BaseQuickAdapter<VipLogo, BaseViewHolder> {

    private Context context;
    RecyclerView recyclerView;

    public BuyVIPAdapter(Context context) {
        super(R.layout.item_buy_vip);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, VipLogo item) {
        ImageLoader.with(helper.itemView.getContext()).load(item.getImgUrl()).into(helper.getView(R.id.image_view));
        helper.setText(R.id.vip_title,item.getTitle());
        helper.setText(R.id.content,item.getContent());
    }
}