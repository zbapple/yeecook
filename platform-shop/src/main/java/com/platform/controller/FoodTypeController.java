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

import com.platform.entity.FoodTypeEntity;
import com.platform.service.FoodTypeService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 食材类型表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:25:00
 */
@RestController
@RequestMapping("foodtype")
public class FoodTypeController {
    @Autowired
    private FoodTypeService foodTypeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("foodtype:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FoodTypeEntity> foodTypeList = foodTypeService.queryList(query);
        int total = foodTypeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(foodTypeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("foodtype:info")
    public R info(@PathVariable("id") Integer id) {
        FoodTypeEntity foodType = foodTypeService.queryObject(id);

        return R.ok().put("foodType", foodType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("foodtype:save")
    public R save(@RequestBody FoodTypeEntity foodType) {
        foodTypeService.save(foodType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("foodtype:update")
    public R update(@RequestBody FoodTypeEntity foodType) {
        foodTypeService.update(foodType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("foodtype:delete")
    public R delete(@RequestBody Integer[] ids) {
        foodTypeService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<FoodTypeEntity> list = foodTypeService.queryList(params);

        return R.ok().put("list", list);
    }
}
