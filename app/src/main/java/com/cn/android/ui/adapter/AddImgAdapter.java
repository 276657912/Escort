package com.cn.android.ui.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.android.R;
import com.cn.android.widget.ItemTouchMoveListener;
import com.hjq.image.ImageLoader;
import com.shehuan.niv.NiceImageView;


import java.util.Collections;
import java.util.List;

/**
 * @author Created by stone
 * @since 2019/6/17
 */
public class AddImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements ItemTouchMoveListener {

    private final onItemMoveListener moveListener;

    public AddImgAdapter(@Nullable List<String> data, onItemMoveListener moveListener) {
        super(R.layout.add_img_item, data);
        this.moveListener = moveListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        NiceImageView img = helper.getView(R.id.item_img);
        if (item.equals("-1")) {
            img.setImageResource(R.mipmap.add_img_gray_img);
            helper.setGone(R.id.item_del_iv,false);
        } else {
            ImageLoader.with(mContext).load(item).into(img);
            helper.setGone(R.id.item_del_iv,true);
        }
        helper.addOnClickListener(R.id.item_del_iv);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (mData.get(fromPosition).equals("-1") || mData.get(toPosition).equals("-1")) {
            return false;
        }
        //1.数据交换 2.刷新
        Collections.swap(mData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        moveListener.onItemMove(fromPosition, toPosition);

        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        return true;
    }

    public interface onItemMoveListener {
        void onItemMove(int fromPos, int toPos);
    }
}
