package com.platform.service.impl;

import com.platform.annotation.DataFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.SupplierDao;
import com.platform.entity.SupplierEntity;
import com.platform.service.SupplierService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-09-12 14:23:53
 */
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;

    @Override
    public SupplierEntity queryObject(Long id) {
        return supplierDao.queryObject(id);
    }

    @Override
    public SupplierEntity queryName(Long deptId) {
        return supplierDao.queryName(deptId);
    }

    @Override
    @DataFilter(deptAlias = "dept_id")
    public List<SupplierEntity> queryList(Map<String, Object> map) {

        return supplierDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return supplierDao.queryTotal(map);
    }

    @Override
    public int save(SupplierEntity supplier) {
        return supplierDao.save(supplier);
    }

    @Override
    public int update(SupplierEntity supplier) {
        return supplierDao.update(supplier);
    }

    @Override
    public int updateStatus(@Param(value = "id") Integer id) {
        SupplierEntity supplier = supplierDao.queryObject(id);
        Integer supplierStatus = supplier.getCompanyStatus();
        if (0 == supplierStatus) {
            supplier.setCompanyStatus(1);
        }
        if (1 == supplierStatus){
            supplier.setCompanyStatus(0);
        }
        return supplierDao.update(supplier);
    }
    @Override
    public int delete(Long id) {
        SupplierEntity supplierEntity = queryObject(id);
        supplierEntity.setIsDelete(1);
        return supplierDao.update(supplierEntity);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        int result = 0;
        for (Long id : ids) {
            result += delete(id);
        }
        return result;
    }
    @Override
    @Transactional
    public int back(Long[] ids) {
        int result = 0;
        for (Long id : ids) {
            SupplierEntity supplierEntity = queryObject(id);
            supplierEntity.setIsDelete(0);
            result += supplierDao.update(supplierEntity);
        }
        return result;
    }

    @Override
    public int deleteIs(Integer id) {
        return supplierDao.delete(id);
    }

    @Override
    public int deleteAll(Integer[] ids) {
        return supplierDao.deleteBatch(ids);
    }
}
