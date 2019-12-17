package com.platform.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.utils.JsonDateSerializer;

import java.io.Serializable;
import java.util.List;

public class ApiFoodtprintVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long time;
    private List<FootprintVo> footprintVoList;
    private List listinfo;

    public List<FootprintVo> getFootprintVoList() {
        return footprintVoList;
    }

    public void setFootprintVoList(List<FootprintVo> footprintVoList) {
        this.footprintVoList = footprintVoList;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public List getListinfo() {
        return listinfo;
    }

    public void setListinfo(List listinfo) {
        this.listinfo = listinfo;
    }
}
