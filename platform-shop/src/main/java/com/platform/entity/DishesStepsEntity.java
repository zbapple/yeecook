package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜品步骤表
实体
 * 表名 dishes_steps
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:31:59
 */
public class DishesStepsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //序号
    private Integer stepsNum;
    //菜品步骤图片
    private String stepsPic;
    //步骤描述
    private String stepsDescribe;
    //菜品id
    private Integer dishesId;
    //菜品名称
    private  String dishesname;

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
     * 设置：序号
     */
    public void setStepsNum(Integer stepsNum) {
        this.stepsNum = stepsNum;
    }

    /**
     * 获取：序号
     */
    public Integer getStepsNum() {
        return stepsNum;
    }
    /**
     * 设置：菜品步骤图片
     */
    public void setStepsPic(String stepsPic) {
        this.stepsPic = stepsPic;
    }

    /**
     * 获取：菜品步骤图片
     */
    public String getStepsPic() {
        return stepsPic;
    }
    /**
     * 设置：步骤描述
     */
    public void setStepsDescribe(String stepsDescribe) {
        this.stepsDescribe = stepsDescribe;
    }

    /**
     * 获取：步骤描述
     */
    public String getStepsDescribe() {
        return stepsDescribe;
    }
    /**
     * 设置：
     */
    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    /**
     * 获取：
     */
    public Integer getDishesId() {
        return dishesId;
    }

    public String getDishesname() {
        return dishesname;
    }

    public void setDishesname(String dishesname) {
        this.dishesname = dishesname;
    }
}
