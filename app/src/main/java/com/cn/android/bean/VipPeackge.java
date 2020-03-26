package com.cn.android.bean;

public class VipPeackge {

    /**
     * id : 1
     * name : 连续包月
     * title : 28/月
     * preferMoney : 10
     * money : 28
     * ctime : 2020-03-18 12:12:12
     * status : 1
     */

    private String id;
    private String name;
    private String title;
    private int preferMoney;
    private int money;
    private String ctime;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPreferMoney() {
        return preferMoney;
    }

    public void setPreferMoney(int preferMoney) {
        this.preferMoney = preferMoney;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
