package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_activation_card
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-21 01:13:48
 */
public class ActivationCardEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //激活时间
    private Date activationTime;
    //失效时间
    private Date disabledTime;
    //激活IP地址
    private String activationIp;
    //激活终端
    private String activation terminal;
    //激活用户id
    private Integer userId;
    //激活码
    private String activationCode;
    //是否激活：0未激活，1已激活
    private String isActivation;
    //上次服务时间
    private Date serveLastTime;
    //下次服务时间
    private Date serveNextTime;
    //已服务次数
    private Integer haveServe count;
    //激活订单编号
    private String orderSn;
    //关联服务id
    private Integer serveId;

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
     * 设置：失效时间
     */
    public void setDisabledTime(Date disabledTime) {
        this.disabledTime = disabledTime;
    }

    /**
     * 获取：失效时间
     */
    public Date getDisabledTime() {
        return disabledTime;
    }
    /**
     * 设置：激活IP地址
     */
    public void setActivationIp(String activationIp) {
        this.activationIp = activationIp;
    }

    /**
     * 获取：激活IP地址
     */
    public String getActivationIp() {
        return activationIp;
    }
    /**
     * 设置：激活终端
     */
    public void setActivation terminal(String activation terminal) {
        this.activation terminal = activation terminal;
    }

    /**
     * 获取：激活终端
     */
    public String getActivation terminal() {
        return activation terminal;
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
     * 设置：是否激活：0未激活，1已激活
     */
    public void setIsActivation(String isActivation) {
        this.isActivation = isActivation;
    }

    /**
     * 获取：是否激活：0未激活，1已激活
     */
    public String getIsActivation() {
        return isActivation;
    }
    /**
     * 设置：上次服务时间
     */
    public void setServeLastTime(Date serveLastTime) {
        this.serveLastTime = serveLastTime;
    }

    /**
     * 获取：上次服务时间
     */
    public Date getServeLastTime() {
        return serveLastTime;
    }
    /**
     * 设置：下次服务时间
     */
    public void setServeNextTime(Date serveNextTime) {
        this.serveNextTime = serveNextTime;
    }

    /**
     * 获取：下次服务时间
     */
    public Date getServeNextTime() {
        return serveNextTime;
    }
    /**
     * 设置：已服务次数
     */
    public void setHaveServe count(Integer haveServe count) {
        this.haveServe count = haveServe count;
    }

    /**
     * 获取：已服务次数
     */
    public Integer getHaveServe count() {
        return haveServe count;
    }
    /**
     * 设置：激活订单编号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取：激活订单编号
     */
    public String getOrderSn() {
        return orderSn;
    }
    /**
     * 设置：关联服务id
     */
    public void setServeId(Integer serveId) {
        this.serveId = serveId;
    }

    /**
     * 获取：关联服务id
     */
    public Integer getServeId() {
        return serveId;
    }
}
