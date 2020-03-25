package com.cn.android.ui.adapter;


import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/11/05
 *    desc   : 可进行拷贝的副本
 */
public final class HomePageVideoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;
    RecyclerView recyclerView;

    public HomePageVideoAdapter(Context context) {
        super(R.layout.item_home_page_video);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if(helper.getLayoutPosition()!=0){
            helper.setGone(R.id.lock,true);
        }else{
            helper.setGone(R.id.lock,false);
        }
        helper.addOnClickListener(R.id.lock);
    }
}