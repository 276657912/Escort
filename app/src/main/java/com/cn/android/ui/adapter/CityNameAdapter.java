package com.cn.android.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.hjq.image.ImageLoader;


public class CityNameAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;

    public CityNameAdapter(Context context) {
        super(R.layout.item_city_name);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.name,item);
        helper.addOnClickListener(R.id.name);
    }

}
