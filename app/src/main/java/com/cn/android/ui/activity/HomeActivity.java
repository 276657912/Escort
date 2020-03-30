package com.cn.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.cn.android.R;
import com.cn.android.bean.UserBean;
import com.cn.android.common.MyActivity;
import com.cn.android.helper.ActivityStackManager;
import com.cn.android.helper.DoubleClickHelper;
import com.cn.android.network.Constant;
import com.cn.android.network.GsonUtils;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.ui.fragment.FragmentA;
import com.cn.android.ui.fragment.FragmentB;
import com.cn.android.ui.fragment.FragmentC;
import com.cn.android.ui.fragment.FragmentD;
import com.cn.android.ui.fragment.FragmentE;
import com.cn.android.utils.SPUtils;
import com.hjq.image.ImageLoader;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 主页界面
 */
public final class HomeActivity extends MyActivity implements PublicInterfaceView {

    @BindView(R.id.home_context)
    FrameLayout homeContext;
    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.tab_1)
    LinearLayout tab1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.tab_2)
    LinearLayout tab2;
    @BindView(R.id.tab_3)
    LinearLayout tab3;
    @BindView(R.id.img_4)
    ImageView img4;
    @BindView(R.id.tab_4)
    LinearLayout tab4;
    @BindView(R.id.img_5)
    ImageView img5;
    @BindView(R.id.tab_5)
    LinearLayout tab5;
    @BindView(R.id.tab_3_2)
    ImageView tab32;
    @BindView(R.id.gif)
    ImageView gif;

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private FragmentD fragmentD;
    private FragmentE fragmentE;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        onViewClicked(tab1);
        presenetr = new PublicInterfaceePresenetr(this);
    }

    @Override
    protected void initData() {
        ImageLoader.with(this).load(R.drawable.gif).gif().into(gif);
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyPushSelectActivity.class);
            }
        });
    }

    public void showGif(boolean isShow) {
        if (isShow) {
            gif.setVisibility(View.VISIBLE);
        } else {
            gif.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            //移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            postDelayed(new Runnable() {

                @Override
                public void run() {
                    ActivityStackManager.getInstance().finishAllActivities();
                }
            }, 300);
        } else {
            toast(R.string.home_exit_hint);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.tab_1, R.id.tab_2, R.id.tab_3, R.id.tab_4, R.id.tab_5})
    public void onViewClicked(View view) {
        img1.setImageResource(R.drawable.tab_icon_1_1);
        img2.setImageResource(R.drawable.tab_icon_2_1);
        img4.setImageResource(R.drawable.tab_icon_4_1);
        img5.setImageResource(R.drawable.tab_icon_5_1);
        tab32.setVisibility(View.GONE);
        switch (view.getId()) {

            case R.id.tab_1:
                img1.setImageResource(R.drawable.tab_icon_1_2);
                changeContent(1);
                break;
            case R.id.tab_2:
                img2.setImageResource(R.drawable.tab_icon_2_2);
                changeContent(2);
                break;
            case R.id.tab_3:
                toLogin();
                tab32.setVisibility(View.VISIBLE);
                changeContent(3);
                break;
            case R.id.tab_4:
                toLogin();
                img4.setImageResource(R.drawable.tab_icon_4_2);
                changeContent(4);
                break;
            case R.id.tab_5:
                toLogin();
                img5.setImageResource(R.drawable.tab_icon_5_2);
                changeContent(5);
                break;
        }
    }

    private void toLogin() {
        if (!isUserLogin()) {
            startActivity(RegisterActivity.class);
        }
    }

    /**
     * changeContent(根据菜单编码切换内容页)
     *
     * @param @param menuCode 设定文件
     * @return void 返回类型
     * @throws
     * @Title: changeContent
     * @Description: TODO
     */
    private void changeContent(int menuCode) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        hideFragments(transaction);
        if (menuCode == 1) {
            if (fragmentA == null) {
                fragmentA = new FragmentA();
                transaction.add(R.id.home_context, fragmentA);
            } else {
                transaction.show(fragmentA);
            }
        } else if (menuCode == 2) {
            if (fragmentB == null) {
                fragmentB = new FragmentB();
                transaction.add(R.id.home_context, fragmentB);
            } else {
                transaction.show(fragmentB);
            }
        } else if (menuCode == 3) {
            if (fragmentC == null) {
                fragmentC = new FragmentC();
                transaction.add(R.id.home_context, fragmentC);
            } else {
                transaction.show(fragmentC);
            }
        } else if (menuCode == 4) {
            if (fragmentD == null) {
                fragmentD = new FragmentD();
                transaction.add(R.id.home_context, fragmentD);
            } else {
                transaction.show(fragmentD);
            }
        } else if (menuCode == 5) {
            if (fragmentE == null) {
                fragmentE = new FragmentE();
                transaction.add(R.id.home_context, fragmentE);
            } else {
                transaction.show(fragmentE);
            }
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (fragmentA != null) {
            transaction.hide(fragmentA);
        }
        if (fragmentB != null) {
            transaction.hide(fragmentB);
        }
        if (fragmentC != null) {
            transaction.hide(fragmentC);
        }
        if (fragmentD != null) {
            transaction.hide(fragmentD);
        }
        if (fragmentE != null) {
            transaction.hide(fragmentE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isUserLogin()) {
            return;
        }
        presenetr.getPostRequest(this, ServerUrl.selectAppUserByuserid, Constant.selectAppUserByuserid);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.selectAppUserByuserid:
                paramsMap.put("userid", userBean().getId());
                return paramsMap;
        }
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        if (code == 200) {
            switch (tag) {
                case Constant.selectAppUserByuserid:
                    UserBean bean = GsonUtils.getPerson(data, UserBean.class);
                    SPUtils.putString("token", bean.getToken());
                    saveUserBean(bean);
                    if (null != fragmentE) {
                        fragmentE.upData();
                    }
                    break;
            }
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }
}