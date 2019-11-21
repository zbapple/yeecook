package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 实体
 * 表名 meal
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-27 16:50:29
 */
public class MealVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //套餐名称
    private String mealName;
    //套餐图片
    private String mealPriue;
    //营养说明
    private String nutritionExplain;
    //门店id
    private Integer stroeid;
    //套餐类型
    private Integer mealType;
    //价格
    private BigDecimal mealPice;
    //菜品名称集合
    private List  dishesname;
    //门店名称
    private  String stroename;
    private  Integer stroe_id;
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
     * 设置：套餐名称
     */
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    /**
     * 获取：套餐名称
     */
    public String getMealName() {
        return mealName;
    }
    /**
     * 设置：套餐图片
     */
    public void setMealPriue(String mealPriue) {
        this.mealPriue = mealPriue;
    }

    /**
     * 获取：套餐图片
     */
    public String getMealPriue() {
        return mealPriue;
    }
    /**
     * 设置：营养说明
     */
    public void setNutritionExplain(String nutritionExplain) {
        this.nutritionExplain = nutritionExplain;
    }

    /**
     * 获取：营养说明
     */
    public String getNutritionExplain() {
        return nutritionExplain;
    }
    /**
     * 设置：门店id
     */
    public void setStroeid(Integer stroeid) {
        this.stroeid = stroeid;
    }

    /**
     * 获取：门店id
     */
    public Integer getStroeid() {
        return stroeid;
    }
    /**
     * 设置：套餐类型
     */
    public void setMealType(Integer mealType) {
        this.mealType = mealType;
    }

    /**
     * 获取：套餐类型
     */
    public Integer getMealType() {
        return mealType;
    }


    public List getDishesname() {
        return dishesname;
    }

    public void setDishesname(List dishesname) {
        this.dishesname = dishesname;
    }

    public BigDecimal getMealPice() {
        return mealPice;
    }

    public void setMealPice(BigDecimal mealPice) {
        this.mealPice = mealPice;
    }

    public String getStroename() {
        return stroename;
    }

    public void setStroename(String stroename) {
        this.stroename = stroename;
    }

    public Integer getStroe_id() {
        return stroe_id;
    }

    public void setStroe_id(Integer stroe_id) {
        this.stroe_id = stroe_id;
    }
}
