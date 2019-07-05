package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.FoodNutrientElementsDao;
import com.platform.entity.FoodNutrientElementsEntity;
import com.platform.service.FoodNutrientElementsService;

/**
 * 食材营养元素对应表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:26:15
 */
@Service("foodNutrientElementsService")
public class FoodNutrientElementsServiceImpl implements FoodNutrientElementsService {
    @Autowired
    private FoodNutrientElementsDao foodNutrientElementsDao;

    @Override
    public FoodNutrientElementsEntity queryObject(Integer id) {
        return foodNutrientElementsDao.queryObject(id);
    }

    @Override
    public List<FoodNutrientElementsEntity> queryList(Map<String, Object> map) {
        return foodNutrientElementsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return foodNutrientElementsDao.queryTotal(map);
    }

    @Override
    public int save(FoodNutrientElementsEntity foodNutrientElements) {
        return foodNutrientElementsDao.save(foodNutrientElements);
    }

    @Override
    public int update(FoodNutrientElementsEntity foodNutrientElements) {
        return foodNutrientElementsDao.update(foodNutrientElements);
    }

    @Override
    public int delete(Integer id) {
        return foodNutrientElementsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return foodNutrientElementsDao.deleteBatch(ids);
    }
}
