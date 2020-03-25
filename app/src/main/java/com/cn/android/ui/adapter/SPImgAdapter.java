package com.cn.android.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;


public class SPImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;


    public SPImgAdapter(Context context) {
        super(R.layout.item_sp_img);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        ImageView view = helper.getView(R.id.img);
//        ImageLoader.with(context)
//                .load(item)
//                .into(view);

    }

}
