package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户身体信息表
实体
 * 表名 user_body_information
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:20:43
 */
public class UserBodyInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //用户id
    private Integer nideshopUserId;
    //身高
    private Double userHeight;
    //生日
    private Date userBirthday;
    //目标体重
    private Double goalWeight;

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
     * 设置：用户id
     */
    public void setNideshopUserId(Integer nideshopUserId) {
        this.nideshopUserId = nideshopUserId;
    }

    /**
     * 获取：用户id
     */
    public Integer getNideshopUserId() {
        return nideshopUserId;
    }
    /**
     * 设置：身高
     */
    public void setUserHeight(Double userHeight) {
        this.userHeight = userHeight;
    }

    /**
     * 获取：身高
     */
    public Double getUserHeight() {
        return userHeight;
    }
    /**
     * 设置：生日
     */
    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    /**
     * 获取：生日
     */
    public Date getUserBirthday() {
        return userBirthday;
    }
    /**
     * 设置：目标体重
     */
    public void setGoalWeight(Double goalWeight) {
        this.goalWeight = goalWeight;
    }

    /**
     * 获取：目标体重
     */
    public Double getGoalWeight() {
        return goalWeight;
    }
}
