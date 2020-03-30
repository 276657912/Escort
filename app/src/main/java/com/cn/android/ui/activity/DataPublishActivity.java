package com.cn.android.ui.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.FileOperationPresenetr;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.FileOperationView;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.ui.adapter.AddImgAdapter;
import com.cn.android.utils.MyItemTouchHelperCallback;
import com.cn.android.utils.SPUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProjectonItemChildClick
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class DataPublishActivity extends MyActivity implements FileOperationView, PublicInterfaceView, BaseQuickAdapter.OnItemChildClickListener, AddImgAdapter.onItemMoveListener {


    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.adr)
    TextView adr;
    @BindView(R.id.img_recycle)
    RecyclerView imgRecycle;
    @BindView(R.id.edit_contant)
    EditText editContant;
    private AddImgAdapter mAdapter;
    String city;
    public List<LocalMedia> uriList = new ArrayList<>();
    public List<String> vpList = new ArrayList<>();
    public ArrayList<String> imgList = new ArrayList<>();
    int imglist_Index = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_publish;
    }

    @Override
    protected void initView() {
        city = SPUtils.getString("city", "西安市");
        adr.setText(city);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {

            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                finish();

            }
        });
        filePresenetr = new FileOperationPresenetr(this);
        presenetr = new PublicInterfaceePresenetr(this);
        imgRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        if (imgList.size() < 9) {
            imgList.add("-1");
        }
        mAdapter = new AddImgAdapter(imgList, this);
        imgRecycle.addItemDecoration(new GridSpacingItemDecoration(3, 10, false));
        imgRecycle.setAdapter(mAdapter);
        // 条目触目帮助类
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(imgRecycle);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == uriList.size()) {
                    choosePicture();
                } else {
                    ImageActivity.start(DataPublishActivity.this, imgList, position);
                }
            }
        });
    }

    @OnClick({R.id.push_btn})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.push_btn:
                showLoading();
                presenetr.getPostTokenRequest(this,ServerUrl.addBar,Constant.addBar);
                break;
        }
    }

    /**
     * item移动回调的监听
     *
     * @param fromPos 起始位置
     * @param toPos   终点位置
     */
    @Override
    public void onItemMove(int fromPos, int toPos) {
        //1.数据交换 2.刷新
        Collections.swap(uriList, fromPos, toPos);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_del_iv:
                uriList.remove(position);
                vpList.remove(position);
                imgList.remove(position);
                refreshData();
                if (imgList.size() < 9 && !imgList.get(imgList.size() - 1).equals("-1")) {
                    imgList.add("-1");
                }
                break;
        }
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        mAdapter.setNewData(imgList);
    }

    /**
     * 选择图片
     */
    @SuppressLint("CheckResult")
    public void choosePicture() {
        RxPermissions rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {

                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            PictureSelector.create(getActivity())
                                    .openGallery(PictureMimeType.ofImage())
                                    .theme(R.style.picture_default_style)
                                    .maxSelectNum(9)
                                    .minSelectNum(1)
                                    .selectionMode(PictureConfig.MULTIPLE)
                                    .isCamera(true)
                                    .glideOverride(480, 480)
                                    .previewEggs(true)
                                    .selectionMedia(uriList)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            uriList = PictureSelector.obtainMultipleResult(data);
            imgList.clear();
            for (LocalMedia media : uriList) {
                imgList.add(media.getPath());
                compressionImage(media.getPath());
            }
            if (imgList.size() < 8) {
                imgList.add("-1");
            }
            refreshData();
        }
    }

    /**
     *
     */
    private void compressionImage(final String uri_path) {
        showLoading();
        filePresenetr.uploadSingleFileRequest(DataPublishActivity.this, "file", new File(uri_path), ServerUrl.upload, 10000);
    }


    @Override
    protected void initData() {
    }

    @Override
    public void FileOperationSuccess(Object data, int tag) {
        showComplete();
        switch (tag) {
            case 10000:
                Log.e("sss", (String) data);
                vpList.add((String) data);
//                mAdapter.setNewData(imgList);
                break;
        }


    }

    @Override
    public void FileOperationProgress(float progress, int tag) {
        Log.e("sss", String.valueOf(progress));
    }

    @Override
    public void FileOperationError(String error, int tag) {
        toast(error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("userid", userBean().getId());
        map.put("img_urls", getStr());
        map.put("content", editContant.getText().toString().trim());
        map.put("city", city);
        return map;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        switch (tag) {
            case Constant.addBar:
                ToastUtils.show("发布成功");
                finish();
                break;
        }

    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        ToastUtils.show(error);

    }

    public String getStr() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < vpList.size(); i++) {

            if (i == vpList.size() - 1) {
                sb.append(vpList.get(i));
            }else{
                sb.append(vpList.get(i) + ",");
            }

        }
        return sb.toString();
    }


}