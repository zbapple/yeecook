package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 餐单详情表
实体
 * 表名 menu_details
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:23:22
 */
public class MenuDetailsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //餐单id
    private Integer userNutritionMenuId;
    //餐单类型
    private String menuType;
    //餐品id
    private Integer dishesId;
    //餐品名
    private String dishesName;
    //叶子节点
    private Integer leafNode;
    //父类id
    private Integer fatherId;
    //用餐时间
    private Date mealTime;
    //餐单日期
    private Date menuDate;
    //菜品卡路里
    private Double dishesCalories;
    //菜品图片
    private String dishesCoverPic;
    //用户id
    private Long nideshopUserId;

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
     * 设置：餐单id
     */
    public void setUserNutritionMenuId(Integer userNutritionMenuId) {
        this.userNutritionMenuId = userNutritionMenuId;
    }

    /**
     * 获取：餐单id
     */
    public Integer getUserNutritionMenuId() {
        return userNutritionMenuId;
    }

    /**
     * 设置：餐单类型
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取：餐单类型
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 设置：餐品id
     */
    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    /**
     * 获取：餐品id
     */
    public Integer getDishesId() {
        return dishesId;
    }

    /**
     * 设置：餐品名
     */
    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    /**
     * 获取：餐品名
     */
    public String getDishesName() {
        return dishesName;
    }

    /**
     * 设置：叶子节点
     */
    public void setLeafNode(Integer leafNode) {
        this.leafNode = leafNode;
    }

    /**
     * 获取：叶子节点
     */
    public Integer getLeafNode() {
        return leafNode;
    }

    /**
     * 设置：父类id
     */
    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    /**
     * 获取：父类id
     */
    public Integer getFatherId() {
        return fatherId;
    }

    /**
     * 设置：用餐时间
     */
    public void setMealTime(Date mealTime) {
        this.mealTime = mealTime;
    }

    /**
     * 获取：用餐时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getMealTime() {
        return mealTime;
    }

    /**
     * 设置：餐单日期
     */
    public void setMenuDate(Date menuDate) {
        this.menuDate = menuDate;
    }

    /**
     * 获取：餐单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getMenuDate() {
        return menuDate;
    }

    public Double getDishesCalories() {
        return dishesCalories;
    }

    public void setDishesCalories(Double dishesCalories) {
        this.dishesCalories = dishesCalories;
    }

    public String getDishesCoverPic() {
        return dishesCoverPic;
    }

    public void setDishesCoverPic(String dishesCoverPic) {
        this.dishesCoverPic = dishesCoverPic;
    }


    public Long getNideshopUserId() {
        return nideshopUserId;
    }

    public void setNideshopUserId(Long nideshopUserId) {
        this.nideshopUserId = nideshopUserId;
    }
}
