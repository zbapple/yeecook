package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_activation_code_log
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-21 01:13:48
 */
public class ActivationCodeLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //卡片信息id
    private Integer cardId;
    //激活码生成数量
    private Integer cardCount;
    //操作人id
    private Integer userId;
    //操作人姓名
    private String userName;
    //生成时间
    private Date addTime;
    //备注
    private String remark;

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
     * 设置：卡片信息id
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取：卡片信息id
     */
    public Integer getCardId() {
        return cardId;
    }
    /**
     * 设置：激活码生成数量
     */
    public void setCardCount(Integer cardCount) {
        this.cardCount = cardCount;
    }

    /**
     * 获取：激活码生成数量
     */
    public Integer getCardCount() {
        return cardCount;
    }
    /**
     * 设置：操作人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：操作人id
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * 设置：操作人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：操作人姓名
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：生成时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：生成时间
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }
}
