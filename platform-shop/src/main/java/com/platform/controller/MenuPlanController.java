package com.platform.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.entity.MenuDetailsEntity;
import com.platform.entity.UserEntity;
import com.platform.entity.UserNutritionMenuEntity;
import com.platform.service.*;
import com.platform.utils.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.MenuPlanEntity;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

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
    private  MenuDetailsService menuDetailsService;
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
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("menuplan:info")
    public R info(@PathVariable("id") Integer id) {
        MenuPlanEntity menuPlan = menuPlanService.queryObject(id);
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
