package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 new_products
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 10:28:00
 */
public class ApiNewProductsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Integer id;
    //餐品名
    private String mfoodName;
    //营养成分
    private String nutrients;
    //食材
    private String foodMaterial;
    //准备材料
    private String prepareMaterial;

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
     * 设置：餐品名
     */
    public void setMfoodName(String mfoodName) {
        this.mfoodName = mfoodName;
    }

    /**
     * 获取：餐品名
     */
    public String getMfoodName() {
        return mfoodName;
    }
    /**
     * 设置：营养成分
     */
    public void setNutrients(String nutrients) {
        this.nutrients = nutrients;
    }

    /**
     * 获取：营养成分
     */
    public String getNutrients() {
        return nutrients;
    }
    /**
     * 设置：食材
     */
    public void setFoodMaterial(String foodMaterial) {
        this.foodMaterial = foodMaterial;
    }

    /**
     * 获取：食材
     */
    public String getFoodMaterial() {
        return foodMaterial;
    }
    /**
     * 设置：准备材料
     */
    public void setPrepareMaterial(String prepareMaterial) {
        this.prepareMaterial = prepareMaterial;
    }

    /**
     * 获取：准备材料
     */
    public String getPrepareMaterial() {
        return prepareMaterial;
    }
}
