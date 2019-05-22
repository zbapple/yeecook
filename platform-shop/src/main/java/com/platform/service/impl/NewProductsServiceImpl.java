package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.NewProductsDao;
import com.platform.entity.NewProductsEntity;
import com.platform.service.NewProductsService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 14:49:30
 */
@Service("newProductsService")
public class NewProductsServiceImpl implements NewProductsService {
    @Autowired
    private NewProductsDao newProductsDao;

    @Override
    public NewProductsEntity queryObject(Integer id) {
        return newProductsDao.queryObject(id);
    }

    @Override
    public List<NewProductsEntity> queryList(Map<String, Object> map) {
        return newProductsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return newProductsDao.queryTotal(map);
    }

    @Override
    public int save(NewProductsEntity newProducts) {
        return newProductsDao.save(newProducts);
    }

    @Override
    public int update(NewProductsEntity newProducts) {
        return newProductsDao.update(newProducts);
    }

    @Override
    public int delete(Integer id) {
        return newProductsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return newProductsDao.deleteBatch(ids);
    }
}
