package com.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_serve_info
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-23 14:50:08
 */
public class ServeInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //服务项目名称
    private String name;
    //服务类型id
    private Integer type;
    //服务规则描述
    private String desc;
    //激活有效期
    private Date activationValidity;
    //总服务次数
    private Integer serveCount;
    //服务有效期(天)
    private Integer serveValidity;
    //自身关联商品id
    private Integer productId;
    //服务关联商品集合
    private String serveProductIds;
    //新增时间
    private Date addTime;
    //修改时间
    private Date updataTime;
    //操作用户id
    private Integer userId;
    //价格
    @Getter@Setter
    private String price;
    //图片
    @Getter@Setter
    private String imgUrl;
    @Getter@Setter //服务关联的商品规格描述
    private String productDesc;

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
     * 设置：服务项目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：服务项目名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：服务类型id
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：服务类型id
     */
    public Integer getType() {
        return type;
    }
    /**
     * 设置：服务规则描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取：服务规则描述
     */
    public String getDesc() {
        return desc;
    }
    /**
     * 设置：激活有效期
     */
    public void setActivationValidity(Date activationValidity) {
        this.activationValidity = activationValidity;
    }

    /**
     * 获取：激活有效期
     */
    public Date getActivationValidity() {
        return activationValidity;
    }
    /**
     * 设置：总服务次数
     */
    public void setServeCount(Integer serveCount) {
        this.serveCount = serveCount;
    }

    /**
     * 获取：总服务次数
     */
    public Integer getServeCount() {
        return serveCount;
    }
    /**
     * 设置：服务有效期(天)
     */
    public void setServeValidity(Integer serveValidity) {
        this.serveValidity = serveValidity;
    }

    /**
     * 获取：服务有效期(天)
     */
    public Integer getServeValidity() {
        return serveValidity;
    }
    /**
     * 设置：自身关联商品id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取：自身关联商品id
     */
    public Integer getProductId() {
        return productId;
    }
    /**
     * 设置：服务关联商品集合
     */
    public void setServeProductIds(String serveProductIds) {
        this.serveProductIds = serveProductIds;
    }

    /**
     * 获取：服务关联商品集合
     */
    public String getServeProductIds() {
        return serveProductIds;
    }
    /**
     * 设置：新增时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：新增时间
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * 设置：修改时间
     */
    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdataTime() {
        return updataTime;
    }
    /**
     * 设置：操作用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：操作用户id
     */
    public Integer getUserId() {
        return userId;
    }
}
