package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体
 * 表名 nideshop_stroe
 *
 * @author 17
 * @email inaoie@163.com
 * @date 2019-11-01 11:00:09
 */
@Data
public class StroeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //门店名称
    private String storeName;
    //门店类型(1.营养餐 2.月子餐3.长者餐)
    private Integer storeType;
    //门店logo
    private String storePicrue;
    //国家
    private String country;
    //省
    private String province;
    //城市
    private String city;
    //区
    private String distrct;
    //详细地址
    private String address;
    //部门id
    private Integer departmentid;
    //供应商id
    private Integer supplierid;
    //经度
    private Double longitude;
    //纬度
    private Double latitude;
    //门店电话
    private String storePhone;
    //门店评分
    private Double stroeGrade;
    //起送费
    private Double sendingfee;
    //配送费
    private Double deliveryfee;
    //门店实景图片
    private String realisticPicture;
    //门店实景图片list
    private List<StroeEntity> picList;
    //营业执照
    private String businessLicense;
    //门店的食品执照
    private String licensePic;
    //门店营业时间
    private String storeTime;
    //门店营业时间
    private List<String> storeTimes;
    //门店状态（0：暂停营业，1：正常营业）
    private Integer storeStatus;
    //门店回收
    private Integer isDelete;
    //删除时间
    private String deleteTime;
    //开户银行
    private String openingBank;
    //银行账号
    private String bankAccount;
    //收款户名
    private String beneficiaryName;
}