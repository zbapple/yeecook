package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 stroe
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-31 09:41:07
 */
public class StroeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //门店名称
    private String storeName;
    //门店类型(1.营养餐 2.月子餐3.长者餐)
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
    //经度
    private Double longitude;
    //纬度
    private Double latitude;
    //门店电话
    private String storePhone;
    //起送费
    private Double sendingfee;
    //配送费
    private Double deliveryfee;
    //门店实景图片
    private String realisticPicture;
    //门店的食品执照
    private String licensePic;
    //门店营业时间
    private  String storetime;
    //距离
    private String juli;
    //评分
    private  Double stroeGrade;;
    //销售量
    private Integer salesVolume;;
    //食品营业范围
    private String businesslicense;

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
     * 设置：门店类型(1.营养餐 2.月子餐3.长者餐)
     */
    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    /**
     * 获取：门店类型(1.营养餐 2.月子餐3.长者餐)
     */
    public Integer getStoreType() {
        return storeType;
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
    /**
     * 设置：经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取：经度
     */
    public Double getLongitude() {
        return longitude;
    }
    /**
     * 设置：纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取：纬度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 设置：起送费
     */
    public void setSendingfee(Double sendingfee) {
        this.sendingfee = sendingfee;
    }

    /**
     * 获取：起送费
     */
    public Double getSendingfee() {
        return sendingfee;
    }
    /**
     * 设置：配送费
     */
    public void setDeliveryfee(Double deliveryfee) {
        this.deliveryfee = deliveryfee;
    }

    /**
     * 获取：配送费
     */
    public Double getDeliveryfee() {
        return deliveryfee;
    }
    /**
     * 设置：门店实景图片
     */
    public void setRealisticPicture(String realisticPicture) {
        this.realisticPicture = realisticPicture;
    }

    /**
     * 获取：门店实景图片
     */
    public String getRealisticPicture() {
        return realisticPicture;
    }
    /**
     * 设置：门店的食品执照
     */
    public void setLicensePic(String licensePic) {
        this.licensePic = licensePic;
    }

    /**
     * 获取：门店的食品执照
     */
    public String getLicensePic() {
        return licensePic;
    }

    public String getJuli() {
        return juli;
    }

    public void setJuli(String juli) {
        this.juli = juli;
    }
    public String getStoretime() {
        return storetime;
    }

    public void setStoretime(String storetime) {
        this.storetime = storetime;
    }

    public String getBusinesslicense() {
        return businesslicense;
    }

    public void setBusinesslicense(String businesslicense) {
        this.businesslicense = businesslicense;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public Double getStroeGrade() {
        return stroeGrade;
    }

    public void setStroeGrade(Double stroeGrade) {
        this.stroeGrade = stroeGrade;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }
}
