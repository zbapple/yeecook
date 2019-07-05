package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户评论表
 用户id
 用户评论
 课件id
 评论分数
 评论时间
 回复类型 0是 1否
 回复id
 id实体
 * 表名 user_comments
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:50
 */
public class UserCommentsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //用户id
    private Long nideshopUserId;
    //用户评论
    private String userComment;
    //课件id
    private Integer videoId;
    //评论分数
    private String commentsScore;
    //评论时间
    private Date commentsTime;
    //回复类型 0是 1否
    private Integer replyType;
    //回复id
    private Integer replyId;
    //用户昵称
    private  String username;
    private String avatar;
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
     * 设置：用户id
     */
    public void setNideshopUserId(Long nideshopUserId) {
        this.nideshopUserId = nideshopUserId;
    }

    /**
     * 获取：用户id
     */
    public Long getNideshopUserId() {
        return nideshopUserId;
    }
    /**
     * 设置：用户评论
     */
    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    /**
     * 获取：用户评论
     */
    public String getUserComment() {
        return userComment;
    }
    /**
     * 设置：课件id
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取：课件id
     */
    public Integer getVideoId() {
        return videoId;
    }
    /**
     * 设置：评论分数
     */
    public void setCommentsScore(String commentsScore) {
        this.commentsScore = commentsScore;
    }

    /**
     * 获取：评论分数
     */
    public String getCommentsScore() {
        return commentsScore;
    }
    /**
     * 设置：评论时间
     */
    public void setCommentsTime(Date commentsTime) {
        this.commentsTime = commentsTime;
    }

    /**
     * 获取：评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCommentsTime() {
        return commentsTime;
    }
    /**
     * 设置：回复类型 0是 1否
     */
    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    /**
     * 获取：回复类型 0是 1否
     */
    public Integer getReplyType() {
        return replyType;
    }
    /**
     * 设置：回复id
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * 获取：回复id
     */
    public Integer getReplyId() {
        return replyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
