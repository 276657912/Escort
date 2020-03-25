package com.cn.android.ui.adapter;


import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;

import java.util.ArrayList;
import java.util.List;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/11/05
 *    desc   : 可进行拷贝的副本
 */
public final class LoveMeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;
    RecyclerView recyclerView;

    public LoveMeAdapter(Context context) {
        super(R.layout.item_love_me);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        recyclerView=helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        LoveMeImgAdapter adapter=new LoveMeImgAdapter(context);
        recyclerView.setAdapter(adapter);
        List<String> data=new ArrayList<>();
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        data.add(new String());
        adapter.replaceData(data);
        helper.addOnClickListener(R.id.all);
    }
}