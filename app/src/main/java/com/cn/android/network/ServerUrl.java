package com.cn.android.network;

import com.cn.android.utils.SPUtils;

/**
 * Created by PC-122 on 2017/12/21.
 */
public class ServerUrl {

    public static String defaultIp="http://192.168.0.111:8888";

    public static String selectBarHomeList=defaultIp+"/app/home/selectBarHomeList";//社区 酒吧诈招募列表
    public static String selectEscortHomeList=defaultIp+"/app/home/selectEscortHomeList";//社区 陪伴诈招募列表
    public static String selectVipPackggeList=defaultIp+"/app/user/selectVipPackggeList";//开通会员 页面的vip套餐列表
    public static String selectVipLogoList=defaultIp+"/app/user/selectVipLogoList";//开通会员 页面的vip标识列表
    public static String addBar=defaultIp+"/app/user/addBar";//酒吧招聘 添加
    public static String deleteBarById=defaultIp+"/app/user/deleteBarById";//酒吧招聘 删除
    public static String selectBarList=defaultIp+"/app/user/selectBarList";//我的酒吧招聘 列表
    public static String addVideo=defaultIp+"/app/user/addVideo";//我的视频 添加
    public static String addPhoto=defaultIp+"/app/user/addPhoto";//我的图片 添加
    public static String addEscort=defaultIp+"/app/user/addEscort";//伴游添加
    public static String deleteEscortById=defaultIp+"/app/user/deleteEscortById";//伴游 删除
    public static String selectVideoList=defaultIp+"/app/user/selectVideoList";//我的视频列表
    public static String selectPhotoList=defaultIp+"/app/user/selectPhotoList";//我的图片列表
    public static String selectEscortList=defaultIp+"/app/user/selectEscortList";//伴游我的发布和草稿列表
    public static String tixian=defaultIp+"/app/user/tixian";//提现
    public static String jiebangAlipay=defaultIp+"/app/user/jiebangAlipay";//解绑
    public static String bangdingAlipay=defaultIp+"/app/user/bangdingAlipay";//账号绑定
    public static String selectAccountList=defaultIp+"/app/user/selectAccountList";//账单明细
    public static String cancelAppUser=defaultIp+"/app/user/cancelAppUser";//注销
    public static String panduanPassword=defaultIp+"/app/user/panduanPassword";//修改绑定手机号/注销 第一步
    public static String updatephone=defaultIp+"/app/user/updatephone";//修改绑定手机号 第二步
    public static String updatePassword=defaultIp+"/app/user/updatePassword";//修改密码
    public static String updateSignature=defaultIp+"/app/user/updateSignature";//修改个性签名
    public static String updateBirthday=defaultIp+"/app/user/updateBirthday";//修改出生日期
    public static String updatenickname=defaultIp+"/app/user/updatenickname";//修改昵称
    public static String realAppUser=defaultIp+"/app/user/realAppUser";//实名认证
    public static String upload=defaultIp+"/app/user/upload";//上传图片
    public static String register=defaultIp+"/app/login/register";//注册
    public static String judgePassword=defaultIp+"/app/login/judgePassword";//登录  第二步（输入密码）
    public static String selectAppUserByPhone=defaultIp+"/app/login/selectAppUserByPhone";//登录/注册  第一步（返回200跳转输入密码登录，返回500去注册）
    public static String sendSms=defaultIp+"/app/login/sendSms";//发送短信

}
