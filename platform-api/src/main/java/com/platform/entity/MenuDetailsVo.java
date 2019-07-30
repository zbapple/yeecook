package com.platform.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 餐单详情表
 id
 餐单id
 餐单类型
 菜品id
 菜品名称
 是否叶子节点
 父级id
 用餐时间
 餐单日期实体
 * 表名 menu_details
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:55
 */
public class MenuDetailsVo implements Serializable {
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
    private Double dishescalories;
    //菜品图片
    private String dishescoverpic;
    //用户id
    private  Long nideshopuserid;
    //签约
    private  Integer menustatus;
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
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
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

    public Double getDishescalories() {
        return dishescalories;
    }

    public void setDishescalories(Double dishescalories) {
        this.dishescalories = dishescalories;
    }

    public String getDishescoverpic() {
        return dishescoverpic;
    }

    public void setDishescoverpic(String dishescoverpic) {
        this.dishescoverpic = dishescoverpic;
    }

    public Long getNideshopUserid() {
        return nideshopuserid;
    }

    public void setNideshopUserid(Long nideshopuserid) {
        this.nideshopuserid = nideshopuserid;
    }

    public Integer getMenustatus() {
        return menustatus;
    }

    public void setMenustatus(Integer menustatus) {
        this.menustatus = menustatus;
    }
}
