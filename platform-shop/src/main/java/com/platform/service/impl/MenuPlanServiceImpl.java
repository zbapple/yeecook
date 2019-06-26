package com.platform.service.impl;

import com.platform.dao.*;
import com.platform.entity.UserEntity;
import com.platform.service.UserNutritionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.entity.MenuPlanEntity;
import com.platform.service.MenuPlanService;

/**
 * 用户膳食计划Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-19 19:14:23
 */
@Service("menuPlanService")
public class MenuPlanServiceImpl implements MenuPlanService {
    @Autowired
    private MenuPlanDao menuPlanDao;

    @Override
    public MenuPlanEntity queryObject(Integer id) {
        return menuPlanDao.queryObject(id);
    }

    @Override
    public List<MenuPlanEntity> queryList(Map<String, Object> map) {
        return menuPlanDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return menuPlanDao.queryTotal(map);
    }

    @Override
    public int save(MenuPlanEntity menuPlan) {
        return menuPlanDao.save(menuPlan);
    }

    @Override
    public int update(MenuPlanEntity menuPlan) {
        return menuPlanDao.update(menuPlan);
    }

    @Override
    public int delete(Integer id) {
        return menuPlanDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return menuPlanDao.deleteBatch(ids);
    }

    @Override
    public MenuPlanEntity queryObjectA(Integer id) {
        return menuPlanDao.queryObjectA(id);
    }

    @Override
    public List<MenuPlanEntity> queryListA(Map<String, Object> map) {
        return menuPlanDao.queryListA(map);
    }

    @Override
    public int queryTotalA(Map<String, Object> map) {
            return menuPlanDao.queryTotalA(map);
    }
}
