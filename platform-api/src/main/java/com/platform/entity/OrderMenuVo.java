package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_order_menu
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-28 16:59:53
 */
public class OrderMenuVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //订单Id
    private Integer orderId;
    //套餐id
    private Integer mealId;
    //套餐名称
    private String mealname;
    //商品序列号
    private String goodsSn;
    //产品id
    private Integer productId;
    //市场价格
    private BigDecimal marketPrice;
    //零售价格
    private BigDecimal retailPrice;
    //商品规格
    private String goodsSpecifitionNameValue;
    //门店id
    private Integer storeid;
    //数量
    private Integer number;
    //价格
    private BigDecimal price;
    //虚拟商品
    private Integer isReal;
    //商品规格Ids
    private String goodsSpecifitionIds;
    //图片链接
    private String listPicUrl;
    //
    private String yqm;
    //订单类型
    private Integer orderType;

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
     * 设置：订单Id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：订单Id
     */
    public Integer getOrderId() {
        return orderId;
    }
    /**
     * 设置：套餐id
     */
    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    /**
     * 获取：套餐id
     */
    public Integer getMealId() {
        return mealId;
    }

    /**
     * 设置：商品序列号
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    /**
     * 获取：商品序列号
     */
    public String getGoodsSn() {
        return goodsSn;
    }
    /**
     * 设置：产品id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取：产品id
     */
    public Integer getProductId() {
        return productId;
    }
    /**
     * 设置：市场价格
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取：市场价格
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }
    /**
     * 设置：零售价格
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 获取：零售价格
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }
    /**
     * 设置：商品规格
     */
    public void setGoodsSpecifitionNameValue(String goodsSpecifitionNameValue) {
        this.goodsSpecifitionNameValue = goodsSpecifitionNameValue;
    }

    /**
     * 获取：商品规格
     */
    public String getGoodsSpecifitionNameValue() {
        return goodsSpecifitionNameValue;
    }
    /**
     * 设置：门店id
     */
    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    /**
     * 获取：门店id
     */
    public Integer getStoreid() {
        return storeid;
    }
    /**
     * 设置：数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取：数量
     */
    public Integer getNumber() {
        return number;
    }
    /**
     * 设置：价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：价格
     */
    public BigDecimal getPrice() {
        return price;
    }
    /**
     * 设置：虚拟商品
     */
    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    /**
     * 获取：虚拟商品
     */
    public Integer getIsReal() {
        return isReal;
    }
    /**
     * 设置：商品规格Ids
     */
    public void setGoodsSpecifitionIds(String goodsSpecifitionIds) {
        this.goodsSpecifitionIds = goodsSpecifitionIds;
    }

    /**
     * 获取：商品规格Ids
     */
    public String getGoodsSpecifitionIds() {
        return goodsSpecifitionIds;
    }
    /**
     * 设置：图片链接
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    /**
     * 获取：图片链接
     */
    public String getListPicUrl() {
        return listPicUrl;
    }
    /**
     * 设置：
     */
    public void setYqm(String yqm) {
        this.yqm = yqm;
    }

    /**
     * 获取：
     */
    public String getYqm() {
        return yqm;
    }
    /**
     * 设置：订单类型
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取：订单类型
     */
    public Integer getOrderType() {
        return orderType;
    }

    public String getMealname() {
        return mealname;
    }

    public void setMealname(String mealname) {
        this.mealname = mealname;
    }
}
