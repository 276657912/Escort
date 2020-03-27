package com.cn.android.bean;

import java.io.Serializable;

public class BillBean implements Serializable {

    /**
     * id : 415A4C619E7E6123EEB5B52FACA341AC
     * type : 2
     * payType : 3
     * payMoney : 1.2
     * status : 0
     * ctime : 2020-03-25 18:33:47
     * userid : 49261655
     * umoney : 9769.5
     * ordercode :
     * shoporder :
     * remark :
     */

    private String id;
    private int type;
    private int payType;
    private String payMoney;
    private int status;
    private String ctime;
    private String userid;
    private String umoney;
    private String ordercode;
    private String shoporder;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUmoney() {
        return umoney;
    }

    public void setUmoney(String umoney) {
        this.umoney = umoney;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getShoporder() {
        return shoporder;
    }

    public void setShoporder(String shoporder) {
        this.shoporder = shoporder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
