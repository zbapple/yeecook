package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_serve_type
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-23 14:50:08
 */
public class ServeTypeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //名称
    private String name;
    //描述
    private String desc;
    //关联商品id
    private Integer goodsId;
    //关联商品名称
    private String goodsName;

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
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取：描述
     */
    public String getDesc() {
        return desc;
    }
    /**
     * 设置：关联商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：关联商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }
    /**
     * 设置：关联商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：关联商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }
}
