package com.cn.android.bean;

import java.io.Serializable;

public class MyBarBean  implements Serializable {

    /**
     * id : 212425E79D2384EA584F0B2F30BB2D21
     * imgUrls : http://192.168.0.111:8888/upload/c8f5fd356b41491382bcac1a760507be.png,http://192.168.0.111:8888/upload/08eb5d8e671a49c29c758b21b65b76b0.png,http://192.168.0.111:8888/upload/19bf99e859094b49beae3b850fa7142e.jpg,http://192.168.0.111:8888/upload/fca0c98d0c69417082a785b5772d7ab1.jpg
     * content : 拿id
     * userid : 06813223
     * ctime : 2020-03-26 17:06:40
     * status : 1
     * city : 江门市
     */

    private String id;
    private String imgUrls;
    private String content;
    private String userid;
    private String ctime;
    private int status;
    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
