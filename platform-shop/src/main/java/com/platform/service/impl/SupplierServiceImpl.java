package com.platform.service.impl;

import com.platform.annotation.DataFilter;
import com.platform.entity.SysUserEntity;
import com.platform.utils.Constant;
import com.platform.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.SupplierDao;
import com.platform.entity.SupplierEntity;
import com.platform.service.SupplierService;

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
    public int delete(Long id) {
        return supplierDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return supplierDao.deleteBatch(ids);
    }
}
