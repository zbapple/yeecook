package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_activation_generator
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-28 00:48:59
 */
public class ActivationGeneratorEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //关联服务id
    private Integer serveInfoId;
    //生成激活码数量
    private Integer count;
    //生成激活码秘钥
    private String password;
    //分组id
    private Integer groupId;
    //激活码生成长度
    private Integer length;

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
     * 设置：关联服务id
     */
    public void setServeInfoId(Integer serveInfoId) {
        this.serveInfoId = serveInfoId;
    }

    /**
     * 获取：关联服务id
     */
    public Integer getServeInfoId() {
        return serveInfoId;
    }
    /**
     * 设置：生成激活码数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取：生成激活码数量
     */
    public Integer getCount() {
        return count;
    }
    /**
     * 设置：生成激活码秘钥
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：生成激活码秘钥
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：分组id
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取：分组id
     */
    public Integer getGroupId() {
        return groupId;
    }
    /**
     * 设置：激活码生成长度
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * 获取：激活码生成长度
     */
    public Integer getLength() {
        return length;
    }
}
