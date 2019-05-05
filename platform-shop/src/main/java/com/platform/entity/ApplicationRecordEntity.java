package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 application_record
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-06 02:42:19
 */
public class ApplicationRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //申请食材id
    private Integer newFoodId;
    //申请人所在省份
    private String province;
    //申请人所在城市
    private String city;
    //申请人姓名
    private String name;
    //手机号码
    private String phone;
    //申请食材名称
    private String newFoodName;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：申请食材id
     */
    public void setNewFoodId(Integer newFoodId) {
        this.newFoodId = newFoodId;
    }

    /**
     * 获取：申请食材id
     */
    public Integer getNewFoodId() {
        return newFoodId;
    }
    /**
     * 设置：申请人所在省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：申请人所在省份
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：申请人所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：申请人所在城市
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：申请人姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：申请人姓名
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：手机号码
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置：申请食材名称
     */
    public void setNewFoodName(String newFoodName) {
        this.newFoodName = newFoodName;
    }

    /**
     * 获取：申请食材名称
     */
    public String getNewFoodName() {
        return newFoodName;
    }
}
