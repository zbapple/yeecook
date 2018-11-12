package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_activation_card
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-28 00:48:59
 */
public class ActivationCardEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //服务类型id
    private Integer serveTypeId;
    //配送地址id
    private Integer addressId;
    //用户id
    private Integer userId;
    //激活状态：0未激活，1已激活
    private Integer activated;
    //服务总次数
    private Integer serveCount;
    //已服务次数
    private Integer haveServeCount;
    //配送规则
    private String deliveryrules;
    //服务有效时间
    private Date servevalidtime;
    //上次服务时间
    private Date serveLastTime;
    //下次服务时间
    private Date serveNextTime;

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
     * 设置：服务类型id
     */
    public void setServeTypeId(Integer serveTypeId) {
        this.serveTypeId = serveTypeId;
    }

    /**
     * 获取：服务类型id
     */
    public Integer getServeTypeId() {
        return serveTypeId;
    }
    /**
     * 设置：配送地址id
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * 获取：配送地址id
     */
    public Integer getAddressId() {
        return addressId;
    }
    /**
     * 设置：用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * 设置：激活状态：0未激活，1已激活
     */
    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    /**
     * 获取：激活状态：0未激活，1已激活
     */
    public Integer getActivated() {
        return activated;
    }
    /**
     * 设置：服务总次数
     */
    public void setServeCount(Integer serveCount) {
        this.serveCount = serveCount;
    }

    /**
     * 获取：服务总次数
     */
    public Integer getServeCount() {
        return serveCount;
    }
    /**
     * 设置：已服务次数
     */
    public void setHaveServeCount(Integer haveServeCount) {
        this.haveServeCount = haveServeCount;
    }

    /**
     * 获取：已服务次数
     */
    public Integer getHaveServeCount() {
        return haveServeCount;
    }
    /**
     * 设置：配送规则
     */
    public void setDeliveryrules(String deliveryrules) {
        this.deliveryrules = deliveryrules;
    }

    /**
     * 获取：配送规则
     */
    public String getDeliveryrules() {
        return deliveryrules;
    }
    /**
     * 设置：服务有效时间
     */
    public void setServevalidtime(Date servevalidtime) {
        this.servevalidtime = servevalidtime;
    }

    /**
     * 获取：服务有效时间
     */
    public Date getServevalidtime() {
        return servevalidtime;
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
}
