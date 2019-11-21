package com.platform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体
 * 表名 menutype
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-27 16:50:29
 */
public class MenutypeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //套餐类型
    private String menuTypename;
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
     * 设置：套餐类型
     */
    public void setMenuTypename(String menuTypename) {
        this.menuTypename = menuTypename;
    }

    /**
     * 获取：套餐类型
     */
    public String getMenuTypename() {
        return menuTypename;
    }
    /**
     * 设置：门店id
     */
    public void setStroeid(Integer stroeid) {
        this.stroeid = stroeid;
    }

    /**
     * 获取：门店id
     */
    public Integer getStroeid() {
        return stroeid;
    }

}
