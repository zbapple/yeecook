package com.platform.controller;


import java.util.*;
import java.util.List;

import com.google.protobuf.DescriptorProtos;
import com.platform.annotation.DataFilter;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户膳食计划Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-19 19:14:23
 */
@RestController
@RequestMapping("menuPlan")
public class MenuPlanController {
    @Autowired
    private MenuPlanService menuPlanService;
    @Autowired
    private  UserService userService;
    @Autowired
    private  MenuDetailsService menuDetailsService;
    @Autowired
    private  UserHealthReportService userHealthReportService;
    @Autowired
    private  SysUserService sysUserService;
    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("menuPlan:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<MenuPlanEntity> menuPlanList = menuPlanService.queryList(query);

        int total = menuPlanService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(menuPlanList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
    /**
     *  查看餐单详情
     *
     **/
    @RequestMapping("/menuinfo/{id}")
    public R menuinfo(@PathVariable("id") Integer id){
        MenuPlanEntity menuPlanEntity = menuPlanService.queryObject(id);
        Integer uid=menuPlanEntity.getNideshopUserId();
        Integer mid=menuPlanEntity.getId();
        Date scs=menuPlanEntity.getServiceCycleSt();
        Date sce=menuPlanEntity.getServiceCycleEt();
        Date mt=menuPlanEntity.getMealTime();
        HashMap menumap=new HashMap();
        //从session获取当前管理员账户
        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        menumap.put("sysuser",user);
        menumap.put("weight",userHealthReportService.queryWeight(uid));
        menumap.put("infomsg",menuPlanService.queryMenu(uid));
        menumap.put("serviceCycleSt",DateUtils.format(scs, DateUtils.DATE_PATTERN));
        menumap.put("serviceCycleEt",DateUtils.format(sce, DateUtils.DATE_PATTERN));
        HashMap map=new HashMap();
        map.put("userNutritionMenuId",mid);
        menumap.put("menutype",menuDetailsService.queryListvo(map));

        return R.ok().put("menumap",menumap);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("menuPlan:info")
    public R info(@PathVariable("id") Integer id) {
       MenuPlanEntity menuPlan=menuPlanService.queryObject(id);
       Integer mid=menuPlan.getId();
       Map map=new HashMap();
       map.put("userNutritionMenuId",mid);
       List<MenuDetailsEntity> me=menuDetailsService.queryListvo(map);
       List<MenuDetailsEntity> foodlist=new ArrayList<>();
       List<MenuDetailsEntity> foodlist1=new ArrayList<>();
       List<MenuDetailsEntity> foodlist2=new ArrayList<>();
       List<MenuDetailsEntity> foodlistadd=new ArrayList<>();
       List<MenuDetailsEntity> foodlistadd1=new ArrayList<>();
       List<MenuDetailsEntity> foodlistadd2=new ArrayList<>();
        for (MenuDetailsEntity menuDetailsEntity:me){
           switch (menuDetailsEntity.getMenuType()) {
               case "0":
                   MenuDetailsEntity menuDetailsEntity1=new MenuDetailsEntity();
                   menuDetailsEntity1.setDishesCoverPic(menuDetailsEntity.getDishesCoverPic());
                   menuDetailsEntity1.setDishesName(menuDetailsEntity.getDishesName());
                   menuDetailsEntity1.setDishesCalories(menuDetailsEntity.getDishesCalories());
                  foodlist.add(menuDetailsEntity1);
                   break;
               case "1":
                   MenuDetailsEntity menuDetailsEntity2=new MenuDetailsEntity();
                   menuDetailsEntity2.setDishesCoverPic(menuDetailsEntity.getDishesCoverPic());
                   menuDetailsEntity2.setDishesName(menuDetailsEntity.getDishesName());
                   menuDetailsEntity2.setDishesCalories(menuDetailsEntity.getDishesCalories());
                   foodlist1.add(menuDetailsEntity2);
                   break;
               case "2":
                   MenuDetailsEntity menuDetailsEntity3=new MenuDetailsEntity();
                   menuDetailsEntity3.setDishesCoverPic(menuDetailsEntity.getDishesCoverPic());
                   menuDetailsEntity3.setDishesName(menuDetailsEntity.getDishesName());
                   menuDetailsEntity3.setDishesCalories(menuDetailsEntity.getDishesCalories());
                   foodlist2.add(menuDetailsEntity3);
                   break;
               case "3":
                   MenuDetailsEntity menuDetailsEntity4=new MenuDetailsEntity();
                   menuDetailsEntity4.setDishesCoverPic(menuDetailsEntity.getDishesCoverPic());
                   menuDetailsEntity4.setDishesName(menuDetailsEntity.getDishesName());
                   menuDetailsEntity4.setDishesCalories(menuDetailsEntity.getDishesCalories());
                   foodlistadd.add(menuDetailsEntity4);
                   break;
               case "4":
                   MenuDetailsEntity menuDetailsEntity5=new MenuDetailsEntity();
                   menuDetailsEntity5.setDishesCoverPic(menuDetailsEntity.getDishesCoverPic());
                   menuDetailsEntity5.setDishesName(menuDetailsEntity.getDishesName());
                   menuDetailsEntity5.setDishesCalories(menuDetailsEntity.getDishesCalories());
                   foodlistadd1.add(menuDetailsEntity5);
                   break;
               case "5":
                   MenuDetailsEntity menuDetailsEntity6=new MenuDetailsEntity();
                   menuDetailsEntity6.setDishesCoverPic(menuDetailsEntity.getDishesCoverPic());
                   menuDetailsEntity6.setDishesName(menuDetailsEntity.getDishesName());
                   menuDetailsEntity6.setDishesCalories(menuDetailsEntity.getDishesCalories());
                   foodlistadd2.add(menuDetailsEntity6);
                   break;

           }
       }
        menuPlan.setFoodlist(foodlist);
        menuPlan.setFoodlist1(foodlist1);
        menuPlan.setFoodlist2(foodlist2);
        menuPlan.setFoodlistadd(foodlistadd);
        menuPlan.setFoodlistadd1(foodlistadd1);
        menuPlan.setFoodlistadd2(foodlistadd2);
        return R.ok().put("menuPlan", menuPlan);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("menuPlan:save")
    public R save(@RequestBody MenuPlanEntity menuPlan) {
        menuPlanService.save(menuPlan);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("menuPlan:update")
    public R update(@RequestBody Integer id) {
        menuPlanService.update(id);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("menuPlan:delete")
    public R delete(@RequestBody Integer[] ids) {
        menuPlanService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<MenuPlanEntity> list = menuPlanService.queryList(params);

        return R.ok().put("list", list);
    }


   /**
    *
    * 更新详情信息
    **/
   @RequestMapping("/updateInfo")
   @RequiresPermissions("menuPlan:updateInfo")
   public R updateinfo(@RequestBody MenuPlanEntity menuPlan){
       menuPlanService.updateinfo(menuPlan);
       return  R.ok();
   }


}
