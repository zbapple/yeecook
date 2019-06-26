package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 云课堂表
实体
 * 表名 cloud_classroom
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:35:47
 */
public class CloudClassroomEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //视频描述
    private String videoDescribe;
    //视频主标题
    private String videoTitle;
    //视频副标题
    private String videoSubtitle;
    //视频封面图片
    private String videoCoverPic;
    //视频地址
    private String videoAdress;

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
     * 设置：视频描述
     */
    public void setVideoDescribe(String videoDescribe) {
        this.videoDescribe = videoDescribe;
    }

    /**
     * 获取：视频描述
     */
    public String getVideoDescribe() {
        return videoDescribe;
    }
    /**
     * 设置：视频主标题
     */
    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    /**
     * 获取：视频主标题
     */
    public String getVideoTitle() {
        return videoTitle;
    }
    /**
     * 设置：视频副标题
     */
    public void setVideoSubtitle(String videoSubtitle) {
        this.videoSubtitle = videoSubtitle;
    }

    /**
     * 获取：视频副标题
     */
    public String getVideoSubtitle() {
        return videoSubtitle;
    }
    /**
     * 设置：视频封面图片
     */
    public void setVideoCoverPic(String videoCoverPic) {
        this.videoCoverPic = videoCoverPic;
    }

    /**
     * 获取：视频封面图片
     */
    public String getVideoCoverPic() {
        return videoCoverPic;
    }
    /**
     * 设置：视频地址
     */
    public void setVideoAdress(String videoAdress) {
        this.videoAdress = videoAdress;
    }

    /**
     * 获取：视频地址
     */
    public String getVideoAdress() {
        return videoAdress;
    }
}
