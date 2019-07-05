package com.platform.service;

import com.platform.dao.ApiDishesMapper;
import com.platform.entity.DishesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 菜品表
 id
 菜品名称
 菜品封面图片
 菜品卡路里Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:40
 */
@Service
public class ApiDishesService  {
    @Autowired
    private ApiDishesMapper dishesDao;

    public DishesVo queryObject(Integer id) {
        return dishesDao.queryObject(id);
    }

    public List<DishesVo> queryList(Map<String, Object> map) {
        return dishesDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return dishesDao.queryTotal(map);
    }

    public int save(DishesVo dishes) {
        return dishesDao.save(dishes);
    }

    public int update(DishesVo dishes) {
        return dishesDao.update(dishes);
    }

    public int delete(Integer id) {
        return dishesDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return dishesDao.deleteBatch(ids);
    }
}

