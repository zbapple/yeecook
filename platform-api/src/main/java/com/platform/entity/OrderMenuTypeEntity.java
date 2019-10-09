package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_order_menu_type
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-23 11:55:56
 */
public class OrderMenuTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //父子关联
    private Integer parentId;
    //类型名称
    private String typeName;
    //门店id
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
     * 设置：父子关联
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父子关联
     */
    public Integer getParentId() {
        return parentId;
    }
    /**
     * 设置：类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取：类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    public Integer getStroeid() {
        return stroeid;
    }

    public void setStroeid(Integer stroeid) {
        this.stroeid = stroeid;
    }
}
