package com.cn.android.ui.fragment;


import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.common.MyLazyFragment;
import com.cn.android.ui.activity.AddressActivity;
import com.cn.android.ui.activity.BuyVIPActivity;
import com.cn.android.ui.activity.CopyActivity;
import com.cn.android.ui.activity.HomeActivity;
import com.cn.android.ui.activity.HomePageActivity;
import com.cn.android.ui.activity.MyDataActivity;
import com.cn.android.ui.activity.MyDataPublishActivity;
import com.cn.android.ui.adapter.AMenu1Adapter;
import com.cn.android.ui.adapter.AMenu2Adapter;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.dialog.base.BaseDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class FragmentA extends MyLazyFragment<CopyActivity> {

    @BindView(R.id.title)
    FrameLayout title;
    @BindView(R.id.adr)
    TextView adr;
    @BindView(R.id.menu_1)
    TextView menu1;
    @BindView(R.id.menu_2)
    TextView menu2;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.choice1)
    TextView choice1;
    @BindView(R.id.choice2)
    TextView choice2;
    @BindView(R.id.choice3)
    TextView choice3;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.linear_choice)
    LinearLayout linearChoice;
    private AMenu1Adapter menu1Adapter;
    private AMenu2Adapter menu2Adapter;

    public static FragmentA newInstance() {
        return new FragmentA();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_a;
    }

    @Override
    protected void initView() {
        ((HomeActivity) getActivity()).showGif(true);
        ImmersionBar.setTitleBar(getAttachActivity(), title);
        choseType(1);
    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        menu1Adapter = new AMenu1Adapter(getActivity());
        menu2Adapter = new AMenu2Adapter(getActivity());
        recyclerView.setAdapter(menu1Adapter);
        List<String> data = new ArrayList<>();
        data.add(new String());
        data.add(new String());
        data.add(new String());
        menu1Adapter.replaceData(data);
        menu2Adapter.replaceData(data);
        menu1Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.to_homepage:
                        startActivity(HomePageActivity.class);
                        break;
                    case R.id.to_buy_vip:
                        showVIPDialog();
                        break;
                }
            }
        });
    }

    private void showVIPDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_to_buy_vip, null);
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity()).setContentView(view);
        builder.setOnClickListener(R.id.close, new BaseDialog.OnClickListener() {
            @Override
            public void onClick(BaseDialog dialog, View view) {
                dialog.dismiss();
            }
        });
        builder.setOnClickListener(R.id.to_buy_vip, new BaseDialog.OnClickListener() {
            @Override
            public void onClick(BaseDialog dialog, View view) {
                dialog.dismiss();
                startActivity(BuyVIPActivity.class);
            }
        });
        builder.show();
    }

    @OnClick({R.id.adr, R.id.menu_1, R.id.menu_2, R.id.add, R.id.choice1, R.id.choice2, R.id.choice3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.adr:
                startActivity(AddressActivity.class);
                break;
            case R.id.menu_1:
                menu1.setBackgroundResource(R.drawable.bg_radius_black);
                menu2.setBackground(null);
                add.setVisibility(View.GONE);
                linearChoice.setVisibility(View.VISIBLE);
                menu1.setTextColor(Color.parseColor("#FFFFFF"));
                menu2.setTextColor(Color.parseColor("#000000"));
                recyclerView.setAdapter(menu1Adapter);
                break;
            case R.id.menu_2:
                menu1.setBackground(null);
                menu2.setBackgroundResource(R.drawable.bg_radius_black);
                add.setVisibility(View.VISIBLE);
                linearChoice.setVisibility(View.GONE);
                menu1.setTextColor(Color.parseColor("#000000"));
                menu2.setTextColor(Color.parseColor("#FFFFFF"));
                recyclerView.setAdapter(menu2Adapter);
                break;
            case R.id.add:
                showAddDialog();
                break;
            case R.id.choice1:
                choseType(1);
                break;
            case R.id.choice2:
                choseType(2);
                break;
            case R.id.choice3:
                choseType(3);
                break;
        }
    }

    private void showAddDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_show_add, null);
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity()).setContentView(view);
        builder.setWidth(getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth());
        builder.setHeight(getActivity().getWindow().getWindowManager().getDefaultDisplay().getHeight());
        builder.setOnClickListener(R.id.add_1, new BaseDialog.OnClickListener() {
            @Override
            public void onClick(BaseDialog dialog, View view) {
                dialog.dismiss();
                startActivity(MyDataActivity.class);
            }
        });
        builder.setOnClickListener(R.id.add_2, new BaseDialog.OnClickListener() {
            @Override
            public void onClick(BaseDialog dialog, View view) {
                dialog.dismiss();
                startActivity(MyDataPublishActivity.class);
            }
        });
        builder.show();
    }

    private void choseType(int i) {
        choice1.setBackground(null);
        choice2.setBackground(null);
        choice3.setBackground(null);
        choice1.setTextColor(Color.parseColor("#666666"));
        choice2.setTextColor(Color.parseColor("#666666"));
        choice3.setTextColor(Color.parseColor("#666666"));
        switch (i) {
            case 1:
                choice1.setBackgroundResource(R.drawable.bg_stroke_orange);
                choice1.setTextColor(Color.parseColor("#FF7E00"));
                choice1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_sex2, null), null, null, null);
                choice2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_sex_m1, null), null, null, null);
                choice3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_sex_w2, null), null, null, null);
                break;
            case 2:
                choice2.setBackgroundResource(R.drawable.bg_stroke_orange);
                choice2.setTextColor(Color.parseColor("#FF7E00"));
                choice2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_sex_m2, null), null, null, null);
                choice1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_sex1, null), null, null, null);
                choice3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_sex_w1, null), null, null, null);
                break;
            case 3:
                choice3.setBackgroundResource(R.drawable.bg_stroke_orange);
                choice3.setTextColor(Color.parseColor("#FF7E00"));
                choice3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_sex_w2, null), null, null, null);
                choice1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_sex1, null), null, null, null);
                choice2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_sex_m1, null), null, null, null);

                break;
        }
    }
}