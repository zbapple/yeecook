package com.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 实体
 * 表名 new_menu
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 10:28:00
 */
public class ApiNewMenuVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //id
    private Integer id;
    //等级
    private String nlevel="";
    //分娩方式
    private String deliveryWay="";
    //喂哺方式
    private String feedingWay="";
    //月子时长
    private Integer liliLength;
    //总能量值
    private String theTotalEnergy="";
    //浏览量
    private String wxViews="";
    @Getter@Setter
    private List<Map> list1;
    /**
     * 设置：id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：等级
     */
    public void setNlevel(String nlevel) {
        this.nlevel = nlevel;
    }

    /**
     * 获取：等级
     */
    public String getNlevel() {
        return nlevel;
    }
    /**
     * 设置：分娩方式
     */
    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay;
    }

    /**
     * 获取：分娩方式
     */
    public String getDeliveryWay() {
        return deliveryWay;
    }
    /**
     * 设置：喂哺方式
     */
    public void setFeedingWay(String feedingWay) {
        this.feedingWay = feedingWay;
    }

    /**
     * 获取：喂哺方式
     */
    public String getFeedingWay() {
        return feedingWay;
    }
    /**
     * 设置：月子时长
     */
    public void setLiliLength(Integer liliLength) {
        this.liliLength = liliLength;
    }

    /**
     * 获取：月子时长
     */
    public Integer getLiliLength() {
        return liliLength;
    }
    /**
     * 设置：总能量值
     */
    public void setTheTotalEnergy(String theTotalEnergy) {
        this.theTotalEnergy = theTotalEnergy;
    }

    /**
     * 获取：总能量值
     */
    public String getTheTotalEnergy() {
        return theTotalEnergy;
    }
    /**
     * 设置：浏览量
     */
    public void setWxViews(String wxViews) {
        this.wxViews = wxViews;
    }

    /**
     * 获取：浏览量
     */
    public String getWxViews() {
        return wxViews;
    }
    public void setList1(List<Map> list1){this.list1=list1;}
    public List<Map> getList1(){return list1;}
}
