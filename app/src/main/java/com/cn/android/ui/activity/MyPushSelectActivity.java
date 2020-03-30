package com.cn.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.tencent.liteav.demo.videorecord.TCVideoSettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPushSelectActivity extends MyActivity {
    @BindView(R.id.push_view)
    ImageView pushView;
    @BindView(R.id.push_video)
    TextView pushVideo;
    @BindView(R.id.push_escort)
    TextView pushEscort;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_push;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.push_video, R.id.push_escort})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.push_video:
                Intent intent=new Intent(this, TCVideoSettingActivity.class);
                startActivity(intent);

                break;
            case R.id.push_escort:
                startActivity(MyPushEscortActivity.class);
                break;
        }
    }
}
