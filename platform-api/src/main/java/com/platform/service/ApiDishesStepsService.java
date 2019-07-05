package com.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ApiDishesStepsMapper;
import com.platform.entity.DishesStepsVo;

/**
 * 菜品步骤表
 id
 序号
 图片
 步骤描述
 菜品idService实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:31
 */
@Service
public class ApiDishesStepsService {
    @Autowired
    private ApiDishesStepsMapper dishesStepsDao;

    public DishesStepsVo queryObject(Integer id) {
        return dishesStepsDao.queryObject(id);
    }

    public List<DishesStepsVo> queryList(Map<String, Object> map) {
        return dishesStepsDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return dishesStepsDao.queryTotal(map);
    }

    public int save(DishesStepsVo dishesSteps) {
        return dishesStepsDao.save(dishesSteps);
    }

    public int update(DishesStepsVo dishesSteps) {
        return dishesStepsDao.update(dishesSteps);
    }

    public int delete(Integer id) {
        return dishesStepsDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return dishesStepsDao.deleteBatch(ids);
    }
}

