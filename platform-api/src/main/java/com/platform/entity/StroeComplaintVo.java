package com.platform.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 实体
 * 表名 nideshop_stroe_complaint
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-29 14:42:33
 */
public class StroeComplaintVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //投诉类型
    private String complaintType;
    //内容
    private String connet;
    //图片
    private String complaintPic;
    //商家id
    private Integer stroeId;
    //用户id
    private Long userid;
    //添加时间
    private Date addtime;
    //类型
    private Integer complaintypes;
    //二进制参数
    private String key;
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
     * 设置：投诉类型
     */
    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    /**
     * 获取：投诉类型
     */
    public String getComplaintType() {
        return complaintType;
    }
    /**
     * 设置：图片
     */
    public void setComplaintPic(String complaintPic) {
        this.complaintPic = complaintPic;
    }

    /**
     * 获取：图片
     */
    public String getComplaintPic() {
        return complaintPic;
    }
    /**
     * 设置：商家id
     */
    public void setStroeId(Integer stroeId) {
        this.stroeId = stroeId;
    }

    /**
     * 获取：商家id
     */
    public Integer getStroeId() {
        return stroeId;
    }


    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }



    public void setComplaintypes(Integer complaintypes) {
        this.complaintypes = complaintypes;
    }

    public Integer getComplaintypes() {
        return complaintypes;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getConnet() {
        return connet;
    }

    public void setConnet(String connet) {
        this.connet = connet;
    }
}
