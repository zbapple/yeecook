package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 test1
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-04-03 15:20:28
 */
public class Test1Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    //姓名
    private String name;
    //
    private Integer id;

    /**
     * 设置：姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：姓名
     */
    public String getName() {
        return name;
    }
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
}
