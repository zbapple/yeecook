package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 user_nutrition_menu
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:59
 */
public class UserNutritionMenuVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //餐单id
    private Integer id;
    //餐单名称
    private String menuName;
    //餐单封面图片
    private String menuCoverPic;
    //营养原理
    private String nutritionPrinciple;
    //膳食服务机构id
    private Integer cateringServiceOrgId;
    //膳食服务机构名称
    private String cateringServiceOrgName;
    //用户id
    private Long nideshopUserId;
    //服务周期开始时间
    private Date serviceCycleSt;
    //服务周期结束时间
    private Date servcieCycleEt;
    //商家id
    private Integer stroeid;
    /**
     * 设置：餐单id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：餐单id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：餐单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取：餐单名称
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * 设置：餐单封面图片
     */
    public void setMenuCoverPic(String menuCoverPic) {
        this.menuCoverPic = menuCoverPic;
    }

    /**
     * 获取：餐单封面图片
     */
    public String getMenuCoverPic() {
        return menuCoverPic;
    }
    /**
     * 设置：营养原理
     */
    public void setNutritionPrinciple(String nutritionPrinciple) {
        this.nutritionPrinciple = nutritionPrinciple;
    }

    /**
     * 获取：营养原理
     */
    public String getNutritionPrinciple() {
        return nutritionPrinciple;
    }
    /**
     * 设置：膳食服务机构id
     */
    public void setCateringServiceOrgId(Integer cateringServiceOrgId) {
        this.cateringServiceOrgId = cateringServiceOrgId;
    }

    /**
     * 获取：膳食服务机构id
     */
    public Integer getCateringServiceOrgId() {
        return cateringServiceOrgId;
    }
    /**
     * 设置：膳食服务机构名称
     */
    public void setCateringServiceOrgName(String cateringServiceOrgName) {
        this.cateringServiceOrgName = cateringServiceOrgName;
    }

    /**
     * 获取：膳食服务机构名称
     */
    public String getCateringServiceOrgName() {
        return cateringServiceOrgName;
    }
    /**
     * 设置：用户id
     */
    public void setNideshopUserId(Long nideshopUserId) {
        this.nideshopUserId = nideshopUserId;
    }
    /**
     * 获取：用户id
     */
    public Long getNideshopUserId() {
        return nideshopUserId;
    }
    /**
     * 设置：服务周期开始时间
     */
    public void setServiceCycleSt(Date serviceCycleSt) {
        this.serviceCycleSt = serviceCycleSt;
    }

    /**
     * 获取：服务周期开始时间
     */
    public Date getServiceCycleSt() {
        return serviceCycleSt;
    }
    /**
     * 设置：服务周期结束时间
     */
    public void setServcieCycleEt(Date servcieCycleEt) {
        this.servcieCycleEt = servcieCycleEt;
    }
    /**
     * 获取：服务周期结束时间
     */
    public Date getServcieCycleEt() {
        return servcieCycleEt;
    }

    public Integer getStroeid() {
        return stroeid;
    }

    public void setStroeid(Integer stroeid) {
        this.stroeid = stroeid;
    }
}
