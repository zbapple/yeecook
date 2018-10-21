package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_serve_type
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-21 01:13:48
 */
public class ServeTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //名称
    private String name;
    //描述
    private String desc;

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
}
