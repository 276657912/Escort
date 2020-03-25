package com.cn.android.ui.fragment;


import android.util.Log;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.android.R;
import com.cn.android.bean.SlideBean;
import com.cn.android.common.MyLazyFragment;
import com.cn.android.slide.ItemConfig;
import com.cn.android.slide.ItemTouchHelperCallback;
import com.cn.android.slide.OnSlideListener;
import com.cn.android.slide.SlideLayoutManager;
import com.cn.android.ui.activity.CopyActivity;
import com.cn.android.ui.activity.HomeActivity;
import com.cn.android.ui.adapter.DAdapter;
import com.cn.android.ui.adapter.VideoAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class FragmentC extends MyLazyFragment<HomeActivity> {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.title)
    TitleBar title;
    private SlideLayoutManager mSlideLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelperCallback mItemTouchHelperCallback;
    private VideoAdapter mAdapter;
    private List<SlideBean> mList = new ArrayList<>();


    public static FragmentC newInstance() {
        return new FragmentC();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_c;
    }

    @Override
    protected void initView() {
        ((HomeActivity)getActivity()).showGif(false);
        ImmersionBar.setTitleBar(getAttachActivity(), title);
        mAdapter = new VideoAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mAdapter);
        mItemTouchHelperCallback = new ItemTouchHelperCallback(mRecyclerView.getAdapter(), mList);
        mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        mSlideLayoutManager = new SlideLayoutManager(mRecyclerView, mItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setLayoutManager(mSlideLayoutManager);
    }

    @Override
    protected void initData() {
        int[] bgs = {
                R.mipmap.img_slide_1,
                R.mipmap.img_slide_2,
                R.mipmap.img_slide_3,
                R.mipmap.img_slide_4,
                R.mipmap.img_slide_5,
                R.mipmap.img_slide_6
        };
        for (int i = 0; i < 6; i++) {
            mList.add(new SlideBean(bgs[i]));
        }
        mAdapter.notifyDataSetChanged();
        mItemTouchHelperCallback.setOnSlideListener(new OnSlideListener() {
            @Override
            public void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                if (direction == ItemConfig.SLIDING_LEFT) {
                } else if (direction == ItemConfig.SLIDING_RIGHT) {
                }
            }

            @Override
            public void onSlided(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                if (direction == ItemConfig.SLIDED_LEFT) {
//                    mDislikeCount--;
                    toast("不喜欢");
                } else if (direction == ItemConfig.SLIDED_RIGHT) {
//                    mLikeCount++;
                    toast("喜欢");
                }
                int position = viewHolder.getAdapterPosition();
            }

            @Override
            public void onClear() {
                toast("没有了");
            }
        });
    }
}