package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 menu
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-07 23:34:58
 */
public class MenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //种类名称
    private String categoryName;
    //菜谱名
    private String menuName;
    //菜谱食材简介
    private String menuBrief;
    //菜谱图片
    private String menuPicUrl;
    //创建人
    private String user;
    //菜谱下载
    private String menuStrUrl;
    //烹调方式
    private String cookMethod;
    //烹调操作图片
    private String cookPicUrl;
    //烹调辅助工具
    private String auxiliaryMeans;
    //配料
    private String burden;
    //下载烹饪程序
    private String menuDownloader;

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
     * 设置：种类名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取：种类名称
     */
    public String getCategoryName() {
        return categoryName;
    }
    /**
     * 设置：菜谱名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取：菜谱名
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * 设置：菜谱食材简介
     */
    public void setMenuBrief(String menuBrief) {
        this.menuBrief = menuBrief;
    }

    /**
     * 获取：菜谱食材简介
     */
    public String getMenuBrief() {
        return menuBrief;
    }
    /**
     * 设置：菜谱图片
     */
    public void setMenuPicUrl(String menuPicUrl) {
        this.menuPicUrl = menuPicUrl;
    }

    /**
     * 获取：菜谱图片
     */
    public String getMenuPicUrl() {
        return menuPicUrl;
    }
    /**
     * 设置：创建人
     */
    public  void setUser(String User){this.user=User;}
    /**
     * 获取：创建人
     */
    public String getUser(){return user;}
    /**
     * 设置：菜谱下载
     */
    public void setMenuStrUrl(String menuStrUrl) {
        this.menuStrUrl = menuStrUrl;
    }

    /**
     * 获取：菜谱下载
     */
    public String getMenuStrUrl() {
        return menuStrUrl;
    }
    /**
     * 设置：烹调方式
     */
    public void setCookMethod(String cookMethod) {
        this.cookMethod = cookMethod;
    }

    /**
     * 获取：烹调方式
     */
    public String getCookMethod() {
        return cookMethod;
    }
    /**
     * 设置：烹调操作图片
     */
    public void setCookPicUrl(String cookPicUrl) {
        this.cookPicUrl = cookPicUrl;
    }

    /**
     * 获取：烹调操作图片
     */
    public String getCookPicUrl() {
        return cookPicUrl;
    }
    /**
     * 设置：烹调辅助工具
     */
    public void setAuxiliaryMeans(String auxiliaryMeans) {
        this.auxiliaryMeans = auxiliaryMeans;
    }

    /**
     * 获取：烹调辅助工具
     */
    public String getAuxiliaryMeans() {
        return auxiliaryMeans;
    }
    /**
     * 设置：配料
     */
    public void setBurden(String burden) {
        this.burden = burden;
    }

    /**
     * 获取：配料
     */
    public String getBurden() {
        return burden;
    }
    /**
     * 设置：下载烹饪程序
     */
    public void setMenuDownloader(String menuDownloader) {
        this.menuDownloader = menuDownloader;
    }

    /**
     * 获取：下载烹饪程序
     */
    public String getMenuDownloader() {
        return menuDownloader;
    }
}
