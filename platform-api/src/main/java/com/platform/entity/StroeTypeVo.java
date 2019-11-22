package com.platform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体
 * 表名 nideshop_stroe_type
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-11-04 11:04:11
 */
public class StroeTypeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //门店类型
    private String name;
    //门店id
    private Integer stroeId;
    //类型
    private Integer stroetype;
    //门店名称
    private String storeName;
    //门店logo
    private String storePicrue;
    //起送费
    private Double sendingfee;
    //配送费
    private Double deliveryfee;
    //距离
    private String juli;
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
     * 设置：门店类型
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：门店类型
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：门店id
     */
    public void setStroeId(Integer stroeId) {
        this.stroeId = stroeId;
    }

    /**
     * 获取：门店id
     */
    public Integer getStroeId() {
        return stroeId;
    }

    public Integer getStroetype() {
        return stroetype;
    }

    public void setStroetype(Integer stroetype) {
        this.stroetype = stroetype;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePicrue() {
        return storePicrue;
    }

    public void setStorePicrue(String storePicrue) {
        this.storePicrue = storePicrue;
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