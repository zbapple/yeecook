package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 promotion
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-18 10:26:51
 */
public class PromotionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //推广人员姓名
    private String promotionName;
    //推广人员的手机号码
    private String promotionTelphone;
    //微信名称
    private String wxName;
    //微信头像
    private String wxListPic;
    //所在单位
    private String affiliatedUnit;
    //推广码
    private String promotionYard;

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
     * 设置：推广人员姓名
     */
    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    /**
     * 获取：推广人员姓名
     */
    public String getPromotionName() {
        return promotionName;
    }
    /**
     * 设置：推广人员的手机号码
     */
    public void setPromotionTelphone(String promotionTelphone) {
        this.promotionTelphone = promotionTelphone;
    }

    /**
     * 获取：推广人员的手机号码
     */
    public String getPromotionTelphone() {
        return promotionTelphone;
    }
    /**
     * 设置：微信名称
     */
    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    /**
     * 获取：微信名称
     */
    public String getWxName() {
        return wxName;
    }
    /**
     * 设置：微信头像
     */
    public void setWxListPic(String wxListPic) {
        this.wxListPic = wxListPic;
    }

    /**
     * 获取：微信头像
     */
    public String getWxListPic() {
        return wxListPic;
    }
    /**
     * 设置：所在单位
     */
    public void setAffiliatedUnit(String affiliatedUnit) {
        this.affiliatedUnit = affiliatedUnit;
    }

    /**
     * 获取：所在单位
     */
    public String getAffiliatedUnit() {
        return affiliatedUnit;
    }
    /**
     * 设置：推广码
     */
    public void setPromotionYard(String promotionYard) {
        this.promotionYard = promotionYard;
    }

    /**
     * 获取：推广码
     */
    public String getPromotionYard() {
        return promotionYard;
    }
}
