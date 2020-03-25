package com.cn.android.ui.activity;


import android.os.Bundle;
import android.view.View;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.hjq.widget.layout.SettingBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class MyEditActivity extends MyActivity {

    @BindView(R.id.change_name)
    SettingBar changeName;
    @BindView(R.id.change_date)
    SettingBar changeDate;
    @BindView(R.id.change_autograph)
    SettingBar changeAutograph;
    @BindView(R.id.change_label)
    SettingBar changeLabel;
    @BindView(R.id.user_id)
    SettingBar userId;
    @BindView(R.id.user_constellation)
    SettingBar userConstellation;
    @BindView(R.id.age)
    SettingBar age;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        upData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        upData();
    }

    private void upData() {
        if (!isUserLogin()) {
            return;
        }
        userId.setRightText(userBean().getId());
        changeName.setRightText(userBean().getNickname());
        changeDate.setRightText(userBean().getBirthday());
        changeAutograph.setRightText(userBean().getSignature());
        changeLabel.setRightText(userBean().getLabel());
        age.setRightText(userBean().getAge());
        userConstellation.setRightText(userBean().getStar());
    }


    @OnClick({R.id.change_name, R.id.change_date, R.id.change_autograph, R.id.change_label})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_name:
                startActivity(ChangeNameActivity.class);
                break;
            case R.id.change_date:
                startActivity(BirthdayActivity.class);
                break;
            case R.id.change_autograph:
                startActivity(AutographActivity.class);
                break;
            case R.id.change_label:
                startActivity(LabelActivity.class);
                break;
        }
    }
}