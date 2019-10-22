package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 promotion_info
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-22 11:58:47
 */
@Data
public class PromotionInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //推广员id
    private Long parentId;
    //推广员姓名
    private String promotionName;
    //用户id
    private Integer userId;
    //用户昵称
    private String nickname;
    //用户头像
    private String avatar;
    //付款时间
    private Date payTime;
    //商品名
    private String goodsName;
    //商品数量
    private Integer number;
    //订单总价
    private BigDecimal orderPrice;

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
     * 设置：推广员id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：推广员id
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * 设置：用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public Integer getUserId() {
        return userId;
    }
}
