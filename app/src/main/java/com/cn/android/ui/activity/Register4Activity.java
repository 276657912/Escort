package com.cn.android.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.cn.android.R;
import com.cn.android.bean.UserBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.GsonUtils;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.FileOperationPresenetr;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.FileOperationView;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.utils.SPUtils;
import com.gyf.immersionbar.ImmersionBar;
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
 * desc   : 注册界面
 */
public final class Register4Activity extends MyActivity implements FileOperationView, PublicInterfaceView {


    @BindView(R.id.btn_register_commit)
    AppCompatTextView btnRegisterCommit;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    @BindView(R.id.head_img)
    ImageView headImg;
    private String userphone;
    private String sex = "女";
    private String birthday = "";
    private String nickname = "";
    private String password = "";
    private String headimg = "";
    private String city = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register4;
    }

    @Override
    protected void initView() {
        userphone = getIntent().getStringExtra("phone");
        sex = getIntent().getStringExtra("sex");
        birthday = getIntent().getStringExtra("birthday");
        nickname = getIntent().getStringExtra("nickname");
        password = getIntent().getStringExtra("password");
        presenetr = new PublicInterfaceePresenetr(this);
        filePresenetr = new FileOperationPresenetr(this);
        mLocationClient = new AMapLocationClient(this);
        mLocationClient.setLocationListener(mAMapLocationListener);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setNeedAddress(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected ImmersionBar statusBarConfig() {
        // 不要把整个布局顶上去
        return super.statusBarConfig().keyboardEnable(true);
    }

    AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    city = amapLocation.getCity();

                    Log.e("123", "onLocationChanged = " + amapLocation.getCity());
                } else {
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    @OnClick({R.id.head_img, R.id.btn_register_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_img:
                PhotoActivity.start(this, new PhotoActivity.OnPhotoSelectListener() {
                    @Override
                    public void onSelect(List<String> data) {
                        showLoading();
                        filePresenetr.uploadSingleFileRequest(Register4Activity.this, "file", new File(data.get(0)), ServerUrl.upload, 10086);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
            case R.id.btn_register_commit:
                showLoading();
                presenetr.getPostRequest(this, ServerUrl.register, Constant.register);
                break;
        }
    }

    @Override
    public void FileOperationSuccess(Object data, int tag) {
        switch (tag) {
            case 10086:
                showComplete();
                headimg = (String) data;
                ImageLoader.with(Register4Activity.this).load(headimg).circle().into(headImg);
                break;
        }
    }

    @Override
    public void FileOperationProgress(float progress, int tag) {

    }

    @Override
    public void FileOperationError(String error, int tag) {
        toast(error);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        switch (tag) {
            case Constant.register:
                paramsMap.put("userphone", userphone);
                paramsMap.put("sex", sex);
                paramsMap.put("birthday", birthday);
                paramsMap.put("nickname", nickname);
                paramsMap.put("password", password);
                paramsMap.put("headimg", headimg);
                paramsMap.put("city", city);
                return paramsMap;
        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        switch (tag) {
            case Constant.register:
                showComplete();
                UserBean bean = GsonUtils.getPerson(data, UserBean.class);
                SPUtils.putString("token", bean.getToken());
                saveUserBean(bean);
                toast("完成");
                SPUtils.putString("isRegister", "isRegister");
                this.finish();
                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        toast(error);
    }
}