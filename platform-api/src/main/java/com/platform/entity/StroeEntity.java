package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 stroe
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-19 10:11:30
 */
public class StroeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //门店名称
    private String storeName;
    //门店类型
    private Integer storeType;
    //门店logo
    private String storePicrue;
    //国家
    private String country;
    //省
    private String province;
    //城市
    private String city;
    //区
    private String distrct;
    //详细地址
    private String address;
    //部门id
    private Integer departmentid;
    //供应商id
    private Integer supplierid;
    //门店评分
    private Double  grade ;
    //月销售
    private  Integer coutsale;
    //门店优惠券
    private String coupon;
    //经度
    private Double longitude;
    //纬度
    private Double latitude;
    //商家电话
    private String stroephone;
    //距离
    private Double juli;
    //起送费
    private Double sendingfee;
    //配送费
    private Double deliveryfee;
    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：门店名称
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 获取：门店名称
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置：门店logo
     */
    public void setStorePicrue(String storePicrue) {
        this.storePicrue = storePicrue;
    }

    /**
     * 获取：门店logo
     */
    public String getStorePicrue() {
        return storePicrue;
    }
    /**
     * 设置：国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取：国家
     */
    public String getCountry() {
        return country;
    }
    /**
     * 设置：省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：省
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：城市
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：区
     */
    public void setDistrct(String distrct) {
        this.distrct = distrct;
    }

    /**
     * 获取：区
     */
    public String getDistrct() {
        return distrct;
    }
    /**
     * 设置：详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：详细地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：部门id
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * 获取：部门id
     */
    public Integer getDepartmentid() {
        return departmentid;
    }
    /**
     * 设置：供应商id
     */
    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    /**
     * 获取：供应商id
     */
    public Integer getSupplierid() {
        return supplierid;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getStroephone() {
        return stroephone;
    }

    public void setStroephone(String stroephone) {
        this.stroephone = stroephone;
    }


    public Double getJuli() {
        return juli;
    }

    public void setJuli(Double juli) {
        this.juli = juli;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public Integer getCoutsale() {
        return coutsale;
    }

    public void setCoutsale(Integer coutsale) {
        this.coutsale = coutsale;
    }


    public Double getSendingfee() {
        return sendingfee;
    }

    public void setSendingfee(Double sendingfee) {
        this.sendingfee = sendingfee;
    }

    public Double getDeliveryfee() {
        return deliveryfee;
    }

    public void setDeliveryfee(Double deliveryfee) {
        this.deliveryfee = deliveryfee;
    }
}
