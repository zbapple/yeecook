package com.platform.charles.xcf;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 xcf_charles_info
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-12-07 00:47:39
 */
public class XcfCharlesInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //课程
    private String course;
    //售价
    private String price;
    //销量
    private Long sales;
    //讲师
    private String lecturer;
    //抓取时间
    private Date addtime;
    //年
    private Integer years;
    //月
    private Integer month;
    //天
    private Integer day;
    //周
    private Integer weeks;

    /**
     * 设置：主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：课程
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * 获取：课程
     */
    public String getCourse() {
        return course;
    }
    /**
     * 设置：售价
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 获取：售价
     */
    public String getPrice() {
        return price;
    }
    /**
     * 设置：销量
     */
    public void setSales(Long sales) {
        this.sales = sales;
    }

    /**
     * 获取：销量
     */
    public Long getSales() {
        return sales;
    }
    /**
     * 设置：讲师
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * 获取：讲师
     */
    public String getLecturer() {
        return lecturer;
    }
    /**
     * 设置：抓取时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取：抓取时间
     */
    public Date getAddtime() {
        return addtime;
    }
    /**
     * 设置：年
     */
    public void setYears(Integer years) {
        this.years = years;
    }

    /**
     * 获取：年
     */
    public Integer getYears() {
        return years;
    }
    /**
     * 设置：月
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * 获取：月
     */
    public Integer getMonth() {
        return month;
    }
    /**
     * 设置：天
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * 获取：天
     */
    public Integer getDay() {
        return day;
    }
    /**
     * 设置：周
     */
    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    /**
     * 获取：周
     */
    public Integer getWeeks() {
        return weeks;
    }
}
