package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_activation_code_log
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-28 00:48:59
 */
public class ActivationCodeLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //关联服务id
    private Integer serveInfoId;
    //激活时间
    private Date activationTime;
    //激活用户id
    private Integer userId;
    //激活用户姓名
    private String userName;
    //备注
    private String remark;
    //激活码
    private String activationCode;
    //激活终端
    private String activateTerminal;
    //订单编号
    private String orderSn;
    //关联服务名称
    private String serveInfoName;

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
     * 设置：关联服务id
     */
    public void setServeInfoId(Integer serveInfoId) {
        this.serveInfoId = serveInfoId;
    }

    /**
     * 获取：关联服务id
     */
    public Integer getServeInfoId() {
        return serveInfoId;
    }
    /**
     * 设置：激活时间
     */
    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }

    /**
     * 获取：激活时间
     */
    public Date getActivationTime() {
        return activationTime;
    }
    /**
     * 设置：激活用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：激活用户id
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * 设置：激活用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：激活用户姓名
     */
    public String getUserName() {
        return userName;
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
    /**
     * 设置：激活码
     */
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * 获取：激活码
     */
    public String getActivationCode() {
        return activationCode;
    }
    /**
     * 设置：激活终端
     */
    public void setActivateTerminal(String activateTerminal) {
        this.activateTerminal = activateTerminal;
    }

    /**
     * 获取：激活终端
     */
    public String getActivateTerminal() {
        return activateTerminal;
    }
    /**
     * 设置：订单编号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取：订单编号
     */
    public String getOrderSn() {
        return orderSn;
    }
    /**
     * 设置：关联服务名称
     */
    public void setServeInfoName(String serveInfoName) {
        this.serveInfoName = serveInfoName;
    }

    /**
     * 获取：关联服务名称
     */
    public String getServeInfoName() {
        return serveInfoName;
    }
}
