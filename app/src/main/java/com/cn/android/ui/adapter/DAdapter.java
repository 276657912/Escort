package com.cn.android.ui.adapter;


import android.content.Context;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.hjq.image.ImageLoader;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/11/05
 *    desc   : 可进行拷贝的副本
 */
public final class DAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;
    RecyclerView recyclerView;

    public DAdapter(Context context) {
        super(R.layout.item_fragment_d);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView img=helper.getView(R.id.img);
        ImageLoader.with(context).load(R.mipmap.test_1).circle().into(img);
    }
}