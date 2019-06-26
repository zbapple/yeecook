package com.platform.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.DishesStepsEntity;
import com.platform.service.DishesStepsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 菜品步骤表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:31:59
 */
@RestController
@RequestMapping("dishessteps")
public class DishesStepsController {
    @Autowired
    private DishesStepsService dishesStepsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dishessteps:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<DishesStepsEntity> dishesStepsList = dishesStepsService.queryList(query);
        int total = dishesStepsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(dishesStepsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dishessteps:info")
    public R info(@PathVariable("id") Integer id) {
        DishesStepsEntity dishesSteps = dishesStepsService.queryObject(id);

        return R.ok().put("dishesSteps", dishesSteps);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dishessteps:save")
    public R save(@RequestBody DishesStepsEntity dishesSteps) {
        dishesStepsService.save(dishesSteps);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dishessteps:update")
    public R update(@RequestBody DishesStepsEntity dishesSteps) {
        dishesStepsService.update(dishesSteps);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dishessteps:delete")
    public R delete(@RequestBody Integer[] ids) {
        dishesStepsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<DishesStepsEntity> list = dishesStepsService.queryList(params);

        return R.ok().put("list", list);
    }
}
