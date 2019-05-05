package com.platform.controller;

import com.platform.BaseSpringTestCase;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.CommonUtil;
import com.platform.utils.JsonUtil;
import com.platform.xss.SQLFilter;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员测试
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-09 10:13:43
 */
public class TestSysUserController extends BaseSpringTestCase {
//    @Autowired
//    TestSysUserService testSysUserService;
//    @Autowired
//    ApiOrderSupplierService apiOrderSupplierService;
//    @Autowired
//    ApiOrderService apiOrderService;
//    @Autowired
//    ApiOrderGoodsService apiOrderGoodsService;
//    @Autowired
//    OrderSupplierService orderSupplierService;
//
//
//
//    @Test
//    public void queryOrderSupplieList() {
//        //getGroupByGoosforDept(102);
//        //OrderSupplierEntity orderSupplierEntity= orderSupplierService.queryObject(3);
//        //orderSupplierService.sendGoods(orderSupplierEntity);
//        //orderSupplierService.confirm(5);
//        apiOrderService.printerSupplierOrder(102);
//    }
//
//    private void  getGroupByGoosforDept(Integer orderId){
//
//        OrderVo orderVo =apiOrderService.queryObject(orderId);
//        if(null==orderVo){
//            return;
//        }
//        Map params = new HashMap();
//        params.put("order_id",orderId);
//        params.put("sidx", SQLFilter.sqlInject("Supplier_id"));
//        params.put("order", SQLFilter.sqlInject("asc"));
//        List<OrderGoodsVo> orderGoodsVoList=apiOrderGoodsService.queryList(params);
//         /*根据供应商分组算法**/
//        Map<Integer, List<OrderGoodsVo>> supIdMap = new HashMap<>();
//        for (OrderGoodsVo orderGoodsVo : orderGoodsVoList) {
//            List<OrderGoodsVo> tempList = supIdMap.get(orderGoodsVo.getSupplier_id());
//            /*如果取不到数据,那么直接new一个空的ArrayList**/
//            if (tempList == null) {
//                tempList = new ArrayList<>();
//                tempList.add(orderGoodsVo);
//                supIdMap.put(orderGoodsVo.getSupplier_id(), tempList);
//            }
//            else {
//                /*某个supplierId之前已经存放过了,则直接追加数据到原来的List里**/
//                tempList.add(orderGoodsVo);
//            }
//        }
//        //供应商订单总价
//        BigDecimal  orderTotalPrice ;
//        //商品总价
//        BigDecimal  goodsTotalPrice ;
//
//        Map mapSupplier=new HashMap();
//        for(Integer supId : supIdMap.keySet()){
//            orderTotalPrice = new BigDecimal(0.00);
//            goodsTotalPrice = new BigDecimal(0.00);
//            List<OrderGoodsVo> voList=supIdMap.get(supId);
//            OrderSupplierVo orderSupplierVo=new OrderSupplierVo(orderVo);
//            for (OrderGoodsVo vo : voList){
//                orderTotalPrice= orderTotalPrice.add(vo.getMarket_price().multiply(new BigDecimal(vo.getNumber())));
//                goodsTotalPrice= goodsTotalPrice.add(vo.getMarket_price().multiply(new BigDecimal(vo.getNumber())));
//            }
//            orderSupplierVo.setDeptId(voList.get(0).getDept_id());
//            orderSupplierVo.setSupplierId(voList.get(0).getSupplier_id().longValue());
//            orderSupplierVo.setOrderPrice(orderTotalPrice);
//            orderSupplierVo.setGoodsPrice(goodsTotalPrice);
//            orderSupplierVo.setOrderSupSn(CommonUtil.generateOrderNumber());
//            logger.info("验证结果"+orderSupplierVo.getOrderSupSn()+";价格"+ orderSupplierVo.getOrderPrice());
//            apiOrderSupplierService.save(orderSupplierVo);
//            mapSupplier.put(String.valueOf(orderSupplierVo.getSupplierId()),voList.get(0).getSupplierName()+"供应商待发货中..");
//        }
//        orderVo.setSupplier_list(JsonUtil.getJsonByObj(mapSupplier));
//        apiOrderService.update(orderVo);
//
//    }


}
