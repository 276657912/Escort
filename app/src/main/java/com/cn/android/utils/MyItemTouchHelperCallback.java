package com.cn.android.utils;

import android.app.Service;
import android.graphics.Canvas;
import android.os.Vibrator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.cn.android.widget.ItemTouchMoveListener;


/**
 * @author Created by stone
 * @since 2019/6/17
 */
public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchMoveListener moveListener;

    public MyItemTouchHelperCallback(ItemTouchMoveListener moveListener) {
        this.moveListener = moveListener;
    }

    /**
     * Callback回调监听时先调用的，用来判断当前是什么动作，比如判断方向
     * 作用：哪个方向的拖动
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        int dragFlags;
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
            //网格布局管理器允许上下左右拖动
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            //其他布局管理器允许上下拖动
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return makeMovementFlags(dragFlags, 0);
    }

    /**
     * 是否打开长按拖拽效果
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }


    //当上下移动的时候回调的方法
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder srcHolder, @NonNull RecyclerView.ViewHolder targetHolder) {
        // 在拖拽过程中不断地调用adapter.notifyItemMoved(from,to);
        if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) {
            return false;
        }
        //在拖拽的过程中不断调用adapter.notifyItemMoved(from,to);
        return moveListener.onItemMove(srcHolder.getAdapterPosition(), targetHolder.getAdapterPosition());
    }

    //侧滑的时候回调的方法
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int direction) {
        //监听侧滑，1.删除数据 2.调用adapter.notifyItemRemove(position);
        moveListener.onItemRemove(holder.getAdapterPosition());

    }

    //设置滑动item的背景
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //判断选中状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            //viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.default_bg));
            //获取系统震动服务//震动50毫秒
            Vibrator vib = (Vibrator) viewHolder.itemView.getContext().getSystemService(Service.VIBRATOR_SERVICE);
            vib.vibrate(50);
        }
        super.onSelectedChanged(viewHolder, actionState);

    }

    //清除滑动item的背景
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        // 恢复
        //viewHolder.itemView.setBackgroundColor(Color.WHITE);

        //防止出现复用问题 而导致条目不显示 方式一
        viewHolder.itemView.setAlpha(1);//1-0
        //设置滑出大小
//            viewHolder.itemView.setScaleX(1);
//            viewHolder.itemView.setScaleY(1);
        super.clearView(recyclerView, viewHolder);
    }

    //设置滑动时item的背景透明度
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //dX:水平方向移动的增量(负：往左；正：往右) 0-view.getWidth()
        float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            //透明度动画
            viewHolder.itemView.setAlpha(alpha);//1-0
            //设置滑出大小
//            viewHolder.itemView.setScaleX(alpha);
//            viewHolder.itemView.setScaleY(alpha);
        }
//        //防止出现复用问题 而导致条目不显示 方式二
//        if(alpha==0){
//            viewHolder.itemView.setAlpha(1);//1-0
//            //设置滑出大小
////            viewHolder.itemView.setScaleX(1);
////            viewHolder.itemView.setScaleY(1);
//        }
        //此super方法自动处理setTranslationX
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

    }
}