package com.cn.android.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.hjq.image.ImageLoader;


public class BYImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;


    public BYImgAdapter(Context context) {
        super(R.layout.item_by_img);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {

        ImageView view = helper.getView(R.id.img);
        ImageLoader.with(context)
                .load(item)
                .into(view);

    }

}
