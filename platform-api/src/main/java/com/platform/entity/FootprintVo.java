package com.platform.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.utils.JsonDateSerializer;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:40
 */
public class FootprintVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //会员Id
    private Long user_id;
    //商品id
    private Integer goods_id;
    //记录时间
    private Long add_time;
    //推荐人
    private Long referrer;

    // 商品冗余字段
    private String name;
    private String list_pic_url;
    private String goods_brief;
    //
    private BigDecimal retail_price;
    // 会员
    private String nickname;
    private String avatar;
    //门店id
    private Integer stroeid;
    //门店名字
    private  String storename;
    //门店图片
    private String storepic;
    //门店状态
    private Integer storestatus;
    //
    private Integer checked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getList_pic_url() {
        return list_pic_url;
    }

    public void setList_pic_url(String list_pic_url) {
        this.list_pic_url = list_pic_url;
    }

    public String getGoods_brief() {
        return goods_brief;
    }

    public void setGoods_brief(String goods_brief) {
        this.goods_brief = goods_brief;
    }


    public BigDecimal getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(BigDecimal retail_price) {
        this.retail_price = retail_price;
    }

    public Long getReferrer() {
        return referrer;
    }

    public void setReferrer(Long referrer) {
        this.referrer = referrer;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStorepic() {
        return storepic;
    }

    public void setStorepic(String storepic) {
        this.storepic = storepic;
    }

    public Integer getStorestatus() {
        return storestatus;
    }

    public void setStorestatus(Integer storestatus) {
        this.storestatus = storestatus;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
    public Integer getStroeid() {
        return stroeid;
    }

    public void setStroeid(Integer stroeid) {
        this.stroeid = stroeid;
    }
}
