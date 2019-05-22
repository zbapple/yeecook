package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 new_menu_list
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 14:49:30
 */
public class NewMenuListEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Integer id;
    //餐品时段
    private String mtime;
    //餐品
    private String mfood;
    //卡路里
    private Double calories;
    //产后时间
    private Integer postpartumTime;
    //等级
    private String mlevel;

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
     * 设置：餐品时段
     */
    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    /**
     * 获取：餐品时段
     */
    public String getMtime() {
        return mtime;
    }
    /**
     * 设置：餐品
     */
    public void setMfood(String mfood) {
        this.mfood = mfood;
    }

    /**
     * 获取：餐品
     */
    public String getMfood() {
        return mfood;
    }
    /**
     * 设置：卡路里
     */
    public void setCalories(Double calories) {
        this.calories = calories;
    }

    /**
     * 获取：卡路里
     */
    public Double getCalories() {
        return calories;
    }
    /**
     * 设置：产后时间
     */
    public void setPostpartumTime(Integer postpartumTime) {
        this.postpartumTime = postpartumTime;
    }

    /**
     * 获取：产后时间
     */
    public Integer getPostpartumTime() {
        return postpartumTime;
    }
    /**
     * 设置：等级
     */
    public void setMlevel(String mlevel) {
        this.mlevel = mlevel;
    }

    /**
     * 获取：等级
     */
    public String getMlevel() {
        return mlevel;
    }
}
