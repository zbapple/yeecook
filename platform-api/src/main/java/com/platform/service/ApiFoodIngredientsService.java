package com.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ApiFoodIngredientsMapper;
import com.platform.entity.FoodIngredientsVo;

/**
 * 菜品食材表
 id
 食材名称
 食材数量
 食材总卡路里
 菜品idService实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:06:22
 */
@Service
public class ApiFoodIngredientsService  {
    @Autowired
    private ApiFoodIngredientsMapper foodIngredientsDao;

    public FoodIngredientsVo queryObject(Integer id) {
        return foodIngredientsDao.queryObject(id);
    }


    public List<FoodIngredientsVo> queryList(Map<String, Object> map) {
        return foodIngredientsDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return foodIngredientsDao.queryTotal(map);
    }

    public int save(FoodIngredientsVo foodIngredients) {
        return foodIngredientsDao.save(foodIngredients);
    }

    public int update(FoodIngredientsVo foodIngredients) {
        return foodIngredientsDao.update(foodIngredients);
    }

    public int delete(Integer id) {
        return foodIngredientsDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return foodIngredientsDao.deleteBatch(ids);
    }
}