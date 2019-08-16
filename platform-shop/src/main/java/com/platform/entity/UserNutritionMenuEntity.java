package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户营养餐单表
实体
 * 表名 user_nutrition_menu
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:05:27
 */
@Data
public class UserNutritionMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //餐单id
    private Integer id;
    //餐单编号
    private String menuSn;
    //餐单名称
    private String menuName;
    //主表餐单类型
    private String nutritionMenuType;
    //餐单封面图片
    private String menuCoverPic;
    //营养原理
    private String nutritionPrinciple;
    //膳食服务机构id
    private Integer cateringServiceOrgId;
    //膳食服务机构名称
    private String cateringServiceOrgName;
    //用户id
    private Integer nideshopUserId;
    //服务周期开始时间
    private Date serviceCycleSt;
    //服务周期结束时间
    private Date serviceCycleEt;
    //服务阶段
    private String serviceStage;
    //服务状态  0未签约 1已签约
    private Integer menuStatus;
    //用户名称
    private  String username;
    //用户名称
    private  String nickname;
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
     * 设置：主表餐单类型
     **/
    public void setNutritionMenuType(String nutritionMenuType){this.nutritionMenuType = nutritionMenuType;}

    /**
     * 获取：主表餐单类型
     **/
    public String getNutritionMenuType(){ return nutritionMenuType;}

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
    public void setNideshopUserId(Integer nideshopUserId) {
        this.nideshopUserId = nideshopUserId;
    }

    /**
     * 获取：用户id
     */
    public Integer getNideshopUserId() {
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
    public void setServiceCycleEt(Date serviceCycleEt) {
        this.serviceCycleEt = serviceCycleEt;
    }

    /**
     * 获取：服务周期结束时间
     */
    public Date getServiceCycleEt() {
        return serviceCycleEt;
    }

    /**
     * 设置：服务阶段
     **/
    public void setServiceStage(String serviceStage){ this.serviceStage=serviceStage;}

    /**
     * 获取：服务阶段
     **/
    public String getServiceStage(){ return  serviceStage;}
    /**
     * 设置：服务状态 0：未签约  1：已签约
     **/
    public void setMenuStatus(Integer menuStatus){ this.menuStatus = menuStatus;}

    /**
     * 获取：服务状态 0：未签约  1：已签约
     **/
    public Integer getMenuStatus(){return  menuStatus;}

    /**
     *  餐单编号
     **/
    public String getMenuSn() {
        return menuSn;
    }

    public void setMenuSn(String menuSn) {
        this.menuSn = menuSn;
    }
}
