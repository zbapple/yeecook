package com.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 nideshop_order_supplier
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-09-19 12:23:49
 */
public class OrderSupplierEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //供应商id
    private Long supplierId;
    //供应商订单号
    private String orderSupSn;
    //用户订单编号
    private String orderSn;
    //用户id
    private Integer userId;
    //订单状态
    private Integer orderStatus;
    //收件人
    private String consignee;
    //国家
    private String country;
    //省份
    private String province;
    //城市
    private String city;
    //地区
    private String district;
    //详细地址
    private String address;
    //手机号码
    private String mobile;
    //用户备注
    private String postscript;
    //快递公司ID
    private Integer shippingId;
    //快递公司
    private String shippingName;
    //运费
    private BigDecimal shippingFee;
    //实际需要支付的金额
    private BigDecimal actualPrice;
    //订单总价
    private BigDecimal orderPrice;
    //商品总价
    private BigDecimal goodsPrice;
    //新增时间
    private Date addTime;
    //确认时间
    private Date confirmTime;
    //付款时间
    private Date payTime;
    //配送费用
    private Integer freightPrice;
    //使用的优惠券id
    private Integer couponId;
    //优惠价格
    private BigDecimal couponPrice;
    //快递单号
    private String shippingNo;
    //配送费用
    private BigDecimal fullCutPrice;
    //订单类型 1：普通订单 2：团购订单 3：砍价订单 4: 直接购买
    private String orderType;
    //是否已经打印：0未打印，1已打印
    private Integer isPrinter;
    //供应商部门id
    private Integer deptId;

    private String supplierName;

    private String supplierAddress;

	private String supplierTelephone;

	private String supplierMobile;

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
     * 设置：供应商id
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 获取：供应商id
     */
    public Long getSupplierId() {
        return supplierId;
    }
    /**
     * 设置：供应商订单号
     */
    public void setOrderSupSn(String orderSupSn) {
        this.orderSupSn = orderSupSn;
    }

    /**
     * 获取：供应商订单号
     */
    public String getOrderSupSn() {
        return orderSupSn;
    }
    /**
     * 设置：用户订单编号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取：用户订单编号
     */
    public String getOrderSn() {
        return orderSn;
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
    /**
     * 设置：订单状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取：订单状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }
    /**
     * 设置：收件人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取：收件人
     */
    public String getConsignee() {
        return consignee;
    }
    /**
     * 设置：国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取：国家
     */
    public String getCountry() {
        return country;
    }
    /**
     * 设置：省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：省份
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：城市
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：地区
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取：地区
     */
    public String getDistrict() {
        return district;
    }
    /**
     * 设置：详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：详细地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号码
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：用户备注
     */
    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    /**
     * 获取：用户备注
     */
    public String getPostscript() {
        return postscript;
    }
    /**
     * 设置：快递公司ID
     */
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    /**
     * 获取：快递公司ID
     */
    public Integer getShippingId() {
        return shippingId;
    }
    /**
     * 设置：快递公司
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    /**
     * 获取：快递公司
     */
    public String getShippingName() {
        return shippingName;
    }
    /**
     * 设置：运费
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * 获取：运费
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }
    /**
     * 设置：实际需要支付的金额
     */
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * 获取：实际需要支付的金额
     */
    public BigDecimal getActualPrice() {
        return actualPrice;
    }
    /**
     * 设置：订单总价
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 获取：订单总价
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }
    /**
     * 设置：商品总价
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取：商品总价
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }
    /**
     * 设置：新增时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：新增时间
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * 设置：确认时间
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * 获取：确认时间
     */
    public Date getConfirmTime() {
        return confirmTime;
    }
    /**
     * 设置：付款时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取：付款时间
     */
    public Date getPayTime() {
        return payTime;
    }
    /**
     * 设置：配送费用
     */
    public void setFreightPrice(Integer freightPrice) {
        this.freightPrice = freightPrice;
    }

    /**
     * 获取：配送费用
     */
    public Integer getFreightPrice() {
        return freightPrice;
    }
    /**
     * 设置：使用的优惠券id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取：使用的优惠券id
     */
    public Integer getCouponId() {
        return couponId;
    }
    /**
     * 设置：优惠价格
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取：优惠价格
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }
    /**
     * 设置：快递单号
     */
    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo;
    }

    /**
     * 获取：快递单号
     */
    public String getShippingNo() {
        return shippingNo;
    }
    /**
     * 设置：配送费用
     */
    public void setFullCutPrice(BigDecimal fullCutPrice) {
        this.fullCutPrice = fullCutPrice;
    }

    /**
     * 获取：配送费用
     */
    public BigDecimal getFullCutPrice() {
        return fullCutPrice;
    }
    /**
     * 设置：订单类型 1：普通订单 2：团购订单 3：砍价订单 4: 直接购买
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取：订单类型 1：普通订单 2：团购订单 3：砍价订单 4: 直接购买
     */
    public String getOrderType() {
        return orderType;
    }
    /**
     * 设置：是否已经打印：0未打印，1已打印
     */
    public void setIsPrinter(Integer isPrinter) {
        this.isPrinter = isPrinter;
    }

    /**
     * 获取：是否已经打印：0未打印，1已打印
     */
    public Integer getIsPrinter() {
        return isPrinter;
    }
    /**
     * 设置：供应商部门id
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：供应商部门id
     */
    public Integer getDeptId() {
        return deptId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierTelephone() {
        return supplierTelephone;
    }

    public void setSupplierTelephone(String supplierTelephone) {
        this.supplierTelephone = supplierTelephone;
    }

    public String getSupplierMobile() {
        return supplierMobile;
    }

    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile;
    }
}
