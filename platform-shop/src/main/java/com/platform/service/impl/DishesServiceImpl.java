package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.DishesDao;
import com.platform.entity.DishesEntity;
import com.platform.service.DishesService;

/**
 * 菜品表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:33:22
 */
@Service("dishesService")
public class DishesServiceImpl implements DishesService {
    @Autowired
    private DishesDao dishesDao;

    @Override
    public DishesEntity queryObject(Integer id) {
        return dishesDao.queryObject(id);
    }

    @Override
    public List<DishesEntity> queryList(Map<String, Object> map) {
        return dishesDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return dishesDao.queryTotal(map);
    }

    @Override
    public int save(DishesEntity dishes) {
        return dishesDao.save(dishes);
    }

    @Override
    public int update(DishesEntity dishes) {
        return dishesDao.update(dishes);
    }

    @Override
    public int delete(Integer id) {
        return dishesDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return dishesDao.deleteBatch(ids);
    }
}
