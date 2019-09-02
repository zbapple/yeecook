package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 云课堂商品关联表
实体
 * 表名 cloud_classroom_good
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:34:57
 */
@Data
public class CloudClassroomGoodEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //课件id
    private Integer videoId;
    //视频标题
    private String videoTitle;
    //商品id
    private Integer goodsId;
    //商品名
    private String goodsName;
    //主图片
    private String primaryPicUrl;
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
     * 设置：商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }
}
