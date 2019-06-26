package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.UserNutritionMenuDao;
import com.platform.entity.UserNutritionMenuEntity;
import com.platform.service.UserNutritionMenuService;

/**
 * 用户营养餐单表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:05:27
 */
@Service("userNutritionMenuService")
public class UserNutritionMenuServiceImpl implements UserNutritionMenuService {
    @Autowired
    private UserNutritionMenuDao userNutritionMenuDao;

    @Override
    public UserNutritionMenuEntity queryObject(Integer id) {
        return userNutritionMenuDao.queryObject(id);
    }

    @Override
    public List<UserNutritionMenuEntity> queryList(Map<String, Object> map) {
        return userNutritionMenuDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userNutritionMenuDao.queryTotal(map);
    }

    @Override
    public int save(UserNutritionMenuEntity userNutritionMenu) {
        return userNutritionMenuDao.save(userNutritionMenu);
    }

    @Override
    public int update(UserNutritionMenuEntity userNutritionMenu) {
        return userNutritionMenuDao.update(userNutritionMenu);
    }

    @Override
    public int delete(Integer id) {
        return userNutritionMenuDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return userNutritionMenuDao.deleteBatch(ids);
    }
}
