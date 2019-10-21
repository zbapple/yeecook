package com.platform.entity;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 order_menuplan
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-25 15:35:28
 */
public class OrderMenuplanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //订单编号
    private String menuSn;
    //用户id
    private Long userid;
    //订餐类型(配送、堂吃)
    private String orderType;
    //人数
    private Integer population;
    //预定天数
    private Integer fate;
    //添加时间
    private Date addTime;
    //是否已经打印：0未打印，1已打印
    private Integer isPrinter;
    //备注
    private String remark;
    //规格
    private String specification;
    //送达时间
    private Date deliverytime;
    //商家id
    private Integer stroeid;

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
     * 设置：订单编号
     */
    public void setMenuSn(String menuSn) {
        this.menuSn = menuSn;
    }

    /**
     * 获取：订单编号
     */
    public String getMenuSn() {
        return menuSn;
    }
    /**
     * 设置：用户id
     */

    /**
     * 获取：用户id
     */
    /**
     * 设置：订餐类型(配送、堂吃)
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取：订餐类型(配送、堂吃)
     */
    public String getOrderType() {
        return orderType;
    }
    /**
     * 设置：人数
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

    /**
     * 获取：人数
     */
    public Integer getPopulation() {
        return population;
    }
    /**
     * 设置：预定天数
     */
    public void setFate(Integer fate) {
        this.fate = fate;
    }

    /**
     * 获取：预定天数
     */
    public Integer getFate() {
        return fate;
    }
    /**
     * 设置：添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：添加时间
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * 设置：是否已经打印：0未打印，1已打印
     */
    public void setIsPrinter(Integer isPrinter) {
        this.isPrinter = isPrinter;
    }

    /**
     * 获取：是否已经打印：0未打印，1已打印
     */
    public Integer getIsPrinter() {
        return isPrinter;
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
     * 设置：规格
     */
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /**
     * 获取：规格
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 设置：商家id
     */
    public void setStroeid(Integer stroeid) {
        this.stroeid = stroeid;
    }

    /**
     * 获取：商家id
     */

    public Integer getStroeid() {
        return stroeid;
    }



    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }


    public Date getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(Date deliverytime) {
        this.deliverytime = deliverytime;
    }
}
