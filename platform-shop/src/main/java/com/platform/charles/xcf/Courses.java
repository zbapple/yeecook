/**
 * Copyright 2018 bejson.com
 */
package com.platform.charles.xcf;

import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2018-12-07 0:14:8
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Courses {

    private int status;
    private Lecturer lecturer;
    private int total_sales_volume;
    private List<Kinds> kinds;
    private String name;
    private List<Lessons> lessons;
    private boolean is_on_sale;
    private Image image;
    private String duration_text;
    private String rate_str;
    private String url;
    private Double rate;
    private String n_rate_users_str;
    private Date begin_time;
    private String courseware_url;
    private String id;
    private int n_views;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setTotal_sales_volume(int total_sales_volume) {
        this.total_sales_volume = total_sales_volume;
    }

    public int getTotal_sales_volume() {
        return total_sales_volume;
    }

    public void setKinds(List<Kinds> kinds) {
        this.kinds = kinds;
    }

    public List<Kinds> getKinds() {
        return kinds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLessons(List<Lessons> lessons) {
        this.lessons = lessons;
    }

    public List<Lessons> getLessons() {
        return lessons;
    }

    public void setIs_on_sale(boolean is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    public boolean getIs_on_sale() {
        return is_on_sale;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setDuration_text(String duration_text) {
        this.duration_text = duration_text;
    }

    public String getDuration_text() {
        return duration_text;
    }

    public void setRate_str(String rate_str) {
        this.rate_str = rate_str;
    }

    public String getRate_str() {
        return rate_str;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public void setN_rate_users_str(String n_rate_users_str) {
        this.n_rate_users_str = n_rate_users_str;
    }

    public String getN_rate_users_str() {
        return n_rate_users_str;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setCourseware_url(String courseware_url) {
        this.courseware_url = courseware_url;
    }

    public String getCourseware_url() {
        return courseware_url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setN_views(int n_views) {
        this.n_views = n_views;
    }

    public int getN_views() {
        return n_views;
    }

}