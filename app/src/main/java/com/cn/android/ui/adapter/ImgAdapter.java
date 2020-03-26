package com.cn.android.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.hjq.image.ImageLoader;

import java.util.List;


public class ImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;


    public ImgAdapter(Context context) {
        super(R.layout.item_img);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageLoader.with(helper.itemView.getContext()).load(item).circle(10).into(helper.getView(R.id.img));
//        ImageView view = helper.getView(R.id.img);
//        ImageLoader.with(context)
//                .load(item)
//                .into(view);

    }

}
