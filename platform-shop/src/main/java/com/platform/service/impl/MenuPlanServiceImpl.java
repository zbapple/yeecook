package com.platform.service.impl;

import com.platform.dao.*;
import com.platform.entity.*;
import com.platform.service.MenuDetailsService;
import com.platform.service.UserNutritionMenuService;
import com.platform.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

import com.platform.service.MenuPlanService;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private UserDao userDao;
    @Autowired
    private MenuDetailsDao menuDetailsDao;
    @Autowired
    private UserNutritionMenuDao userNutritionMenuDao;
    @Autowired
    private UserDetectionCycleDao userDetectionCycleDao;

    @Override
    public MenuPlanEntity queryObject(Integer id) {
        MenuPlanEntity menuPlanEntity=new MenuPlanEntity();
        Integer mid= menuPlanEntity.getId();
        HashMap map=new HashMap();
        





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
    @Transactional
    public int save(MenuPlanEntity menuPlan) {
        //插入用户id
        UserEntity userEntity = new UserEntity();
        menuPlan.setNideshopUserId(userEntity.getId());

       //保存餐品详情
        MenuDetailsEntity me=new MenuDetailsEntity();
        me.setUserNutritionMenuId(menuPlan.getId());
        me.setMenuType(menuPlan.getMenuType());
        me.setDishesName(menuPlan.getDishesName());
        me.setMealTime(menuPlan.getMealTime());
        me.setMenuDate(menuPlan.getMenuDate());
        menuDetailsDao.save(me);










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
    public int updatestatus(Integer id) {
        MenuPlanEntity menuPlanEntity=queryObject(id);
        Integer menustatus=menuPlanEntity.getMenuStatus();//审核状态
        if (0 == menustatus){
            System.out.println("当前状态未审核");
        }
        if (1 == menustatus){
            System.out.println("当前状态已审核");
        }
        menuPlanEntity.setMenuStatus(1);
        return menuPlanDao.update(menuPlanEntity);
    }


    /**
     * 自定义餐单编码格式  服务机构id+年月日+餐单id
     **/
    public static String getNewSn(){
        Calendar cl=Calendar.getInstance();
        cl.setTime(new Date());
        String strdate = DateUtils.format(cl.getTime(), DateUtils.DATE_PATTERN);
        MenuPlanEntity menuPlanEntity=new MenuPlanEntity();
        Integer cid=menuPlanEntity.getCateringServiceOrgId();
        Integer id=menuPlanEntity.getId();
        String str=cid+strdate+id;
        menuPlanEntity.setMenuSn(str);
        return str;
    }


}
