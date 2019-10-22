package com.platform.service.impl;

import com.platform.annotation.DataFilter;
import com.platform.dao.OrderDao;
import com.platform.dao.ShippingDao;
import com.platform.entity.OrderEntity;
import com.platform.entity.ShippingEntity;
import com.platform.utils.JsonUtil;
import com.platform.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platform.dao.OrderSupplierDao;
import com.platform.entity.OrderSupplierEntity;
import com.platform.service.OrderSupplierService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-09-19 12:23:49
 */
@Service("orderSupplierService")
public class  OrderSupplierServiceImpl implements OrderSupplierService {
    @Autowired
    private OrderSupplierDao orderSupplierDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ShippingDao shippingDao;

    @Override
    @DataFilter(deptAlias = "a.dept_id")
    public OrderSupplierEntity queryObject(Integer id) {
        return orderSupplierDao.queryObject(id);
    }

    @Override
    @DataFilter(deptAlias = "a.dept_id")
    public OrderSupplierEntity queryInfo(Integer id) {
        HashMap<String,Object> map=new HashMap();
        map.put("id",id);
        return orderSupplierDao.queryInfo(map);
    }


    @Override
    @DataFilter(deptAlias = "a.dept_id")
    public List<OrderSupplierEntity> queryList(Map<String, Object> map) {
        return orderSupplierDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderSupplierDao.queryTotal(map);
    }

    @Override
    public int save(OrderSupplierEntity orderSupplier) {
        return orderSupplierDao.save(orderSupplier);
    }

    @Override
    public int update(OrderSupplierEntity orderSupplier) {
        return orderSupplierDao.update(orderSupplier);
    }

    @Override
    public int delete(Integer id) {
        return orderSupplierDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return orderSupplierDao.deleteBatch(ids);
    }

    @Override
    public int confirm(Integer id) {
        OrderSupplierEntity orderSupplierEntity = queryObject(id);
        Map map =new HashMap();
        map.put("orderSn",orderSupplierEntity.getOrderSn());
        List orderEntityList = orderDao.queryList(map);
        if (orderEntityList==null||orderEntityList.size()<=0){
            throw new RRException("用户主订单不存在，请联系管理员！");
        }
        OrderEntity orderEntity= (OrderEntity) orderEntityList.get(0);
        Integer shippingStatus = orderSupplierEntity.getShippingStatus();//发货状态
        Integer payStatus = orderSupplierEntity.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new RRException("此订单未付款，不能确认收货！");
        }
        if (4 == shippingStatus) {
            throw new RRException("此订单处于退货状态，不能确认收货！");
        }
        if (0 == shippingStatus) {
            throw new RRException("此订单未发货，不能确认收货！");
        }
        orderSupplierEntity.setShippingStatus(2);
        //统计用户订单有多少供应商订单
        int a=orderSupplierDao.queryTotal(map);
        //统计已确认收货的供应商有多少家
        map.put("shippingStatus","2");
        int b=orderSupplierDao.queryTotal(map);
        //供应商都已收货更新用户订单为已确认收货状态
        if(a==b+1) {
            orderEntity.setOrderStatus(301);
        }
        Map map1= JsonUtil.getObjet(orderEntity.getSupplierList(),HashMap.class);
        map1.put(String.valueOf(orderSupplierEntity.getSupplierId()),orderSupplierEntity.getSupplierName()+"供应商确认收货;");
        orderEntity.setSupplierList(JsonUtil.getJsonByObj(map1));
        orderDao.update(orderEntity);
        return orderSupplierDao.update(orderSupplierEntity);
    }

    @Override
    public int sendGoods(OrderSupplierEntity orderSupplierEntity) {
        Integer payStatus = orderSupplierEntity.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new RRException("此订单未付款！");
        }

        ShippingEntity shippingEntity = shippingDao.queryObject(orderSupplierEntity.getShippingId());
        if (null != shippingEntity) {
            orderSupplierEntity.setShippingName(shippingEntity.getName());
        }
        Map map =new HashMap();
        map.put("orderSn",orderSupplierEntity.getOrderSn());
        List orderEntityList = orderDao.queryList(map);
        if (orderEntityList==null||orderEntityList.size()<=0){
            throw new RRException("用户主订单不存在，请联系管理员！");
        }
        OrderEntity orderEntity= (OrderEntity) orderEntityList.get(0);
        //统计用户订单有多少供应商订单
        int a=orderSupplierDao.queryTotal(map);
        //统计已发货的供应商有多少家
        map.put("shippingStatus","1");
        int b=orderSupplierDao.queryTotal(map);
        //供应商都已发货更新用户订单为已发货状态
        if(a==b+1) {
            orderEntity.setOrderStatus(300);

        }
        Map map1= JsonUtil.getObjet(orderEntity.getSupplierList(),HashMap.class);
        map1.put(String.valueOf(orderSupplierEntity.getSupplierId()),orderSupplierEntity.getSupplierName()+"供应商已发货;");
        orderEntity.setSupplierList(JsonUtil.getJsonByObj(map1));
        orderDao.update(orderEntity);
        orderSupplierEntity.setShippingStatus(1);//已发货
        return orderSupplierDao.update(orderSupplierEntity);
    }
    
}
