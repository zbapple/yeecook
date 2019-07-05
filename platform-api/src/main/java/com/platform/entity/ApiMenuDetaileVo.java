package com.platform.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApiMenuDetaileVo {


    private  Date    time;
    private  Double sumcal;
    private  String menuType;
    private List<Map>  zhengcan;
    private List<Map> jiacan;



    public Double getSumcal() {
        return sumcal;
    }

    public void setSumcal(Double sumcal) {
        this.sumcal = sumcal;
    }

    public List<Map> getJiacan() {
        return jiacan;
    }

    public void setJiacan(List<Map> jiacan) {
        this.jiacan = jiacan;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public List<Map> getZhengcan() {
        return zhengcan;
    }

    public void setZhengcan(List<Map> zhengcan) {
        this.zhengcan = zhengcan;
    }
}
