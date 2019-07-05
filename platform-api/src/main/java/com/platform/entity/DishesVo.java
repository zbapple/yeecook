package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜品表
 id
 菜品名称
 菜品封面图片
 菜品卡路里实体
 * 表名 dishes
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:40
 */
public class DishesVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //菜品名字
    private String dishesName;
    //菜品封面图片
    private String dishesCoverPic;
    //菜品卡路里
    private String dishesCalories;

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
     * 设置：菜品名字
     */
    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    /**
     * 获取：菜品名字
     */
    public String getDishesName() {
        return dishesName;
    }
    /**
     * 设置：菜品封面图片
     */
    public void setDishesCoverPic(String dishesCoverPic) {
        this.dishesCoverPic = dishesCoverPic;
    }

    /**
     * 获取：菜品封面图片
     */
    public String getDishesCoverPic() {
        return dishesCoverPic;
    }
    /**
     * 设置：菜品卡路里
     */
    public void setDishesCalories(String dishesCalories) {
        this.dishesCalories = dishesCalories;
    }

    /**
     * 获取：菜品卡路里
     */
    public String getDishesCalories() {
        return dishesCalories;
    }
}
