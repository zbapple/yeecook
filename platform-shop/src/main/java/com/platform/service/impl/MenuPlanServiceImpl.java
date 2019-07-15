package com.platform.service.impl;

import com.platform.dao.*;
import com.platform.entity.*;
import com.platform.service.MenuDetailsService;
import com.platform.service.UserNutritionMenuService;
import com.platform.utils.DateUtils;
import com.platform.utils.RRException;
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
    @Autowired
    private UserHealthReportDao userHealthReportDao;


    @Override
    public MenuPlanEntity queryMenu(Integer id) {
        return menuPlanDao.queryMenu(id);
    }

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
    @Transactional
    public int save(MenuPlanEntity menuPlan) {
//        //插入用户信息
//        UserEntity userEntity = new UserEntity();
//        menuPlan.setNideshopUserId(userEntity.getId());
//        menuPlan.setUserName(userEntity.getUsername());
//
//
//       //保存餐品详情
//        MenuDetailsEntity me=new MenuDetailsEntity();
//        me.setUserNutritionMenuId(menuPlan.getId());
//        me.setMenuType(menuPlan.getMenuType());
//        me.setDishesName(menuPlan.getDishesName());
//        me.setMealTime(menuPlan.getMealTime());
//        me.setMenuDate(menuPlan.getMenuDate());
//        menuDetailsDao.save(me);
//
//       //保存周期
//        UserDetectionCycleEntity uce=new UserDetectionCycleEntity();
//        uce.setInspectionCycle(menuPlan.getInspectionCycle());
//        userDetectionCycleDao.save(uce);
//
//        //商品
//
//        menuPlan.setMenuSn(menuPlan.getNewSn());
        //插入用户
//        Integer id=menuPlan.getNideshopUserId();
//        List<MenuPlanEntity> ulist=menuPlan.getUserNames();
//        if (ulist.size()>0){
//            for () {
//                menuPlan.setNideshopUserId(id);
////                menuPlanDao.save(menuPlan);
//            }
//        }
        //插入用户信息
        String nickname=menuPlan.getNickName();
        UserEntity userEntity=userDao.queryObject(nickname);
        Integer ids=userEntity.getId();
        menuPlan.setNideshopUserId(ids);
        menuPlanDao.save(menuPlan);

        //保存餐品详情
        Integer id=menuPlan.getId();
//        MenuDetailsEntity md=menuDetailsDao.queryObject(id);
        List<MenuDetailsEntity> mdlist=menuPlan.getFoodlist();
        List<MenuDetailsEntity> mdlist1=menuPlan.getFoodlistadd();
        List<MenuDetailsEntity> mdlist2=menuPlan.getFoodlist1();
        List<MenuDetailsEntity> mdlist3=menuPlan.getFoodlistadd1();
        List<MenuDetailsEntity> mdlist4=menuPlan.getFoodlist2();
        List<MenuDetailsEntity> mdlist5=menuPlan.getFoodlistadd2();
        if (null!=mdlist && mdlist.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist){
                menuDetailsEntity.setUserNutritionMenuId(id);
                menuDetailsEntity.setMenuType("0");
//                menuDetailsEntity.setDishesName(dishesname);
                menuDetailsDao.save(menuDetailsEntity);
            }
        }
        if (null!=mdlist1 && mdlist1.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist1){
                menuDetailsEntity.setUserNutritionMenuId(id);
                menuDetailsEntity.setMenuType("3");
//                menuDetailsEntity.setDishesName(dishesname);
                menuDetailsDao.save(menuDetailsEntity);
            }
        }
        if (null!=mdlist2 && mdlist2.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist2){
                menuDetailsEntity.setUserNutritionMenuId(id);
                menuDetailsEntity.setMenuType("1");
//                menuDetailsEntity.setDishesName(dishesname);
                menuDetailsDao.save(menuDetailsEntity);
            }
        }
        if (null!=mdlist3 && mdlist3.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist3){
                menuDetailsEntity.setUserNutritionMenuId(id);
                menuDetailsEntity.setMenuType("4");
//                menuDetailsEntity.setDishesName(dishesname);
                menuDetailsDao.save(menuDetailsEntity);
            }
        }
        if (null!=mdlist4 && mdlist4.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist4){
                menuDetailsEntity.setUserNutritionMenuId(id);
                menuDetailsEntity.setMenuType("2");
//                menuDetailsEntity.setDishesName(dishesname);
                menuDetailsDao.save(menuDetailsEntity);
            }

        }
        if (null!=mdlist5 && mdlist5.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist5){
                menuDetailsEntity.setUserNutritionMenuId(id);
                menuDetailsEntity.setMenuType("5");
//                menuDetailsEntity.setDishesName(dishesname);
                menuDetailsDao.save(menuDetailsEntity);
            }

        }

        //轮播图
        List<MenuPlanEntity> llist=menuPlan.getMenuCoverPics();
        if (llist.size()>0){
            for (MenuPlanEntity mp:llist){
                mp.setMenuCoverPic(menuPlan.getMenuCoverPic());
                menuPlanDao.save(mp);
            }
        }
        return menuPlanDao.save(menuPlan);
    }

    @Override
    public int update(Integer id) {
        MenuPlanEntity menuPlan=menuPlanDao.queryObject(id);
        Integer menustatus=menuPlan.getMenuStatus();
        menuPlan.setMenuStatus(1);
//        if (0 == menustatus){
//            throw new RRException("当前状态未审核");
//        }
        if (1 == menustatus){
            throw new RRException("当前状态已审核");
        }
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

//    /**
//     * 更改状态
//     **/
//    @Override
//    public int updatestatus(MenuPlanEntity menuPlan) {
//        Integer id=menuPlan.getId();
//        Integer menustatus=menuPlan.getMenuStatus();//审核状态
//        if (0 == menustatus){
//            throw new RRException("当前状态未审核");
//        }
//        if (1 == menustatus){
//            throw new RRException("当前状态已审核");
//        }
//        menuPlan.setMenuStatus(1);
//        return menuPlanDao.update(menuPlan);
//    }

}
