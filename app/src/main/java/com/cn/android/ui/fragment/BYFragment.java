package com.cn.android.ui.fragment;


import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.android.R;
import com.cn.android.bean.EscortRecruitmentBean;
import com.cn.android.bean.MyBarBean;
import com.cn.android.bean.MyEscortBean;
import com.cn.android.common.MyLazyFragment;
import com.cn.android.network.Constant;
import com.cn.android.network.GsonUtils;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.ui.activity.CopyActivity;
import com.cn.android.ui.activity.LoginActivity;
import com.cn.android.ui.activity.MyPublishActivity;
import com.cn.android.ui.activity.RegisterActivity;
import com.cn.android.ui.adapter.AMenu1Adapter;
import com.cn.android.ui.adapter.AMenu2Adapter;
import com.cn.android.ui.adapter.FaBuAdapter;
import com.cn.android.ui.adapter.FaEscortAdapter;
import com.hjq.base.BaseRecyclerViewAdapter;
import com.hjq.dialog.MessageDialog;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class BYFragment extends MyLazyFragment<CopyActivity> implements
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, PublicInterfaceView {
    @BindView(R.id.fa_bu)
    TextView faBu;
    @BindView(R.id.cao_gao)
    TextView caoGao;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    FaEscortAdapter adapter;
    private int page = 1;
    private int type = 1;
    PublicInterfaceePresenetr presenetr;
    private List<MyEscortBean> list = new ArrayList<>();
    private String del_id;

    public static BYFragment newInstance() {
        return new BYFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_by;
    }

    @Override
    protected void initView() {
        presenetr = new PublicInterfaceePresenetr(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FaEscortAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        faBu.setBackgroundResource(R.drawable.bg_radius_white);
        caoGao.setBackground(null);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        getdata();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenetr != null)
            getdata();
    }

    public void getdata() {
        if (userBean() == null) {
            startActivity(RegisterActivity.class);
        } else {
            showLoading();
            presenetr.getPostRequest(getActivity(), ServerUrl.selectEscortList, Constant.selectEscortList);
        }

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.fa_bu, R.id.cao_gao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fa_bu:
                faBu.setBackgroundResource(R.drawable.bg_radius_white);
                caoGao.setBackground(null);
                adapter.setDel(false);
                type = 1;
                getdata();
                break;
            case R.id.cao_gao:
                caoGao.setBackgroundResource(R.drawable.bg_radius_white);
                faBu.setBackground(null);
                adapter.setDel(true);
                type = 2;
                getdata();

                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(MyPublishActivity.class);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.del:
                new MessageDialog.Builder(getActivity()).setListener(new MessageDialog.OnListener() {
                    @Override
                    public void onConfirm(Dialog dialog) {
                        del_id = list.get(position).getId();
                        showLoading();
                        presenetr.getPostTokenRequest(getActivity(), ServerUrl.deleteEscortById, Constant.deleteEscortById);
                    }

                    @Override
                    public void onCancel(Dialog dialog) {

                    }
                })
                        .setTitle("删除")
                        .setMessage("确定删除该草稿吗？")
                        .setConfirm("确定")
                        .setCancel("取消")
                        .show();
                break;
        }
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> map = new HashMap<>();
        switch (tag) {
            case Constant.selectEscortList:
                map.put("userid", userBean().getId());
                map.put("page", page);
                map.put("rows", 10);
                map.put("type", type);
                return map;
            case Constant.deleteEscortById:
                map.put("id", del_id);
                return  map;
        }
        return null;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        View view = View.inflate(getContext(), R.layout.no_data_layout, null);
        switch (tag) {
            case Constant.selectEscortList:
                if (page == 1 && list.size() > 0) {
                    list.clear();
                }
                list = GsonUtils.getPersons(data, MyEscortBean.class);
                adapter.setNewData(list);
                if (list.size() == 0) {
                    adapter.setEmptyView(view);
                }
                break;
            case Constant.deleteEscortById:
                ToastUtils.show("删除成功");
                getdata();
                break;
        }
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {
        showComplete();
        ToastUtils.show(error);

    }
}