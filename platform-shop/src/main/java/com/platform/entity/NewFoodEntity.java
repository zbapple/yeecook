package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 new_food
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
public class NewFoodEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //菜名称
    private String name;
    //视频URL
    private String voidUrl;
    //标题一
    private String tile1;
    //标题一备注
    private String tile1Remark;
    //研发大师图像
    private String yfdsPic;
    //研发大师名称
    private String yfdsName;
    //研发大师描述
    private String yfdsDesc;
    //观看次数
    private Integer plays;
    //供应商图像
    private String supplieImg;
    //供应商标题一
    private String supplieTile;
    //供应商标题描述
    private String supplieDesc;
    //供应商介绍
    private String supplieInfo;
    //课程简介
    private String tile2;
    //课程简介内容
    private String tile2Remark;
    //课程福利
    private String tile3;
    //课程内容
    private String tile3Remark;
    //底部图片LOG
    private String log;
    //底部介绍
    private String logRemark;
    //食材图片
    private String foodImg;
    //食材标题
    private String foodTile;
    //食材描述
    private String foodRemark;
    //应用菜式
    private String yycs;
    //应用菜式内容
    private String yycsRemark;
    //食材申请标题一
    private String foodSqTile1;
    //食材申请标题二
    private String foodSqTile2;
    //食材供应商图片
    private String foodSupImg;
    //食材供应商标题
    private String foodSupTile;
    //食材供应商描述
    private String foodSupRemark;
    //食材功能供应商介绍
    private String foodSupInfo;
    //页面URL地址
    private String url;
    //食材id
    private Integer foodId;
    //研发大师id
    private Integer yfdsInfo;
    //商家id
    private Integer supplieId;
    //食材页面url
    private String foodUrl;
    //供应商介绍url
    private String supplieUrl;

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
     * 设置：菜名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：菜名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：视频URL
     */
    public void setVoidUrl(String voidUrl) {
        this.voidUrl = voidUrl;
    }

    /**
     * 获取：视频URL
     */
    public String getVoidUrl() {
        return voidUrl;
    }
    /**
     * 设置：标题一
     */
    public void setTile1(String tile1) {
        this.tile1 = tile1;
    }

    /**
     * 获取：标题一
     */
    public String getTile1() {
        return tile1;
    }
    /**
     * 设置：标题一备注
     */
    public void setTile1Remark(String tile1Remark) {
        this.tile1Remark = tile1Remark;
    }

    /**
     * 获取：标题一备注
     */
    public String getTile1Remark() {
        return tile1Remark;
    }
    /**
     * 设置：研发大师图像
     */
    public void setYfdsPic(String yfdsPic) {
        this.yfdsPic = yfdsPic;
    }

    /**
     * 获取：研发大师图像
     */
    public String getYfdsPic() {
        return yfdsPic;
    }
    /**
     * 设置：研发大师名称
     */
    public void setYfdsName(String yfdsName) {
        this.yfdsName = yfdsName;
    }

    /**
     * 获取：研发大师名称
     */
    public String getYfdsName() {
        return yfdsName;
    }
    /**
     * 设置：研发大师描述
     */
    public void setYfdsDesc(String yfdsDesc) {
        this.yfdsDesc = yfdsDesc;
    }

    /**
     * 获取：研发大师描述
     */
    public String getYfdsDesc() {
        return yfdsDesc;
    }
    /**
     * 设置：观看次数
     */
    public void setPlays(Integer plays) {
        this.plays = plays;
    }

    /**
     * 获取：观看次数
     */
    public Integer getPlays() {
        return plays;
    }
    /**
     * 设置：供应商图像
     */
    public void setSupplieImg(String supplieImg) {
        this.supplieImg = supplieImg;
    }

    /**
     * 获取：供应商图像
     */
    public String getSupplieImg() {
        return supplieImg;
    }
    /**
     * 设置：供应商标题一
     */
    public void setSupplieTile(String supplieTile) {
        this.supplieTile = supplieTile;
    }

    /**
     * 获取：供应商标题一
     */
    public String getSupplieTile() {
        return supplieTile;
    }
    /**
     * 设置：供应商标题描述
     */
    public void setSupplieDesc(String supplieDesc) {
        this.supplieDesc = supplieDesc;
    }

    /**
     * 获取：供应商标题描述
     */
    public String getSupplieDesc() {
        return supplieDesc;
    }
    /**
     * 设置：供应商介绍
     */
    public void setSupplieInfo(String supplieInfo) {
        this.supplieInfo = supplieInfo;
    }

    /**
     * 获取：供应商介绍
     */
    public String getSupplieInfo() {
        return supplieInfo;
    }
    /**
     * 设置：课程简介
     */
    public void setTile2(String tile2) {
        this.tile2 = tile2;
    }

    /**
     * 获取：课程简介
     */
    public String getTile2() {
        return tile2;
    }
    /**
     * 设置：课程简介内容
     */
    public void setTile2Remark(String tile2Remark) {
        this.tile2Remark = tile2Remark;
    }

    /**
     * 获取：课程简介内容
     */
    public String getTile2Remark() {
        return tile2Remark;
    }
    /**
     * 设置：课程福利
     */
    public void setTile3(String tile3) {
        this.tile3 = tile3;
    }

    /**
     * 获取：课程福利
     */
    public String getTile3() {
        return tile3;
    }
    /**
     * 设置：课程内容
     */
    public void setTile3Remark(String tile3Remark) {
        this.tile3Remark = tile3Remark;
    }

    /**
     * 获取：课程内容
     */
    public String getTile3Remark() {
        return tile3Remark;
    }
    /**
     * 设置：底部图片LOG
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * 获取：底部图片LOG
     */
    public String getLog() {
        return log;
    }
    /**
     * 设置：底部介绍
     */
    public void setLogRemark(String logRemark) {
        this.logRemark = logRemark;
    }

    /**
     * 获取：底部介绍
     */
    public String getLogRemark() {
        return logRemark;
    }
    /**
     * 设置：食材图片
     */
    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    /**
     * 获取：食材图片
     */
    public String getFoodImg() {
        return foodImg;
    }
    /**
     * 设置：食材标题
     */
    public void setFoodTile(String foodTile) {
        this.foodTile = foodTile;
    }

    /**
     * 获取：食材标题
     */
    public String getFoodTile() {
        return foodTile;
    }
    /**
     * 设置：食材描述
     */
    public void setFoodRemark(String foodRemark) {
        this.foodRemark = foodRemark;
    }

    /**
     * 获取：食材描述
     */
    public String getFoodRemark() {
        return foodRemark;
    }
    /**
     * 设置：应用菜式
     */
    public void setYycs(String yycs) {
        this.yycs = yycs;
    }

    /**
     * 获取：应用菜式
     */
    public String getYycs() {
        return yycs;
    }
    /**
     * 设置：应用菜式内容
     */
    public void setYycsRemark(String yycsRemark) {
        this.yycsRemark = yycsRemark;
    }

    /**
     * 获取：应用菜式内容
     */
    public String getYycsRemark() {
        return yycsRemark;
    }
    /**
     * 设置：食材申请标题一
     */
    public void setFoodSqTile1(String foodSqTile1) {
        this.foodSqTile1 = foodSqTile1;
    }

    /**
     * 获取：食材申请标题一
     */
    public String getFoodSqTile1() {
        return foodSqTile1;
    }
    /**
     * 设置：食材申请标题二
     */
    public void setFoodSqTile2(String foodSqTile2) {
        this.foodSqTile2 = foodSqTile2;
    }

    /**
     * 获取：食材申请标题二
     */
    public String getFoodSqTile2() {
        return foodSqTile2;
    }
    /**
     * 设置：食材供应商图片
     */
    public void setFoodSupImg(String foodSupImg) {
        this.foodSupImg = foodSupImg;
    }

    /**
     * 获取：食材供应商图片
     */
    public String getFoodSupImg() {
        return foodSupImg;
    }
    /**
     * 设置：食材供应商标题
     */
    public void setFoodSupTile(String foodSupTile) {
        this.foodSupTile = foodSupTile;
    }

    /**
     * 获取：食材供应商标题
     */
    public String getFoodSupTile() {
        return foodSupTile;
    }
    /**
     * 设置：食材供应商描述
     */
    public void setFoodSupRemark(String foodSupRemark) {
        this.foodSupRemark = foodSupRemark;
    }

    /**
     * 获取：食材供应商描述
     */
    public String getFoodSupRemark() {
        return foodSupRemark;
    }
    /**
     * 设置：食材功能供应商介绍
     */
    public void setFoodSupInfo(String foodSupInfo) {
        this.foodSupInfo = foodSupInfo;
    }

    /**
     * 获取：食材功能供应商介绍
     */
    public String getFoodSupInfo() {
        return foodSupInfo;
    }
    /**
     * 设置：页面URL地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：页面URL地址
     */
    public String getUrl() {
        return url;
    }
    /**
     * 设置：食材id
     */
    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    /**
     * 获取：食材id
     */
    public Integer getFoodId() {
        return foodId;
    }
    /**
     * 设置：研发大师id
     */
    public void setYfdsInfo(Integer yfdsInfo) {
        this.yfdsInfo = yfdsInfo;
    }

    /**
     * 获取：研发大师id
     */
    public Integer getYfdsInfo() {
        return yfdsInfo;
    }
    /**
     * 设置：商家id
     */
    public void setSupplieId(Integer supplieId) {
        this.supplieId = supplieId;
    }

    /**
     * 获取：商家id
     */
    public Integer getSupplieId() {
        return supplieId;
    }
    /**
     * 设置：食材页面url
     */
    public void setFoodUrl(String foodUrl) {
        this.foodUrl = foodUrl;
    }

    /**
     * 获取：食材页面url
     */
    public String getFoodUrl() {
        return foodUrl;
    }
    /**
     * 设置：供应商介绍url
     */
    public void setSupplieUrl(String supplieUrl) {
        this.supplieUrl = supplieUrl;
    }

    /**
     * 获取：供应商介绍url
     */
    public String getSupplieUrl() {
        return supplieUrl;
    }
}
