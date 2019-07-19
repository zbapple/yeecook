package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户检测周期表
实体
 * 表名 user_detection_cycle
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:17:10
 */
@Data
public class UserDetectionCycleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //检测开始日期
    private Date inspectionStartDate;
    //检测结束日期
    private Date inspectionEndDate;
    //已检测次数
    private Integer inspectionNum;
    //检测执行日期
    private Date inspectionTime;
    //检测周期
    private String inspectionCycle;
    //用户id
    private Integer nideshopUserId;
    //下次检测时间
    private Date nextTime;

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
     * 设置：检测开始日期
     */
    public void setInspectionStartDate(Date inspectionStartDate) {
        this.inspectionStartDate = inspectionStartDate;
    }

    /**
     * 获取：检测开始日期
     */
    public Date getInspectionStartDate() {
        return inspectionStartDate;
    }
    /**
     * 设置：检测结束日期
     */
    public void setInspectionEndDate(Date inspectionEndDate) {
        this.inspectionEndDate = inspectionEndDate;
    }

    /**
     * 获取：检测结束日期
     */
    public Date getInspectionEndDate() {
        return inspectionEndDate;
    }
    /**
     * 设置：已检测次数
     */
    public void setInspectionNum(Integer inspectionNum) {
        this.inspectionNum = inspectionNum;
    }

    /**
     * 获取：已检测次数
     */
    public Integer getInspectionNum() {
        return inspectionNum;
    }
    /**
     * 设置：检测执行日期
     */
    public void setInspectionTime(Date inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    /**
     * 获取：检测执行日期
     */
    public Date getInspectionTime() {
        return inspectionTime;
    }
    /**
     * 设置：检测周期
     */
    public void setInspectionCycle(String inspectionCycle) {
        this.inspectionCycle = inspectionCycle;
    }

    /**
     * 获取：检测周期
     */
    public String getInspectionCycle() {
        return inspectionCycle;
    }
    /**
     * 设置：用户id
     */
    public void setNideshopUserId(Integer nideshopUserId) {
        this.nideshopUserId = nideshopUserId;
    }

    /**
     * 获取：用户id
     */
    public Integer getNideshopUserId() {
        return nideshopUserId;
    }
}
