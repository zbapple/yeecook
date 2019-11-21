package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_supplier
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-11-06 16:55:52
 */
public class SupplierVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //供应商公司名称
    private String companyName;
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
    //部门id
    private Long deptId;
    //地址
    private String address;
    //部门名称
    private String deptName;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：供应商公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取：供应商公司名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 设置：开户银行
     */
    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    /**
     * 获取：开户银行
     */
    public String getOpeningBank() {
        return openingBank;
    }
    /**
     * 设置：银行账号
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * 获取：银行账号
     */
    public String getBankAccount() {
        return bankAccount;
    }
    /**
     * 设置：收款户名
     */
    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    /**
     * 获取：收款户名
     */
    public String getBeneficiaryName() {
        return beneficiaryName;
    }
    /**
     * 设置：电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取：电话
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * 设置：手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：部门id
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：部门id
     */
    public Long getDeptId() {
        return deptId;
    }
    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取：部门名称
     */
    public String getDeptName() {
        return deptName;
    }
}
