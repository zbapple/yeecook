package com.platform.service;

import com.platform.dao.ApiMealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.platform.entity.MealVo;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-27 16:50:29
 */
@Service
public class ApiMealService {
    @Autowired
    private ApiMealMapper mealDao;

    public MealVo queryObject(Integer id) {
        return mealDao.queryObject(id);
    }

    public List<MealVo> queryList(Map<String, Object> map) {
        return mealDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return mealDao.queryTotal(map);
    }

    public int save(MealVo meal) {
        return mealDao.save(meal);
    }

    public int update(MealVo meal) {
        return mealDao.update(meal);
    }

    public int delete(Integer id) {
        return mealDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return mealDao.deleteBatch(ids);
    }
}
