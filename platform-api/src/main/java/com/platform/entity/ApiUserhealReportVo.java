package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApiUserhealReportVo {

    private Date  time;
    private  Integer count;
    private List<UserHealthReportVo> healportlistmap;

    @JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserHealthReportVo> getHealportlistmap() {
        return healportlistmap;
    }

    public void setHealportlistmap(List<UserHealthReportVo> healportlistmap) {
        this.healportlistmap = healportlistmap;
    }
}
