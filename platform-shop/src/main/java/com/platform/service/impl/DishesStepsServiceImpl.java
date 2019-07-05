package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.DishesStepsDao;
import com.platform.entity.DishesStepsEntity;
import com.platform.service.DishesStepsService;

/**
 * 菜品步骤表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:31:59
 */
@Service("dishesStepsService")
public class DishesStepsServiceImpl implements DishesStepsService {
    @Autowired
    private DishesStepsDao dishesStepsDao;

    @Override
    public DishesStepsEntity queryObject(Integer id) {
        return dishesStepsDao.queryObject(id);
    }

    @Override
    public List<DishesStepsEntity> queryList(Map<String, Object> map) {
        return dishesStepsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return dishesStepsDao.queryTotal(map);
    }

    @Override
    public int save(DishesStepsEntity dishesSteps) {
        return dishesStepsDao.save(dishesSteps);
    }

    @Override
    public int update(DishesStepsEntity dishesSteps) {
        return dishesStepsDao.update(dishesSteps);
    }

    @Override
    public int delete(Integer id) {
        return dishesStepsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return dishesStepsDao.deleteBatch(ids);
    }
}
