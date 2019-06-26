package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.FoodIngredientsDao;
import com.platform.entity.FoodIngredientsEntity;
import com.platform.service.FoodIngredientsService;

/**
 * 菜品食材表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:29:33
 */
@Service("foodIngredientsService")
public class FoodIngredientsServiceImpl implements FoodIngredientsService {
    @Autowired
    private FoodIngredientsDao foodIngredientsDao;

    @Override
    public FoodIngredientsEntity queryObject(Integer id) {
        return foodIngredientsDao.queryObject(id);
    }

    @Override
    public List<FoodIngredientsEntity> queryList(Map<String, Object> map) {
        return foodIngredientsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return foodIngredientsDao.queryTotal(map);
    }

    @Override
    public int save(FoodIngredientsEntity foodIngredients) {
        return foodIngredientsDao.save(foodIngredients);
    }

    @Override
    public int update(FoodIngredientsEntity foodIngredients) {
        return foodIngredientsDao.update(foodIngredients);
    }

    @Override
    public int delete(Integer id) {
        return foodIngredientsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return foodIngredientsDao.deleteBatch(ids);
    }
}
