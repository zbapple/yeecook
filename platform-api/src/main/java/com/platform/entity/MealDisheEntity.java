package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 meal_dishe
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-27 16:50:29
 */
public class MealDisheEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //套餐id
    private Integer mealid;
    //菜品id
    private Integer disheid;
    //菜品名称
    private String  dishesname;
    //套餐名称
    private String mealname;
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
     * 设置：套餐id
     */
    public void setMealid(Integer mealid) {
        this.mealid = mealid;
    }

    /**
     * 获取：套餐id
     */
    public Integer getMealid() {
        return mealid;
    }
    /**
     * 设置：菜品id
     */
    public void setDisheid(Integer disheid) {
        this.disheid = disheid;
    }

    /**
     * 获取：菜品id
     */
    public Integer getDisheid() {
        return disheid;
    }

    public String getDishesname() {
        return dishesname;
    }

    public void setDishesname(String dishesname) {
        this.dishesname = dishesname;
    }

    public String getMealname() {
        return mealname;
    }

    public void setMealname(String mealname) {
        this.mealname = mealname;
    }
}
