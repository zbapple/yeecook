package com.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_activation_card
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-23 14:50:08
 */
public class ActivationCardVo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Integer ACTIVATED_OPEN=1;
    public static final Integer ACTIVATED_CLOSE=0;
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
    //地址信息
    @Getter@Setter
    private AddressVo addressVo;

    /**
     * 设置：服务总时间，下次服务时间
     */
    public void countServeTime(ActivationCardVo activationCardVo){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int serveCount=activationCardVo.getServeCount(); //总服务次数
        int haveServeCount=activationCardVo.getHaveServeCount(); //已服务次数
        String deliveryRules=activationCardVo.getDeliveryrules();//配送规则
        //计算有效服务日期
        switch (deliveryRules){
            //每月一号
            case "1":
                if(day>1){
                    month=month+1+serveCount-haveServeCount;
                }else {
                    month=month+serveCount-haveServeCount;
                }
                day = 1;

                calendar.clear();
                calendar.set(year,month,day);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                activationCardVo.setServevalidtime(calendar.getTime());
                //计算下次服务时间
                calendar.clear();
                calendar.set(year,month-(serveCount-haveServeCount),day);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                activationCardVo.setServeNextTime(calendar.getTime());
                break;
            //每月中旬
            case "2":
                if(day>15){
                    month=month+1+serveCount-haveServeCount;
                }else {
                    month=month+serveCount-haveServeCount;
                }
                day=15;

                calendar.clear();
                calendar.set(year,month,day);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                activationCardVo.setServevalidtime(calendar.getTime());
                //计算下次服务时间
                calendar.clear();
                calendar.set(year,month-(serveCount-haveServeCount),day);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                activationCardVo.setServeNextTime(calendar.getTime());
                break;
            //每月月底
            case "3":


                month=month+serveCount-haveServeCount;

                calendar.clear();
                calendar.set(year,month,day);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                activationCardVo.setServevalidtime(calendar.getTime());
                //计算下次服务时间
                calendar.clear();
                calendar.set(year,month-(serveCount-haveServeCount),day);
                int lastDay=  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                calendar.set(Calendar.DAY_OF_MONTH,lastDay+1);
                activationCardVo.setServeNextTime(calendar.getTime());
                break;
        }


    }

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
