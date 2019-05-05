package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 supplie_info
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
public class SupplieInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //商家图像
    private String supplieImg;
    //商家名称
    private String supplieTile;
    //商家描述
    private String supplieDesc;
    //商家介绍
    private String supplieInfo;

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
     * 设置：商家图像
     */
    public void setSupplieImg(String supplieImg) {
        this.supplieImg = supplieImg;
    }

    /**
     * 获取：商家图像
     */
    public String getSupplieImg() {
        return supplieImg;
    }
    /**
     * 设置：商家名称
     */
    public void setSupplieTile(String supplieTile) {
        this.supplieTile = supplieTile;
    }

    /**
     * 获取：商家名称
     */
    public String getSupplieTile() {
        return supplieTile;
    }
    /**
     * 设置：商家描述
     */
    public void setSupplieDesc(String supplieDesc) {
        this.supplieDesc = supplieDesc;
    }

    /**
     * 获取：商家描述
     */
    public String getSupplieDesc() {
        return supplieDesc;
    }
    /**
     * 设置：商家介绍
     */
    public void setSupplieInfo(String supplieInfo) {
        this.supplieInfo = supplieInfo;
    }

    /**
     * 获取：商家介绍
     */
    public String getSupplieInfo() {
        return supplieInfo;
    }
}
