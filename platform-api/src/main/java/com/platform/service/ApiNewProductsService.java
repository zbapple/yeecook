package com.platform.service.impl;

import com.platform.entity.ApiNewProductsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ApiNewProductsMapper;


/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 10:28:00
 */
@Service
public class ApiNewProductsService {
    @Autowired
    private ApiNewProductsMapper newProductsDao;


    public ApiNewProductsVo queryObject(Integer id) {
        return newProductsDao.queryObject(id);
    }


    public List<ApiNewProductsVo> queryList(Map<String, Object> map) {
        return newProductsDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return newProductsDao.queryTotal(map);
    }


    public int save(ApiNewProductsVo newProducts) {
        return newProductsDao.save(newProducts);
    }


    public int update(ApiNewProductsVo newProducts) {
        return newProductsDao.update(newProducts);
    }


    public int delete(Integer id) {
        return newProductsDao.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return newProductsDao.deleteBatch(ids);
    }
}
