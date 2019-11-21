package com.platform.service;

import com.platform.dao.ApiOrderMenuplanMapper;
import com.platform.entity.OrderMenuplanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-25 15:35:28
 */
@Service
public class ApiOrderMenuplanService {
    @Autowired
    private ApiOrderMenuplanMapper orderMenuplanDao;


    public OrderMenuplanVo queryObject(Integer id) {
        return orderMenuplanDao.queryObject(id);
    }

    public List<OrderMenuplanVo> queryList(Map<String, Object> map) {
        return orderMenuplanDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return orderMenuplanDao.queryTotal(map);
    }

    public int save(OrderMenuplanVo orderMenuplan) {
        return orderMenuplanDao.save(orderMenuplan);
    }

    public int update(OrderMenuplanVo orderMenuplan) {
        return orderMenuplanDao.update(orderMenuplan);
    }

    public int delete(Integer id) {
        return orderMenuplanDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return orderMenuplanDao.deleteBatch(ids);
    }
}
