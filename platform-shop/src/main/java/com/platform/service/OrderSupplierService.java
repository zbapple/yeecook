package com.platform.service;

import com.platform.entity.OrderSupplierEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-09-19 12:23:49
 */
public interface OrderSupplierService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    OrderSupplierEntity queryObject(Integer id);

    OrderSupplierEntity queryInfo(Integer id);
    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<OrderSupplierEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param orderSupplier 实体
     * @return 保存条数
     */
    int save(OrderSupplierEntity orderSupplier);

    /**
     * 根据主键更新实体
     *
     * @param orderSupplier 实体
     * @return 更新条数
     */
    int update(OrderSupplierEntity orderSupplier);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);

    /**
     * 确定收货
     *
     * @param id
     * @return
     */
    int confirm(Integer id);

    int sendGoods(OrderSupplierEntity orderSupplier);

}
