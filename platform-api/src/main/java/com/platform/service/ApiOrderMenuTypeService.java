package com.platform.service;

import com.platform.dao.ApiOrderMenuTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.platform.entity.OrderMenuTypeEntity;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-23 11:55:56
 */
@Service
public class ApiOrderMenuTypeService  {
    @Autowired
    private ApiOrderMenuTypeMapper orderMenuTypeDao;


    public OrderMenuTypeEntity queryObject(Integer id) {
        return orderMenuTypeDao.queryObject(id);
    }


    public List<OrderMenuTypeEntity> queryList(Map<String, Object> map) {
        return orderMenuTypeDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return orderMenuTypeDao.queryTotal(map);
    }


    public int save(OrderMenuTypeEntity orderMenuType) {
        return orderMenuTypeDao.save(orderMenuType);
    }


    public int update(OrderMenuTypeEntity orderMenuType) {
        return orderMenuTypeDao.update(orderMenuType);
    }


    public int delete(Integer id) {
        return orderMenuTypeDao.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return orderMenuTypeDao.deleteBatch(ids);
    }
}
