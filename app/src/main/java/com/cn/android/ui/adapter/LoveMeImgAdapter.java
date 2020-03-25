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
public final class LoveMeImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;
    RecyclerView recyclerView;

    public LoveMeImgAdapter(Context context) {
        super(R.layout.item_love_me_img);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
    }
}