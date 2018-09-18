package com.platform.printer.vo;

import com.platform.entity.OrderGoodsVo;
import com.platform.entity.OrderVo;

import java.util.List;

public class YeecookVo {

    private static final long serialVersionUID = 1L;

    private OrderVo orderVo;
    private List<OrderGoodsVo> orderGoodsVoList;

    public OrderVo getOrderVo() {
        return orderVo;
    }

    public void setOrderVo(OrderVo orderVo) {
        this.orderVo = orderVo;
    }

    public List<OrderGoodsVo> getOrderGoodsVoList() {
        return orderGoodsVoList;
    }

    public void setOrderGoodsVoList(List<OrderGoodsVo> orderGoodsVoList) {
        this.orderGoodsVoList = orderGoodsVoList;
    }



}
