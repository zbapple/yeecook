package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 dzdp
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-02-25 17:08:00
 */
public class DzdpEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //
    private String name;
    //
    private String province;
    //
    private String city;
    //
    private String areas;
    //
    private String address;
    //
    private String latitude;
    //
    private String longitude;
    //
    private String categories;
    //
    private String phones;
    //
    private String openingHours;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：
     */
    public void setAreas(String areas) {
        this.areas = areas;
    }

    /**
     * 获取：
     */
    public String getAreas() {
        return areas;
    }
    /**
     * 设置：
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取：
     */
    public String getLatitude() {
        return latitude;
    }
    /**
     * 设置：
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取：
     */
    public String getLongitude() {
        return longitude;
    }
    /**
     * 设置：
     */
    public void setCategories(String categories) {
        this.categories = categories;
    }

    /**
     * 获取：
     */
    public String getCategories() {
        return categories;
    }
    /**
     * 设置：
     */
    public void setPhones(String phones) {
        this.phones = phones;
    }

    /**
     * 获取：
     */
    public String getPhones() {
        return phones;
    }
    /**
     * 设置：
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * 获取：
     */
    public String getOpeningHours() {
        return openingHours;
    }
}
