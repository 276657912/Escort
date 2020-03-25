package com.cn.android.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.helper.ActivityStackManager;
import com.cn.android.ui.dialog.MenuDialog;
import com.cn.android.utils.SPUtils;
import com.hjq.dialog.MessageDialog;
import com.hjq.dialog.base.BaseDialog;
import com.hjq.widget.layout.SettingBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/03/01
 * desc   : 设置界面
 */
public final class SettingActivity extends MyActivity {

    @BindView(R.id.menu1)
    SettingBar menu1;
    @BindView(R.id.menu2)
    SettingBar menu2;
    @BindView(R.id.menu3)
    SettingBar menu3;
    @BindView(R.id.menu4)
    SettingBar menu4;
    @BindView(R.id.menu5)
    SettingBar menu5;
    @BindView(R.id.menu6)
    SettingBar menu6;
    @BindView(R.id.menu7)
    SettingBar menu7;
    @BindView(R.id.menu8)
    SettingBar menu8;
    @BindView(R.id.menu9)
    SettingBar menu9;
    @BindView(R.id.menu10)
    SettingBar menu10;
    @BindView(R.id.menu11)
    SettingBar menu11;
    @BindView(R.id.button)
    Button button;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
//        0未申请 1审核中 2通过 3不通过
            switch (userBean().getIsReal()) {
                case  0:
                    menu3.setRightText("未申请");
                    break;
                case  1:
                    menu3.setRightText("审核中");
                    break;
                case  2:
                    menu3.setRightText("通过");
                    break;
                case  3:
                    menu3.setRightText("不通过");
                    break;
            }
    }

    @OnClick({R.id.menu1, R.id.menu2, R.id.menu3, R.id.menu4, R.id.menu5, R.id.menu6, R.id.menu7, R.id.menu8, R.id.menu9, R.id.menu10, R.id.menu11, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu1://清空缓存
                break;
            case R.id.menu2://推送通知管理
                break;
            case R.id.menu3://实名认证
                if(userBean().getIsReal()==0){
                    startActivity(CertificationActivity.class);
                }
                break;
            case R.id.menu4://修改密码
                startActivity(ChangePwdActivity.class);
                break;
            case R.id.menu5://修改绑定手机
                startActivity(BindPhone1Activity.class);
                break;
            case R.id.menu6://账号注销
                startActivity(Logout1Activity.class);
                break;
            case R.id.menu7://意见与反馈
                break;
            case R.id.menu8://关于我们
                startActivity(AboutActivity.class);
                break;
            case R.id.menu9://用户协议
                break;
            case R.id.menu10://隐私政策
                break;
            case R.id.menu11://信息安全投诉举报
                break;
            case R.id.button:
                List<String> str=new ArrayList<>();
                str.add("确定要退出登录吗？");
                str.add("确定");
                new MenuDialog.Builder(this)
                        .setList(str)
                        .setListener(new MenuDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int position, Object o) {
                                if(position==1){
                                    SPUtils.removeAll();
                                    SettingActivity.this.finish();
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
        }
    }
}