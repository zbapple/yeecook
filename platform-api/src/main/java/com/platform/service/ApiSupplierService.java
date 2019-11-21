package com.platform.service;

import com.platform.dao.ApiSupplierMapper;

import com.platform.entity.SupplierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-11-06 16:55:52
 */
@Service
public class ApiSupplierService {
    @Autowired
    private ApiSupplierMapper supplierDao;

    public SupplierVo queryObject(Long id) {
        return supplierDao.queryObject(id);
    }

    public List<SupplierVo> queryList(Map<String, Object> map) {
        return supplierDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return supplierDao.queryTotal(map);
    }

    public int save(SupplierVo supplier) {
        return supplierDao.save(supplier);
    }

    public int update(SupplierVo supplier) {
        return supplierDao.update(supplier);
    }

    public int delete(Long id) {
        return supplierDao.delete(id);
    }

    public int deleteBatch(Long[] ids) {
        return supplierDao.deleteBatch(ids);
    }
}
