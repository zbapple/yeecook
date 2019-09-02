package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 食材表

实体
 * 表名 food_material
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:28:09
 */

@Data
public class FoodMaterialEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //食材id
    private Integer id;
    //食材名
    private String foodMaterialName;
    //食材图片
    private String foodMaterialPic;
    //食材描述
    private String foodMaterialDescribe;
    //食材单位
    private String foodUnit;
    //食材卡路里
    private String foodMaterialCalories;
    //食材类型id
    private Integer foodTypeId;
    //食材类型
    private String typeName;
    /**
     * 设置：食材id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：食材id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：食材名
     */
    public void setFoodMaterialName(String foodMaterialName) {
        this.foodMaterialName = foodMaterialName;
    }

    /**
     * 获取：食材名
     */
    public String getFoodMaterialName() {
        return foodMaterialName;
    }
    /**
     * 设置：食材图片
     */
    public void setFoodMaterialPic(String foodMaterialPic) {
        this.foodMaterialPic = foodMaterialPic;
    }

    /**
     * 获取：食材图片
     */
    public String getFoodMaterialPic() {
        return foodMaterialPic;
    }
    /**
     * 设置：食材描述
     */
    public void setFoodMaterialDescribe(String foodMaterialDescribe) {
        this.foodMaterialDescribe = foodMaterialDescribe;
    }

    /**
     * 获取：食材描述
     */
    public String getFoodMaterialDescribe() {
        return foodMaterialDescribe;
    }

    /**
     * 设置：食材单位
     **/
    public  void setFoodUnit(String foodUnit){
        this.foodUnit=foodUnit;
    }

    /**
     * 获取：食材单位
     **/
    public String getFoodUnit(){return  foodUnit;}

    /**
     * 设置：食材卡路里
     */
    public void setFoodMaterialCalories(String foodMaterialCalories) {
        this.foodMaterialCalories = foodMaterialCalories;
    }

    /**
     * 获取：食材卡路里
     */
    public String getFoodMaterialCalories() {
        return foodMaterialCalories;
    }
    /**
     * 设置：食材类型id
     */
    public void setFoodTypeId(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    /**
     * 获取：食材类型id
     */
    public Integer getFoodTypeId() {
        return foodTypeId;
    }
}
