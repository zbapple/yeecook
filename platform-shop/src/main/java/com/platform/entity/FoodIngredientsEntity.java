package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜品食材表
实体
 * 表名 food_ingredients
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:29:33
 */
public class FoodIngredientsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //食材id
    private Integer foodMaterialId;
    //食材名字
    private String foodMaterialName;
    //食材数量
    private String foodMaterialNum;
    //食材总卡路里
    private String foodMaterialSumcal;
    //
    private Integer dishesId;

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
     * 设置：食材id
     */
    public void setFoodMaterialId(Integer foodMaterialId) {
        this.foodMaterialId = foodMaterialId;
    }

    /**
     * 获取：食材id
     */
    public Integer getFoodMaterialId() {
        return foodMaterialId;
    }
    /**
     * 设置：食材名字
     */
    public void setFoodMaterialName(String foodMaterialName) {
        this.foodMaterialName = foodMaterialName;
    }

    /**
     * 获取：食材名字
     */
    public String getFoodMaterialName() {
        return foodMaterialName;
    }
    /**
     * 设置：食材数量
     */
    public void setFoodMaterialNum(String foodMaterialNum) {
        this.foodMaterialNum = foodMaterialNum;
    }

    /**
     * 获取：食材数量
     */
    public String getFoodMaterialNum() {
        return foodMaterialNum;
    }
    /**
     * 设置：食材总卡路里
     */
    public void setFoodMaterialSumcal(String foodMaterialSumcal) {
        this.foodMaterialSumcal = foodMaterialSumcal;
    }

    /**
     * 获取：食材总卡路里
     */
    public String getFoodMaterialSumcal() {
        return foodMaterialSumcal;
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
}
