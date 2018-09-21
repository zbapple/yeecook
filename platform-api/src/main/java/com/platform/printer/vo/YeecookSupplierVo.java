package com.platform.printer.vo;

import com.platform.entity.GoodsVo;
import com.platform.entity.OrderGoodsVo;
import com.platform.entity.OrderSupplierVo;
import com.platform.entity.OrderVo;

import java.util.List;

public class YeecookSupplierVo {

    private static final long serialVersionUID = 1L;

    private OrderSupplierVo orderVo;
    private List<OrderGoodsVo> orderGoodsVoList;

    public OrderSupplierVo getOrderVo() {
        return orderVo;
    }

    public void setOrderVo(OrderSupplierVo orderVo) {
        this.orderVo = orderVo;
    }

    public List<OrderGoodsVo> getOrderGoodsVoList() {
        return orderGoodsVoList;
    }

    public void setOrderGoodsVoList(List<OrderGoodsVo> orderGoodsVoList) {
        this.orderGoodsVoList = orderGoodsVoList;
    }



}
