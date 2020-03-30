package com.cn.android.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.TimePickerView;
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
import com.cn.android.ui.adapter.ImgAdapter;
import com.cn.android.ui.dialog.MenuDialog;
import com.cn.android.utils.MyItemTouchHelperCallback;
import com.cn.android.utils.SPUtils;
import com.cn.android.utils.TimeUtils;
import com.hjq.bar.TitleBar;
import com.hjq.dialog.MessageDialog;
import com.hjq.dialog.base.BaseDialog;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MyPushEscortActivity extends MyActivity implements FileOperationView, PublicInterfaceView, BaseQuickAdapter.OnItemChildClickListener, AddImgAdapter.onItemMoveListener {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.escort_recycle)
    RecyclerView escortRecycle;
    @BindView(R.id.edit_push_title)
    EditText editPushTitle;
    @BindView(R.id.edit_push_content)
    EditText editPushContent;
    @BindView(R.id.adr_tv)
    TextView adrTv;
    @BindView(R.id.edit_push_adr)
    EditText editPushAdr;
    @BindView(R.id.out_time_tv)
    TextView outTimeTv;
    @BindView(R.id.edit_push_out_time)
    EditText editPushOutTime;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.edit_push_time)
    EditText editPushTime;
    @BindView(R.id.obj_tv)
    TextView objTv;
    @BindView(R.id.edit_push_obj)
    EditText editPushObj;
    @BindView(R.id.save_draft)
    TextView saveDraft;
    @BindView(R.id.push)
    TextView push;
    private AddImgAdapter mAdapter;
    String city;
    public List<LocalMedia> uriList = new ArrayList<>();
    public List<String> vpList = new ArrayList<>();
    public ArrayList<String> imgList = new ArrayList<>();
    private int mtype;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_push_escort;
    }

    @Override
    public void onLeftClick(View v) {
        new MenuDialog.Builder(this)
                .setCancelable(false)
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .setList("存入草稿", "放弃发布")
                .setListener(new MenuDialog.OnListener() {
                    @Override
                    public void onSelected(BaseDialog dialog, int position, Object object) {
                        switch (position) {
                            case 0:
                                new MessageDialog.Builder(getActivity()).setListener(new MessageDialog.OnListener() {
                                    @Override
                                    public void onConfirm(Dialog dialog) {
                                        if (isCheck()) {
                                            mtype = 2;
                                            getdata();
                                        }
                                    }

                                    @Override
                                    public void onCancel(Dialog dialog) {

                                    }
                                })
                                        .setMessage("确定保存到草稿箱吗？")
                                        .setConfirm("确定")
                                        .setCancel("取消")
                                        .show();
                                break;
                            case 1:
                                finish();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                    }
                })
                .show();
    }

    @Override
    protected void initView() {
        city = SPUtils.getString("city", "西安市");
//        adr.setText(city);
        editPushTime.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        editPushTime.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(".") && dest.toString().length() == 0) {
                    return "0.";
                }

                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int mlength = dest.toString().substring(index).length();
                    if (mlength == 3) {
                        return "";
                    }
                }
                return null;
            }
        }});
        filePresenetr = new FileOperationPresenetr(this);
        presenetr = new PublicInterfaceePresenetr(this);
        escortRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        if (imgList.size() < 9) {
            imgList.add("-1");
        }
        mAdapter = new AddImgAdapter(imgList, this);
        escortRecycle.addItemDecoration(new GridSpacingItemDecoration(3, 10, false));
        escortRecycle.setAdapter(mAdapter);

        // 条目触目帮助类
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(escortRecycle);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == uriList.size()) {
                    choosePicture();
                } else {
                    ImageActivity.start(MyPushEscortActivity.this, imgList, position);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            if (vpList.size() > 0) {
                vpList.clear();
            }
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
        filePresenetr.uploadSingleFileRequest(MyPushEscortActivity.this, "file", new File(uri_path), ServerUrl.upload, 10000);
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
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.save_draft, R.id.push, R.id.edit_push_out_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_push_out_time:
                getdata(editPushOutTime);
                break;
            case R.id.save_draft:
                if (isCheck()) {
                    mtype = 2;
                    getdata();
                }
                break;
            case R.id.push:
                if (isCheck()) {
                    mtype = 1;
                    getdata();
                }
                break;
        }
    }

    public boolean isCheck() {
        if (TextUtils.isEmpty(editPushAdr.getText().toString().trim())) {
            ToastUtils.show("请输入目的地");
            return false;
        }
        if (TextUtils.isEmpty(editPushContent.getText().toString().trim())) {
            ToastUtils.show("请输入伴游内容");
            return false;
        }
        if (TextUtils.isEmpty(editPushOutTime.getText().toString().trim())) {
            ToastUtils.show("请输入出行时间");
            return false;
        }
        if (TextUtils.isEmpty(editPushTime.getText().toString().trim())) {
            ToastUtils.show("请输入行程时间");
            return false;
        }
        if (TextUtils.isEmpty(editPushObj.getText().toString().trim())) {
            ToastUtils.show("请输入对象");
            return false;
        }

        return true;
    }

    public void getdata() {

        showLoading();
        presenetr.getPostTokenRequest(this, ServerUrl.addEscort, Constant.addEscort);
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

    @Override
    public void FileOperationSuccess(Object data, int tag) {
        showComplete();
        switch (tag) {
            case 10000:
                Log.e("sss", vpList.size() + "");
                vpList.add((String) data);
                ;
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

    }

    public void getdata(TextView view) {
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(new SimpleDateFormat(TimeUtils.FORMAT).format(date));
            }
        }).setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("请选择")//标题
                .setTitleBgColor(R.color.white)//标题背景颜色 Night mode
                .setBgColor(R.color.line_color)//滚轮背景颜色 Night mode
//                .setBackgroundId(R.drawable.time_selector)
                .setTextColorOut(R.color.black)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();

    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> map = new HashMap<>();
        switch (tag) {
            case Constant.addEscort:
                map.put("userid", userBean().getId());
                map.put("img_urls", getStr());
                map.put("title", editPushTitle.getText().toString().trim());
                map.put("content", editPushContent.getText().toString().trim());
                map.put("to_address", editPushAdr.getText().toString().trim());
                map.put("out_time", editPushOutTime.getText().toString().trim());
                map.put("trip_time", editPushTime.getText().toString().trim());//行程时间
                map.put("objects", editPushObj.getText().toString().trim());
                map.put("type", mtype);//1已发布 2草稿
                return map;
        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        switch (tag) {
            case Constant.addEscort:
                if (mtype == 1)
                    ToastUtils.show("发布成功");
                else
                    ToastUtils.show("保存成功");
                finish();
                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        Log.e("sss", error);
    }

    @Override
    public void onItemMove(int fromPos, int toPos) {
        //1.数据交换 2.刷新
        Collections.swap(uriList, fromPos, toPos);
    }

    public String getStr() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < vpList.size(); i++) {

            if (i == vpList.size() - 1) {
                sb.append(vpList.get(i));
            } else {
                sb.append(vpList.get(i) + ",");
            }

        }
        return sb.toString();
    }

}
