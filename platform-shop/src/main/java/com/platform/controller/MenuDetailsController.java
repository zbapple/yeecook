package com.platform.controller;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.platform.entity.MenuDetailsDemo;
import com.platform.entity.UserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.MenuDetailsEntity;
import com.platform.service.MenuDetailsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 餐单详情表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:23:22
 */
@RestController
@RequestMapping("menuDetails")
public class MenuDetailsController {
    @Autowired
    private MenuDetailsService menuDetailsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("menudetails:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<MenuDetailsEntity> menuDetailsList = menuDetailsService.queryList(query);
        int total = menuDetailsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(menuDetailsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("menudetails:info")
    public R info(@PathVariable("id") Integer id) {
        MenuDetailsEntity menuDetails = menuDetailsService.queryObject(id);

        return R.ok().put("menuDetails", menuDetails);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("menudetails:save")
    public R save(@RequestBody MenuDetailsEntity menuDetails) {
        menuDetailsService.save(menuDetails);

        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("menudetails:update")
    public R update(@RequestBody MenuDetailsEntity menuDetails) {
        menuDetailsService.update(menuDetails);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("menudetails:delete")
    public R delete(@RequestBody Integer[] ids) {
        menuDetailsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<MenuDetailsEntity> list = menuDetailsService.queryList(params);

        return R.ok().put("list", list);
    }

//    /**
//     *  餐单详情
//     **/
//    public R menuinfo(@RequestBody MenuDetailsEntity me) {
//        Map<String, Object> result = new HashMap<>();
//        UserEntity userEntity = new UserEntity();
//        Date today=me.getMenuDate();
//        Integer userid = userEntity.getId();
//        Map dayfoodmap = new HashMap();
//        dayfoodmap.put("userid", userid);
//        dayfoodmap.put("today",today);
//        List<MenuDetailsEntity> userlist = menuDetailsService.queryList(dayfoodmap);
//        String namegood = null;
//        String nameluch = null;
//        String nameeve = null;
//        Double countgood = 0.0;
//        Double sumcalluch = 0.0;
//        Double couteve = 0.0;
//        Double sum = 0.0;
//        if (userlist != null) {
//            for (MenuDetailsEntity MenuDetailsVoItem : userlist) {
//                if (MenuDetailsVoItem.getMenuType().equals("0") && userid.equals(MenuDetailsVoItem.getId())) {
//                    countgood += MenuDetailsVoItem.getDishesCalories();
//                    namegood = MenuDetailsVoItem.getDishesName();
//                } else if (MenuDetailsVoItem.getMenuType().equals("1") && userid.equals(MenuDetailsVoItem.getNideshopUserId())) {
//                    sumcalluch += MenuDetailsVoItem.getDishesCalories();
//                    nameluch = MenuDetailsVoItem.getDishesName();
//                } else if (MenuDetailsVoItem.getMenuType().equals("2") && userid.equals(MenuDetailsVoItem.getNideshopUserId())) {
//                    couteve += MenuDetailsVoItem.getDishesCalories();
//                    nameeve = MenuDetailsVoItem.getDishesName();
//                }
//            }
//            sum = countgood + sumcalluch + couteve;
//            Map namemap=new HashMap();
//            namemap.put("namegood",namegood);
//            Map namemap1=new HashMap();
//            namemap1.put("nameluch",nameluch);
//            Map namemap2=new HashMap();
//            namemap2.put("nameeve",nameeve);
//            result.put("namemap", namemap);
//            result.put("countgood", countgood);
//            result.put("namemap1", namemap1);
//            result.put("sumcalluch", sumcalluch);
//            result.put("namemap2", namemap2);
//            result.put("couteve", couteve);
//            result.put("sum", sum);
//            result.put("flag", 1);
//            return R.ok(result);
//        } else {
//            result.put("flag", 0);
//            return R.ok(result);
//        }
//    }

    /**
     *  查看今日菜谱
     **/
    @RequestMapping("/todayInfo")
    public R todayinfo(@RequestBody MenuDetailsEntity me) {
        Date todays=me.getMenuDate();
        Integer menuId=me.getUserNutritionMenuId();
        Map todaymap = new HashMap();
        todaymap.put("userNutritionMenuId", menuId);
        todaymap.put("todays",todays);
        List<MenuDetailsEntity> menuDetailsVoList = menuDetailsService.queryListvo(todaymap); //查询出菜谱所有菜品信息
        List<MenuDetailsDemo> apiMenuDetaileVos = new ArrayList<>();    //传给前端的数据格式
        Double breakfastcal=0.0; //早餐总能量
        Double lunchcal=0.0;     //午餐总能量
        Double dinnercal=0.0;    //晚餐总能量

        Date breakfasttime = null;   //早餐时间段
        Date lunchtime = null;       //午餐时间段
        Date dinnertime = null;      //晚餐时间段

        Double breakfastSnackscal=0.0; //加餐早餐总能量
        Double dinnerSnackscal=0.0;    //加餐晚餐总能量
        Double lunchSnackscal=0.0;     //加餐午餐总能量

        Double breakfastsumcal=0.0;    //早餐与加餐总能量
        Double lunchsumcal=0.0;        //午餐与加餐总能量
        Double dinnersumcal=0.0;       //晚餐与加餐总能量

        List<Map>  zhengcan_0=new ArrayList<>();   //正餐早餐集合
        List<Map>  zhengcan_1=new ArrayList<>();   //正餐中餐集合
        List<Map>  zhengcan_2=new ArrayList<>();   //正餐晚餐集合

        List<Map> jiacan_3=new ArrayList<>();      //加餐早餐集合
        List<Map> jiacan_4=new ArrayList<>();      //加餐中餐集合
        List<Map> jiacan_5=new ArrayList<>();      //加餐晚餐集合

        //迭代总的餐单菜品
        for (MenuDetailsEntity menuDetailsVoItem : menuDetailsVoList) {

            //归类早中晚菜品数
            switch (menuDetailsVoItem.getMenuType()) {
                case "0":
                    Map breakfast=new HashMap();
                    breakfast.put("dishescoverpic",menuDetailsVoItem.getDishesCoverPic());
                    breakfasttime=menuDetailsVoItem.getMealTime();
                    breakfast.put("dishesname",menuDetailsVoItem.getDishesName());
                    breakfast.put("breakfastcal",menuDetailsVoItem.getDishesCalories());
                    breakfastcal+=menuDetailsVoItem.getDishesCalories();
                    zhengcan_0.add(breakfast);
                    break;
                case "1":
                    Map lunch=new HashMap();
                    lunch.put("dishescoverpic",menuDetailsVoItem.getDishesCoverPic());
                    lunchtime=menuDetailsVoItem.getMealTime();
                    lunch.put("dishesname",menuDetailsVoItem.getDishesName());
                    lunch.put("lunchcal",menuDetailsVoItem.getDishesCalories());
                    lunchcal+=menuDetailsVoItem.getDishesCalories();
                    zhengcan_1.add(lunch);
                    break;
                case "2":
                    Map dinner=new HashMap();
                    dinner.put("dishescoverpic",menuDetailsVoItem.getDishesCoverPic());
                    dinnertime=menuDetailsVoItem.getMealTime();
                    dinner.put("dishesname",menuDetailsVoItem.getDishesName());
                    dinner.put("dinnercal",menuDetailsVoItem.getDishesCalories());
                    dinnercal+=menuDetailsVoItem.getDishesCalories();
                    zhengcan_2.add(dinner);
                    break;
                case "3":
                    Map breakfastSnacks=new HashMap();
                    breakfastSnacks.put("dishescoverpic",menuDetailsVoItem.getDishesCoverPic());
                    breakfastSnacks.put("dishesname",menuDetailsVoItem.getDishesName());
                    breakfastSnacks.put("breakfastSnackscal",menuDetailsVoItem.getDishesCalories());
                    breakfastSnackscal+=menuDetailsVoItem.getDishesCalories();
                    jiacan_3.add(breakfastSnacks);
                    break;
                case "4":
                    Map lunchSnacks=new HashMap();
                    lunchSnacks.put("dishescoverpic",menuDetailsVoItem.getDishesCoverPic());
                    lunchSnacks.put("dishesname",menuDetailsVoItem.getDishesName());
                    lunchSnacks.put("lunchsumcal",menuDetailsVoItem.getDishesCalories());
                    lunchsumcal+=menuDetailsVoItem.getDishesCalories();
                    jiacan_4.add(lunchSnacks);
                    break;
                case "5":
                    Map dinnerSnacks=new HashMap();
                    dinnerSnacks.put("dishescoverpic",menuDetailsVoItem.getDishesCoverPic());
                    dinnerSnacks.put("dishesname",menuDetailsVoItem.getDishesName());
                    dinnerSnacks.put("dinnerSnackscal",menuDetailsVoItem.getDishesCalories());
                    dinnerSnackscal+=menuDetailsVoItem.getDishesCalories();
                    jiacan_5.add(dinnerSnacks);
                    break;

            }

        }

        breakfastsumcal=breakfastcal+breakfastSnackscal;
        lunchsumcal=lunchSnackscal+lunchcal;
        dinnersumcal=dinnerSnackscal+dinnercal;

        MenuDetailsDemo apiMenuDetaileVo_1=new MenuDetailsDemo();
        apiMenuDetaileVo_1.setMenuType("0");
        apiMenuDetaileVo_1.setTime(breakfasttime);
        apiMenuDetaileVo_1.setSumcal(breakfastsumcal);
        apiMenuDetaileVo_1.setJiacan(jiacan_3);
        apiMenuDetaileVo_1.setZhengcan(zhengcan_0);
        MenuDetailsDemo apiMenuDetaileVo_2=new MenuDetailsDemo();
        apiMenuDetaileVo_2.setMenuType("1");
        apiMenuDetaileVo_2.setTime(lunchtime);
        apiMenuDetaileVo_2.setSumcal(lunchsumcal);
        apiMenuDetaileVo_2.setJiacan(jiacan_4);
        apiMenuDetaileVo_2.setZhengcan(zhengcan_1);
        MenuDetailsDemo apiMenuDetaileVo_3=new MenuDetailsDemo();
        apiMenuDetaileVo_3.setMenuType("2");
        apiMenuDetaileVo_3.setTime(dinnertime);
        apiMenuDetaileVo_3.setSumcal(dinnersumcal);
        apiMenuDetaileVo_3.setJiacan(jiacan_5);
        apiMenuDetaileVo_3.setZhengcan(zhengcan_2);

        apiMenuDetaileVos.add(apiMenuDetaileVo_1);
        apiMenuDetaileVos.add(apiMenuDetaileVo_2);
        apiMenuDetaileVos.add(apiMenuDetaileVo_3);

        return R.ok().put("apiMenuDetaileVos",apiMenuDetaileVos);
    }
    /**
     * 完善保存
     **/
    @RequestMapping("/saveDetails")
    public R saveDetails(@RequestBody MenuDetailsEntity menuDetails){
        if (menuDetails !=null){

        }
        return R.ok();
    }
}
