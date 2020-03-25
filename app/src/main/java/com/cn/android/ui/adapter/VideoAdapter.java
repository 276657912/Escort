package com.cn.android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.bean.SlideBean;
import com.hjq.image.ImageLoader;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private Context context;
    private List<SlideBean> mList;

    public VideoAdapter(Context context, List<SlideBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_slide, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SlideBean bean = mList.get(position);
        holder.imgBg.setImageResource(bean.getItemBg());
        ImageLoader.with(context).load(R.mipmap.test_1).circle().into(holder.userIcon);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBg;
        ImageView userIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            imgBg = itemView.findViewById(R.id.img_bg);
            userIcon = itemView.findViewById(R.id.head_image);
        }
    }
}