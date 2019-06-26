package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 营养元素表
实体
 * 表名 nutrient_elements
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:22:12
 */
public class NutrientElementsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //营养元素名称
    private String nutrientElementsName;
    //营养元素图片
    private String nutrientElementsPic;
    //单位
    private String unit;
    //营养元素功能描述
    private String functionalDescription;

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
     * 设置：营养元素名称
     */
    public void setNutrientElementsName(String nutrientElementsName) {
        this.nutrientElementsName = nutrientElementsName;
    }

    /**
     * 获取：营养元素名称
     */
    public String getNutrientElementsName() {
        return nutrientElementsName;
    }
    /**
     * 设置：营养元素图片
     */
    public void setNutrientElementsPic(String nutrientElementsPic) {
        this.nutrientElementsPic = nutrientElementsPic;
    }

    /**
     * 获取：营养元素图片
     */
    public String getNutrientElementsPic() {
        return nutrientElementsPic;
    }
    /**
     * 设置：单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取：单位
     */
    public String getUnit() {
        return unit;
    }
    /**
     * 设置：营养元素功能描述
     */
    public void setFunctionalDescription(String functionalDescription) {
        this.functionalDescription = functionalDescription;
    }

    /**
     * 获取：营养元素功能描述
     */
    public String getFunctionalDescription() {
        return functionalDescription;
    }
}
