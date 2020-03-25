package com.cn.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 注册界面
 */
public final class Register3Activity extends MyActivity {


    @BindView(R.id.btn_register_commit)
    AppCompatTextView btnRegisterCommit;
    @BindView(R.id.nickName)
    AppCompatEditText nickName;
    @BindView(R.id.passWord)
    AppCompatEditText passWord;
    private String phone;
    private String sex = "女";
    private String birthday = "";
    private String nickname = "";
    private String password = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register3;
    }

    @Override
    protected void initView() {
        phone = getIntent().getStringExtra("phone");
        sex = getIntent().getStringExtra("sex");
        birthday = getIntent().getStringExtra("birthday");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected ImmersionBar statusBarConfig() {
        // 不要把整个布局顶上去
        return super.statusBarConfig().keyboardEnable(true);
    }


    @OnClick(R.id.btn_register_commit)
    public void onViewClicked() {
        if(TextUtils.isEmpty(nickName.getText())){
            toast("请输入内容");
            return;
        }
        if(TextUtils.isEmpty(passWord.getText())){
            toast("请输入内容");
            return;
        }
        nickname=nickName.getText().toString();
        password=passWord.getText().toString();
        startActivity(new Intent(this, Register4Activity.class)
                .putExtra("phone", phone)
                .putExtra("sex", sex)
                .putExtra("birthday", birthday)
                .putExtra("nickname", nickname)
                .putExtra("password", password));
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}