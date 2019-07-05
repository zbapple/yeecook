package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 云课堂商品关联表
 id
 课件ID
 商品id实体
 * 表名 cloud_classroom_good
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:08:17
 */
public class CloudClassroomGoodVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Integer id;
    //课件id
    private Integer videoId;
    //商品id
    private Integer goodsId;
    //商品列表图
    private String listpicurl;
    //零售价格(现价)
    private BigDecimal retailprice;
    //专柜价格
    private BigDecimal counterprice;
    //商品名称
    private  String name;

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
     * 设置：课件id
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取：课件id
     */
    public Integer getVideoId() {
        return videoId;
    }
    /**
     * 设置：商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    public String getListpicurl() {
        return listpicurl;
    }

    public void setListpicurl(String listpicurl) {
        this.listpicurl = listpicurl;
    }

    public BigDecimal getRetailprice() {
        return retailprice;
    }

    public void setRetailprice(BigDecimal retailprice) {
        this.retailprice = retailprice;
    }

    public BigDecimal getCounterprice() {
        return counterprice;
    }

    public void setCounterprice(BigDecimal counterprice) {
        this.counterprice = counterprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
