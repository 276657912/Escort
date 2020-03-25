package com.cn.android.ui.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cn.android.R;
import com.cn.android.bean.UserBean;
import com.cn.android.common.MyActivity;
import com.cn.android.network.Constant;
import com.cn.android.network.ServerUrl;
import com.cn.android.presenter.PublicInterfaceePresenetr;
import com.cn.android.presenter.view.PublicInterfaceView;
import com.cn.android.widget.LoopView;
import com.zhy.http.okhttp.utils.L;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class BirthdayActivity extends MyActivity implements PublicInterfaceView, LoopView.LoopScrollListener {

    @BindView(R.id.lv_time_1)
    LoopView lvTime1;
    @BindView(R.id.lv_time_2)
    LoopView lvTime2;
    @BindView(R.id.lv_time_3)
    LoopView lvTime3;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.star)
    TextView star;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_birthday;
    }

    @Override
    protected void initView() {
        presenetr=new PublicInterfaceePresenetr(this);
        age.setText(userBean().getAge() + "岁");
        star.setText(userBean().getStar());
    }

    ArrayList<String> yearList;

    @Override
    protected void initData() {
        // 生产小时
        yearList = new ArrayList<>();
        for (int i = 1970; i <= 2020; i++) {
            yearList.add(i + "");
        }

        // 生产月份
        ArrayList<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i + "");
        }

        // 生产天
        ArrayList<String> dayList = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            dayList.add(i + "");
        }
        lvTime1.setData(yearList);
        lvTime2.setData(monthList);
        lvTime3.setData(dayList);
        lvTime1.setLoopListener(this);
        lvTime2.setLoopListener(this);
        lvTime3.setLoopListener(this);
    }


    String date,ageNum,starMy;

    @Override
    public void onItemSelect(LoopView loopView, int position) {
        date = lvTime1.getSelectedItemData() + "-" + lvTime2.getSelectedItemData() + "-" + lvTime3.getSelectedItemData();
        starMy=constellation(lvTime2.getSelectedItem(), lvTime3.getSelectedItem());
        star.setText(starMy);
        ageNum=getAgeFromBirthTime(date)+"";
        age.setText(ageNum + "岁");
    }

    public static String constellation(int month, int day) {
        String constellation = "";
        if (month == 1 && day >= 20 || month == 2 && day <= 18) {
            constellation = "水瓶座";
        }
        if (month == 2 && day >= 19 || month == 3 && day <= 20) {
            constellation = "双鱼座";
        }
        if (month == 3 && day >= 21 || month == 4 && day <= 19) {
            constellation = "白羊座";
        }
        if (month == 4 && day >= 20 || month == 5 && day <= 20) {
            constellation = "金牛座";
        }
        if (month == 5 && day >= 21 || month == 6 && day <= 21) {
            constellation = "双子座";
        }
        if (month == 6 && day >= 22 || month == 7 && day <= 22) {
            constellation = "巨蟹座";
        }
        if (month == 7 && day >= 23 || month == 8 && day <= 22) {
            constellation = "狮子座";
        }
        if (month == 8 && day >= 23 || month == 9 && day <= 22) {
            constellation = "处女座";
        }
        if (month == 9 && day >= 23 || month == 10 && day <= 23) {
            constellation = "天秤座";
        }
        if (month == 10 && day >= 24 || month == 11 && day <= 22) {
            constellation = "天蝎座";
        }
        if (month == 11 && day >= 23 || month == 12 && day <= 21) {
            constellation = "射手座";
        }
        if (month == 12 && day >= 22 || month == 1 && day <= 19) {
            constellation = "摩羯座";
        }
        return constellation;
    }

    // 根据年月日计算年龄,birthTimeString:"1994-11-14"
    public static int getAgeFromBirthTime(String birthTimeString) {
        // 先截取到字符串中的年、月、日
        String strs[] = birthTimeString.trim().split("-");
        int selectYear = Integer.parseInt(strs[0]);
        int selectMonth = Integer.parseInt(strs[1]);
        int selectDay = Integer.parseInt(strs[2]);
        // 得到当前时间的年、月、日
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DATE);

        // 用当前年月日减去生日年月日
        int yearMinus = yearNow - selectYear;
        int monthMinus = monthNow - selectMonth;
        int dayMinus = dayNow - selectDay;

        int age = yearMinus;// 先大致赋值
        if (yearMinus < 0) {// 选了未来的年份
            age = 0;
        } else if (yearMinus == 0) {// 同年的，要么为1，要么为0
            if (monthMinus < 0) {// 选了未来的月份
                age = 0;
            } else if (monthMinus == 0) {// 同月份的
                if (dayMinus < 0) {// 选了未来的日期
                    age = 0;
                } else if (dayMinus >= 0) {
                    age = 1;
                }
            } else if (monthMinus > 0) {
                age = 1;
            }
        } else if (yearMinus > 0) {
            if (monthMinus < 0) {// 当前月>生日月
            } else if (monthMinus == 0) {// 同月份的，再根据日期计算年龄
                if (dayMinus < 0) {
                } else if (dayMinus >= 0) {
                    age = age + 1;
                }
            } else if (monthMinus > 0) {
                age = age + 1;
            }
        }
        return age;
    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);
        showLoading();
        presenetr.getPostTokenRequest(this, ServerUrl.updateBirthday, Constant.updateBirthday);
    }

    @Override
    public Map<String, Object> setPublicInterfaceData(int tag) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userid",userBean().getId());
        paramsMap.put("birthday",date);
        paramsMap.put("age",ageNum);
        paramsMap.put("star",starMy);
        return paramsMap;
    }

    @Override
    public void onPublicInterfaceSuccess(int code, String data, String msg, int tag) {
        showComplete();
        UserBean bean=userBean();
        bean.setBirthday(date);
        bean.setAge(ageNum);
        bean.setStar(starMy);
        saveUserBean(bean);
        toast(msg);
        this.finish();
    }

    @Override
    public void onPublicInterfaceError(String error, int tag) {

    }
}