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

import com.platform.entity.FoodIngredientsEntity;
import com.platform.service.FoodIngredientsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 菜品食材表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:29:33
 */
@RestController
@RequestMapping("foodingredients")
public class FoodIngredientsController {
    @Autowired
    private FoodIngredientsService foodIngredientsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("foodingredients:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FoodIngredientsEntity> foodIngredientsList = foodIngredientsService.queryList(query);
        int total = foodIngredientsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(foodIngredientsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("foodingredients:info")
    public R info(@PathVariable("id") Integer id) {
        FoodIngredientsEntity foodIngredients = foodIngredientsService.queryObject(id);

        return R.ok().put("foodIngredients", foodIngredients);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("foodingredients:save")
    public R save(@RequestBody FoodIngredientsEntity foodIngredients) {
        foodIngredientsService.save(foodIngredients);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("foodingredients:update")
    public R update(@RequestBody FoodIngredientsEntity foodIngredients) {
        foodIngredientsService.update(foodIngredients);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("foodingredients:delete")
    public R delete(@RequestBody Integer[] ids) {
        foodIngredientsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<FoodIngredientsEntity> list = foodIngredientsService.queryList(params);

        return R.ok().put("list", list);
    }
}
