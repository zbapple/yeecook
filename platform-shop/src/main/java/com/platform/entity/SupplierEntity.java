package com.platform.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 实体
 * 表名 nideshop_supplier
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-09-12 14:23:53
 */


@Data
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    //供应商公司名称
    private String companyName;
    //供应商logo
    private String companyLogo;
    //供应商类型
    private Integer companyType;
    //供应商状态
    private Integer companyStatus;
    //供应商回收
    private Integer isDelete;
    //开户银行
    private String openingBank;
    //银行账号
    private String bankAccount;
    //收款户名
    private String beneficiaryName;
    //电话
    private String telephone;
    //手机
    private String mobile;
    //部门
    private Long deptId;
    //部门名称
    private String deptName;
    //地址id
    private String address;
    //删除时间
    private String deleteTime;


}
