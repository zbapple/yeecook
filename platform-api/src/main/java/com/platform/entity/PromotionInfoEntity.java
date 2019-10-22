package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 promotion_info
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-22 17:55:52
 */
public class PromotionInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //推广员id
    private Long parentId;
    //用户id
    private Integer userId;

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
     * 设置：推广员id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：推广员id
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * 设置：用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public Integer getUserId() {
        return userId;
    }
}
