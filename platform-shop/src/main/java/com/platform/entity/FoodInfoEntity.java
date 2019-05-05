package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 food_info
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
public class FoodInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //食材图片
    private String foodImg;
    //食材标题
    private String foodTile;
    //食材描述
    private String foodRemark;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：食材图片
     */
    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    /**
     * 获取：食材图片
     */
    public String getFoodImg() {
        return foodImg;
    }
    /**
     * 设置：食材标题
     */
    public void setFoodTile(String foodTile) {
        this.foodTile = foodTile;
    }

    /**
     * 获取：食材标题
     */
    public String getFoodTile() {
        return foodTile;
    }
    /**
     * 设置：食材描述
     */
    public void setFoodRemark(String foodRemark) {
        this.foodRemark = foodRemark;
    }

    /**
     * 获取：食材描述
     */
    public String getFoodRemark() {
        return foodRemark;
    }
}
