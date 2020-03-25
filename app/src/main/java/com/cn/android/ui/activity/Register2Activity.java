package com.cn.android.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.dialog.DateDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 注册界面
 */
public final class Register2Activity extends MyActivity {


    @BindView(R.id.btn_register_commit)
    AppCompatTextView btnRegisterCommit;
    @BindView(R.id.woman)
    TextView woman;
    @BindView(R.id.man)
    TextView man;
    @BindView(R.id.birthday)
    LinearLayout mBirthday;
    @BindView(R.id.year)
    AppCompatTextView mYear;
    @BindView(R.id.mouh)
    AppCompatTextView mMouh;
    @BindView(R.id.day)
    AppCompatTextView mDay;
    private String phone;
    private String sex="女";
    private String birthday="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register2;
    }

    @Override
    protected void initView() {
        phone = getIntent().getStringExtra("phone");
        birthday="1970年 "+"1月 "+"1日";
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
        startActivity(new Intent(this,Register3Activity.class)
                .putExtra("phone",phone)
                .putExtra("sex",sex)
                .putExtra("birthday",birthday));
        this.finish();
    }


    @OnClick({R.id.woman, R.id.man, R.id.birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.woman:
                sex="女";
                setTopDrawable(woman,R.drawable.icon_man);
                setTopDrawable(man,R.drawable.icon_woman);
                break;
            case R.id.man:
                sex="男";
                setTopDrawable(woman,R.drawable.icon_man1);
                setTopDrawable(man,R.drawable.icon_woman1);
                break;
            case R.id.birthday:
                new DateDialog.Builder(this).setListener(new DateDialog.OnListener() {
                    @Override
                    public void onSelected(Dialog dialog, int year, int month, int day) {
                        mYear.setText(year+"");
                        mMouh.setText(month+"");
                        mDay.setText(day+"");
                        birthday=year+"年 "+month+"月 "+day+"日";
                        birthday=year+"年 "+month+"月 "+day+"日";
                    }

                    @Override
                    public void onCancel(Dialog dialog) {

                    }
                }).show();
                break;
        }
    }

    private void setTopDrawable(TextView view,int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置边界
        // param 左上右下
        view.setCompoundDrawables(null,drawable,null,null);
    }
}