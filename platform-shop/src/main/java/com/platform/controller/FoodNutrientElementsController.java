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

import com.platform.entity.FoodNutrientElementsEntity;
import com.platform.service.FoodNutrientElementsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 食材营养元素对应表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:26:15
 */
@RestController
@RequestMapping("foodnutrientelements")
public class FoodNutrientElementsController {
    @Autowired
    private FoodNutrientElementsService foodNutrientElementsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("foodnutrientelements:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FoodNutrientElementsEntity> foodNutrientElementsList = foodNutrientElementsService.queryList(query);
        int total = foodNutrientElementsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(foodNutrientElementsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("foodnutrientelements:info")
    public R info(@PathVariable("id") Integer id) {
        FoodNutrientElementsEntity foodNutrientElements = foodNutrientElementsService.queryObject(id);

        return R.ok().put("foodNutrientElements", foodNutrientElements);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("foodnutrientelements:save")
    public R save(@RequestBody FoodNutrientElementsEntity foodNutrientElements) {
        foodNutrientElementsService.save(foodNutrientElements);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("foodnutrientelements:update")
    public R update(@RequestBody FoodNutrientElementsEntity foodNutrientElements) {
        foodNutrientElementsService.update(foodNutrientElements);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("foodnutrientelements:delete")
    public R delete(@RequestBody Integer[] ids) {
        foodNutrientElementsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<FoodNutrientElementsEntity> list = foodNutrientElementsService.queryList(params);

        return R.ok().put("list", list);
    }
}
