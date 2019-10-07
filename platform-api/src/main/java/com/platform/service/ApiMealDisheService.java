package com.platform.service;

import com.platform.dao.ApiMealDisheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.entity.MealDisheEntity;


/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-27 16:50:29
 */
@Service
public class ApiMealDisheService  {
    @Autowired
    private ApiMealDisheMapper mealDisheDao;

    public MealDisheEntity queryObject(Integer id) {
        return mealDisheDao.queryObject(id);
    }

    public List<MealDisheEntity> queryList(Map<String, Object> map) {
        return mealDisheDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return mealDisheDao.queryTotal(map);
    }

    public int save(MealDisheEntity mealDishe) {
        return mealDisheDao.save(mealDishe);
    }

    public int update(MealDisheEntity mealDishe) {
        return mealDisheDao.update(mealDishe);
    }

    public int delete(Integer id) {
        return mealDisheDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return mealDisheDao.deleteBatch(ids);
    }
}
