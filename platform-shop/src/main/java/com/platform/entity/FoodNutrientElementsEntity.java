package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 食材营养元素对应表
实体
 * 表名 food_nutrient_elements
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:26:15
 */
public class FoodNutrientElementsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //食材id
    private Integer foodMaterialId;
    //营养元素id
    private Integer nutrientElementsId;
    //每克含量
    private String contentG;

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
     * 设置：营养元素id
     */
    public void setNutrientElementsId(Integer nutrientElementsId) {
        this.nutrientElementsId = nutrientElementsId;
    }

    /**
     * 获取：营养元素id
     */
    public Integer getNutrientElementsId() {
        return nutrientElementsId;
    }
    /**
     * 设置：每克含量
     */
    public void setContentG(String contentG) {
        this.contentG = contentG;
    }

    /**
     * 获取：每克含量
     */
    public String getContentG() {
        return contentG;
    }
}
