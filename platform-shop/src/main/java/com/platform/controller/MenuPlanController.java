package com.platform.controller;


import java.util.*;
import java.util.List;

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
@RequestMapping("menuplan")
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
    @RequiresPermissions("menuplan:list")
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
        Date scs=menuPlanEntity.getServiceCycleSt();
        Date sce=menuPlanEntity.getServiceCycleEt();
        Date mt=menuPlanEntity.getMealTime();
        UserEntity userEntity=userService.queryObject(uid);
        menuPlanEntity.setUserName(userEntity.getUsername());
        HashMap menumap=new HashMap();
        //从session获取当前管理员账户
        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        menumap.put("sysuser",user);
        menumap.put("weight",userHealthReportService.queryWeight(uid));
        menumap.put("infomsg",menuPlanService.queryMenu(uid));
        menumap.put("serviceCycleSt",DateUtils.format(scs, DateUtils.DATE_PATTERN));
        menumap.put("serviceCycleEt",DateUtils.format(sce, DateUtils.DATE_PATTERN));
        menumap.put("mealTime",DateUtils.format(mt, DateUtils.DATE_TIME_PATTERN));
        HashMap map=new HashMap();
        map.put("userid",uid);
        menumap.put("menutype",menuDetailsService.queryListvo(map));


        return R.ok().put("menumap",menumap);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("menuplan:info")
    public R info(@PathVariable("id") Integer id) {
       MenuPlanEntity menuPlan=menuPlanService.queryObject(id);

        return R.ok().put("menuPlan", menuPlan);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("menuplan:save")
    public R save(@RequestBody MenuPlanEntity menuPlan) {
        menuPlanService.save(menuPlan);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("menuplan:update")
    public R update(@RequestBody MenuPlanEntity menuPlan) {
        menuPlanService.update(menuPlan);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("menuplan:delete")
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
     *  更改签约状态
     **/
    public R updatestatus(@RequestBody Integer id){
        menuPlanService.updatestatus(id);
        return R.ok();
    }



}
