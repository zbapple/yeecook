package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.FoodTypeDao;
import com.platform.entity.FoodTypeEntity;
import com.platform.service.FoodTypeService;

/**
 * 食材类型表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:25:00
 */
@Service("foodTypeService")
public class FoodTypeServiceImpl implements FoodTypeService {
    @Autowired
    private FoodTypeDao foodTypeDao;

    @Override
    public FoodTypeEntity queryObject(Integer id) {
        return foodTypeDao.queryObject(id);
    }

    @Override
    public List<FoodTypeEntity> queryList(Map<String, Object> map) {
        return foodTypeDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return foodTypeDao.queryTotal(map);
    }

    @Override
    public int save(FoodTypeEntity foodType) {
        return foodTypeDao.save(foodType);
    }

    @Override
    public int update(FoodTypeEntity foodType) {
        return foodTypeDao.update(foodType);
    }

    @Override
    public int delete(Integer id) {
        return foodTypeDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return foodTypeDao.deleteBatch(ids);
    }
}
