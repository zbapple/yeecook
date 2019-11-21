package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 商品对应规格表值表
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:40
 */
public class GoodsSpecificationVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //商品
    private Integer goods_id;
    //规范Id
    private Integer specification_id;
    //规范说明
    private String value;
    //规格名称
    private String name;
    //规范图片
    private String pic_url;
    //规格数量
    private Integer specificationnumber;
    //规格价格
    private BigDecimal specificationPrice;
    //
    private Integer isDefault;
    //是否售卖
    private Integer isSale;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(Integer specification_id) {
        this.specification_id = specification_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpecificationnumber() {
        return specificationnumber;
    }

    public void setSpecificationnumber(Integer specificationnumber) {
        this.specificationnumber = specificationnumber;
    }

    public BigDecimal getSpecificationPrice() {
        return specificationPrice;
    }

    public void setSpecificationPrice(BigDecimal specificationPrice) {
        this.specificationPrice = specificationPrice;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }
}
