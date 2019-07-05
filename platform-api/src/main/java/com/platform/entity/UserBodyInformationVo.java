package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class UserBodyInformationVo  implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //用户id
    private Long nideshopUserId;
    //身高
    private Double userHeight;
    //生日
    private Date userBirthday;
    //目标体重
    private Double goalWeight;
    //初始化体重
    private Double weight;
    //BMI
    private  Double BMI;
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
    public void setNideshopUserId(Long nideshopUserId) {
        this.nideshopUserId = nideshopUserId;
    }

    /**
     * 获取：用户id
     */
    public Long getNideshopUserId() {
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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

    public  void  setWeight (Double weight){
        this.weight=weight;
    }
    public  Double getWeight(){ return weight;}

    public void  setBMI(Double BMI){this.BMI=BMI;  }
    public  Double getBMI(){return BMI;}
}
