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

import com.platform.entity.DishesEntity;
import com.platform.service.DishesService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 菜品表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:33:22
 */
@RestController
@RequestMapping("dishes")
public class DishesController {
    @Autowired
    private DishesService dishesService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dishes:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<DishesEntity> dishesList = dishesService.queryList(query);
        int total = dishesService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(dishesList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dishes:info")
    public R info(@PathVariable("id") Integer id) {
        DishesEntity dishes = dishesService.queryObject(id);

        return R.ok().put("dishes", dishes);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dishes:save")
    public R save(@RequestBody DishesEntity dishes) {
        dishesService.save(dishes);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dishes:update")
    public R update(@RequestBody DishesEntity dishes) {
        dishesService.update(dishes);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dishes:delete")
    public R delete(@RequestBody Integer[] ids) {
        dishesService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<DishesEntity> list = dishesService.queryList(params);

        return R.ok().put("list", list);
    }
}
