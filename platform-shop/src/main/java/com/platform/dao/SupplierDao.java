package com.platform.dao;

import com.platform.entity.SupplierEntity;


/**
 * Dao
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-09-12 14:23:53
 */
public interface SupplierDao extends BaseDao<SupplierEntity> {
    SupplierEntity queryName(Long deptId);
}
