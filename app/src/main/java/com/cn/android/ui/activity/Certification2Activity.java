package com.cn.android.ui.activity;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.FileOperationPresenetr;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.FileOperationView;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.hjq.dialog.MessageDialog;
import com.hjq.image.ImageLoader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class Certification2Activity extends MyActivity implements FileOperationView, PublicInterfaceView {
    @BindView(R.id.shenfen_1)
    ImageView shenfen1;
    @BindView(R.id.shenfen_2)
    ImageView shenfen2;
    @BindView(R.id.button)
    Button button;
    private String idcard_front;
    private String idcard_back;
    private String face_img;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_certification2;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
        filePresenetr = new FileOperationPresenetr(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.shenfen_1, R.id.shenfen_2, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shenfen_1:
                PhotoActivity.start(this, new PhotoActivity.OnPhotoSelectListener() {
                    @Override
                    public void onSelect(List<String> data) {
                        showLoading();
                        filePresenetr.uploadSingleFileRequest(Certification2Activity.this, "file", new File(data.get(0)), ServerUrl.upload, 10087);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
            case R.id.shenfen_2:
                PhotoActivity.start(this, new PhotoActivity.OnPhotoSelectListener() {
                    @Override
                    public void onSelect(List<String> data) {
                        showLoading();
                        filePresenetr.uploadSingleFileRequest(Certification2Activity.this, "file", new File(data.get(0)), ServerUrl.upload, 10088);

                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
            case R.id.button:
                new MessageDialog.Builder(this)
                        .setTitle("照片认证")
                        .setMessage("点击拍摄本人无遮挡露脸照")
                        .setConfirm("确定")
                        .setCancel("取消")
                        .setListener(new MessageDialog.OnListener() {
                            @Override
                            public void onConfirm(Dialog dialog) {
                                PhotoActivity.start(Certification2Activity.this, new PhotoActivity.OnPhotoSelectListener() {
                                    @Override
                                    public void onSelect(List<String> data) {
                                        showLoading();
                                        filePresenetr.uploadSingleFileRequest(Certification2Activity.this, "file", new File(data.get(0)), ServerUrl.upload, 10089);

                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                            }

                            @Override
                            public void onCancel(Dialog dialog) {

                            }
                        })
                        .show();
                break;
        }
    }

    @Override
    public void FileOperationSuccess(Object data, int tag) {
        showComplete();
        switch (tag) {
            case 10087:
                idcard_front = (String) data;
                ImageLoader.with(Certification2Activity.this).load(idcard_front).circle(10).into(shenfen1);
                break;
            case 10088:
                idcard_back = (String) data;
                ImageLoader.with(Certification2Activity.this).load(idcard_back).circle(10).into(shenfen2);
                break;
            case 10089:
                face_img = (String) data;
                presenetr.getPostTokenRequest(this, ServerUrl.realAppUser, Constant.realAppUser);
                break;
        }
    }

    @Override
    public void FileOperationProgress(float progress, int tag) {

    }

    @Override
    public void FileOperationError(String error, int tag) {

    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userid", userBean().getId());
        paramsMap.put("idcard_front", idcard_front);
        paramsMap.put("idcard_back", idcard_back);
        paramsMap.put("face_img", face_img);
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        toast(msg);
        if (code == 200) {
            startActivity(Certification3Activity.class);
            this.finish();
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }
}