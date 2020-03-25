package com.cn.android.ui.fragment;


import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cn.android.R;
import com.cn.android.common.MyLazyFragment;
import com.cn.android.ui.activity.BindAccountActivity;
import com.cn.android.ui.activity.BuyVIPActivity;
import com.cn.android.ui.activity.CopyActivity;
import com.cn.android.ui.activity.FansActivity;
import com.cn.android.ui.activity.FollowActivity;
import com.cn.android.ui.activity.HomeActivity;
import com.cn.android.ui.activity.LoveMeActivity;
import com.cn.android.ui.activity.MyEditActivity;
import com.cn.android.ui.activity.MyMoneyActivity;
import com.cn.android.ui.activity.SettingActivity;
import com.cn.android.ui.activity.ShareActivity;
import com.cn.android.utils.SPUtils;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.image.ImageLoader;
import com.hjq.widget.layout.NoScrollViewPager;

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
public final class FragmentE extends MyLazyFragment<CopyActivity> {


    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.my_money)
    TextView myMoney;
    @BindView(R.id.gz)
    TextView gz;
    @BindView(R.id.fs)
    TextView fs;
    @BindView(R.id.dz)
    TextView dz;
    @BindView(R.id.open_vip)
    ImageView openVip;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    List<String> mTitle;
    List<Fragment> mFragment;
    @BindView(R.id.mytab)
    TabLayout mytab;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_qian_ming)
    TextView userQianMing;

    public static FragmentE newInstance() {
        return new FragmentE();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_e;
    }

    @Override
    protected void initView() {
        ((HomeActivity) getActivity()).showGif(true);
        ImmersionBar.setTitleBar(getAttachActivity(), title);
    }

    @Override
    protected void initData() {
        mViewPager.setNoScroll(true);//不能滑动
        mViewPager.setScrollAnim(false);//无动画
        mTitle = new ArrayList<>();
        mTitle.add("伴游");
        mTitle.add("照片");
        mTitle.add("视频");
        mFragment = new ArrayList<>();
        mFragment.add(new BYFragment());
        mFragment.add(new ZPFragment());
        mFragment.add(new SPFragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        mytab.setupWithViewPager(mViewPager);
        ImageLoader.with(getActivity()).load(R.mipmap.test_1).circle().into(headImage);
        for (int i = 0; i < mytab.getTabCount(); i++) {
            TabLayout.Tab tab = mytab.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }
        updateTabView(mytab.getTabAt(mytab.getSelectedTabPosition()), true);
        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabView(tab, true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        upData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            upData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        upData();
    }

    private void upData() {
        if (!isUserLogin()) {
            return;
        }
        ImageLoader.with(getActivity()).load(userBean().getHeadimg()).circle().into(headImage);
        userName.setText(userBean().getNickname());
        userQianMing.setText(userBean().getSignature());
        gz.setText("关注，" + userBean().getFocusNum());
        fs.setText("粉丝，" + userBean().getFansNum());
        dz.setText("喜欢我，" + userBean().getLikeNum());
        switch (userBean().getVip()) {
            case  0:
                openVip.setVisibility(View.VISIBLE);
                break;
            case  1:
                openVip.setVisibility(View.GONE);
                break;
        }
    }

    @OnClick({R.id.gz, R.id.fs, R.id.dz, R.id.my_money, R.id.open_vip, R.id.to_edit, R.id.setting, R.id.share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share:
                startActivity(ShareActivity.class);
                break;
            case R.id.gz:
                startActivity(FollowActivity.class);
                break;
            case R.id.fs:
                startActivity(FansActivity.class);
                break;
            case R.id.dz:
                startActivity(LoveMeActivity.class);
                break;
            case R.id.my_money:
                if (userBean().getIsAccount()==2) {
                    startActivity(MyMoneyActivity.class);
                } else {
                    startActivity(BindAccountActivity.class);
                }
                break;
            case R.id.setting:
                startActivity(SettingActivity.class);
                break;
            case R.id.open_vip:
                startActivity(BuyVIPActivity.class);
                break;
            case R.id.to_edit:
                startActivity(MyEditActivity.class);
                break;
        }
    }

    private View getTabView(int currentPosition) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_item_textview);
        textView.setText(mTitle.get(currentPosition));
        return view;
    }

    private void updateTabView(TabLayout.Tab tab, boolean isSelect) {

        if (isSelect) {
            //选中加粗
            TextView tabSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tabSelect.setText(tab.getText());
        } else {
            TextView tabUnSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setText(tab.getText());
        }
    }

}