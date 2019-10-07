package com.platform.entity;

import java.io.Serializable;

public class BuMealVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer mealid;
    private Integer stroeid;
    private String name;
    private Integer number;

    public Integer getMealid() {
        return mealid;
    }

    public void setMealid(Integer mealid) {
        this.mealid = mealid;
    }

    public Integer getStroeid() {
        return stroeid;
    }

    public void setStroeid(Integer stroeid) {
        this.stroeid = stroeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
