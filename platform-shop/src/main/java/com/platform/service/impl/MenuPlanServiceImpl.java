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
        //插入用户信息
        String nickname=menuPlan.getNickName();
        UserEntity userEntity=userDao.queryObject(nickname);
        Integer ids=userEntity.getId();
        menuPlan.setNideshopUserId(ids);
        //当前时间
        Calendar cl=Calendar.getInstance();
        cl.setTime(new Date());
        String menudatestr=DateUtils.format(cl.getTime(), DateUtils.DATE_PATTERN);
        Date menudate=DateUtils.strToDate(menudatestr);
        //传入阶段状态标识
        String stages=menuPlan.getServiceStage();
        if (stages.equals("0")){
            menuPlan.setServiceStage("第一周疗养阶段");
        }else if (stages.equals("1")){
            menuPlan.setServiceStage("第二周疗养阶段");
        }else if (stages.equals("2")){
            menuPlan.setServiceStage("第三周疗养阶段");
        }else{
            menuPlan.setServiceStage("第四周疗养阶段");
        }
        //传入餐单类型
        String type=menuPlan.getNutritionMenuType();
        if (type.equals("1")){
            menuPlan.setNutritionMenuType("月子餐类型A");
        }else{
            menuPlan.setNutritionMenuType("月子餐类型B");
        }
        //餐单封面图
        List<MenuPlanEntity> llist=menuPlan.getMenuCoverPics();
        if (llist !=null &&llist.size()>0){
            for (MenuPlanEntity menuPlanEntity:llist){
                menuPlan.setMenuCoverPic(menuPlanEntity.getMenuCoverPic());
            }
        }
        menuPlanDao.save(menuPlan);
        //保存餐品详情

        Integer id=menuPlan.getId();
        List<MenuDetailsEntity> mdlist=menuPlan.getFoodlist();
        List<MenuDetailsEntity> mdlist1=menuPlan.getFoodlist1();
        List<MenuDetailsEntity> mdlist2=menuPlan.getFoodlist2();
        List<MenuDetailsEntity> mdlist3=menuPlan.getFoodlistadd();
        List<MenuDetailsEntity> mdlist4=menuPlan.getFoodlistadd1();
        List<MenuDetailsEntity> mdlist5=menuPlan.getFoodlistadd2();
        if (null!=mdlist && mdlist.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist){
                if (menuDetailsEntity.getDishesName() != null && menuDetailsEntity.getDishesId()!=null) {
                    menuDetailsEntity.setUserNutritionMenuId(id);
                    menuDetailsEntity.setMenuType("0");
                    menuDetailsEntity.setMenuDate(menudate);
                    menuDetailsDao.save(menuDetailsEntity);
                }
            }
        }

        if (null!=mdlist1 && mdlist1.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist1) {
                if (menuDetailsEntity.getDishesName() != null && menuDetailsEntity.getDishesId() != null) {
                    menuDetailsEntity.setUserNutritionMenuId(id);
                    menuDetailsEntity.setMenuType("1");
                    menuDetailsEntity.setMenuDate(menudate);
                    menuDetailsDao.save(menuDetailsEntity);
                }
            }
        }
        if (null!=mdlist2 && mdlist2.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist2) {
                if (menuDetailsEntity.getDishesName() != null && menuDetailsEntity.getDishesId() != null) {
                    menuDetailsEntity.setUserNutritionMenuId(id);
                    menuDetailsEntity.setMenuType("2");
                    menuDetailsEntity.setMenuDate(menudate);
                    menuDetailsDao.save(menuDetailsEntity);
                }
            }
        }
        if (null!=mdlist3 && mdlist3.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist3) {

                if (menuDetailsEntity.getDishesName()!=null && menuDetailsEntity.getDishesId()!=null) {
                    menuDetailsEntity.setUserNutritionMenuId(id);
                    menuDetailsEntity.setMenuType("3");
                    menuDetailsEntity.setMenuDate(menudate);
                    menuDetailsDao.save(menuDetailsEntity);
                }
            }
        }
        if (null!=mdlist4 && mdlist4.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist4) {
                if (menuDetailsEntity.getDishesName() != null && menuDetailsEntity.getDishesId()!=null) {
                    menuDetailsEntity.setUserNutritionMenuId(id);
                    menuDetailsEntity.setMenuType("4");
                    menuDetailsEntity.setMenuDate(menudate);
                    menuDetailsDao.save(menuDetailsEntity);
                }
            }
        }
        if (null!=mdlist5 && mdlist5.size()>0){
            for (MenuDetailsEntity menuDetailsEntity:mdlist5) {
                if (menuDetailsEntity.getDishesName() != null && menuDetailsEntity.getDishesId()!=null) {
                    menuDetailsEntity.setUserNutritionMenuId(id);
                    menuDetailsEntity.setMenuType("5");
                    menuDetailsEntity.setMenuDate(menudate);
                    menuDetailsDao.save(menuDetailsEntity);
                }
            }
        }

        return id;
    }

    @Override
    public int update(Integer id) {
        MenuPlanEntity menuPlan=menuPlanDao.queryObject(id);
        Integer menustatus=menuPlan.getMenuStatus();
        menuPlan.setMenuStatus(1);
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

    @Override
    public void updateinfo(MenuPlanEntity menuPlan) {

         menuPlanDao.update(menuPlan);
    }


}
