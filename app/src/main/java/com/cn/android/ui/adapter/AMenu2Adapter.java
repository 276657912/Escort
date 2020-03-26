package com.cn.android.ui.adapter;


import android.content.Context;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.cn.android.bean.BarRecruitBean;
import com.hjq.image.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/11/05
 * desc   : 可进行拷贝的副本
 */
public final class AMenu2Adapter extends BaseQuickAdapter<BarRecruitBean, BaseViewHolder> {

    private Context context;
    RecyclerView recyclerView;
    private String[] list;
    List<String> data;

    public AMenu2Adapter(Context context) {
        super(R.layout.item_menu2);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BarRecruitBean item) {

        helper.setText(R.id.time, String.format("发布时间：%s", item.getCtime()));
        helper.setText(R.id.content, item.getContent());
        helper.setText(R.id.address, item.getCity());
        helper.setText(R.id.user_name, item.getNickname());
        ImageLoader.with(helper.itemView.getContext()).load(item.getHeadimg()).circle(10).into(helper.getView(R.id.head_img));
        helper.addOnClickListener(R.id.to_homepage);
        helper.addOnClickListener(R.id.to_buy_vip);
        if (item.getImg_urls().contains(",")) {
            list = item.getImg_urls().split(",");
            data = Arrays.asList(list);
        } else {
            data = new ArrayList<>();
            data.add(item.getImg_urls());
        }
        recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        ImgAdapter adapter = new ImgAdapter(context);
        recyclerView.setAdapter(adapter);
        adapter.replaceData(data);
    }
}