package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 new_nutrients
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 14:49:30
 */
public class NewNutrientsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Integer id;
    //营养成分名
    private String nutrientsName;
    //营养成分数量
    private Integer nutrientsNum;
    //单位
    private String nunit;
    //营养成分对应食物
    private String nutrientsFood;
    //营养成分对应id
    private Integer nutrientsId;

    /**
     * 设置：id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：营养成分名
     */
    public void setNutrientsName(String nutrientsName) {
        this.nutrientsName = nutrientsName;
    }

    /**
     * 获取：营养成分名
     */
    public String getNutrientsName() {
        return nutrientsName;
    }
    /**
     * 设置：营养成分数量
     */
    public void setNutrientsNum(Integer nutrientsNum) {
        this.nutrientsNum = nutrientsNum;
    }

    /**
     * 获取：营养成分数量
     */
    public Integer getNutrientsNum() {
        return nutrientsNum;
    }
    /**
     * 设置：单位
     */
    public void setNunit(String nunit) {
        this.nunit = nunit;
    }

    /**
     * 获取：单位
     */
    public String getNunit() {
        return nunit;
    }
    /**
     * 设置：营养成分对应食物
     */
    public void setNutrientsFood(String nutrientsFood) {
        this.nutrientsFood = nutrientsFood;
    }

    /**
     * 获取：营养成分对应食物
     */
    public String getNutrientsFood() {
        return nutrientsFood;
    }
    /**
     * 设置：营养成分对应id
     */
    public void setNutrientsId(Integer nutrientsId) {
        this.nutrientsId = nutrientsId;
    }

    /**
     * 获取：营养成分对应id
     */
    public Integer getNutrientsId() {
        return nutrientsId;
    }
}
