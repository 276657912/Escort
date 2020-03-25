package com.cn.android.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.hjq.image.ImageLoader;


public class CityAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private Context context;


    public CityAdapter(Context context) {
        super(R.layout.item_city);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        ImageView view = helper.getView(R.id.city_img);
        ImageLoader.with(context)
                .load(item)
                .into(view);

    }

}
