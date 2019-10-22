package com.platform.dao;

import com.platform.entity.OrderSupplierEntity;

import java.util.Map;

/**
 * Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-09-19 12:23:49
 */
public interface OrderSupplierDao extends BaseDao<OrderSupplierEntity> {

    OrderSupplierEntity queryInfo(Map<String,Object> map);
}
