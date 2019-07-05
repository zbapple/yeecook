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

import com.platform.entity.FoodMaterialEntity;
import com.platform.service.FoodMaterialService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 食材表

Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:28:09
 */
@RestController
@RequestMapping("foodmaterial")
public class FoodMaterialController {
    @Autowired
    private FoodMaterialService foodMaterialService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("foodmaterial:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FoodMaterialEntity> foodMaterialList = foodMaterialService.queryList(query);
        int total = foodMaterialService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(foodMaterialList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("foodmaterial:info")
    public R info(@PathVariable("id") Integer id) {
        FoodMaterialEntity foodMaterial = foodMaterialService.queryObject(id);

        return R.ok().put("foodMaterial", foodMaterial);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("foodmaterial:save")
    public R save(@RequestBody FoodMaterialEntity foodMaterial) {
        foodMaterialService.save(foodMaterial);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("foodmaterial:update")
    public R update(@RequestBody FoodMaterialEntity foodMaterial) {
        foodMaterialService.update(foodMaterial);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("foodmaterial:delete")
    public R delete(@RequestBody Integer[] ids) {
        foodMaterialService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<FoodMaterialEntity> list = foodMaterialService.queryList(params);

        return R.ok().put("list", list);
    }
}
