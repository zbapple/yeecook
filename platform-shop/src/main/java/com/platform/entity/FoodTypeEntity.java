package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 食材类型表
实体
 * 表名 food_type
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:25:00
 */
public class FoodTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //类型名称
    private String typeName;

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
}
