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

import com.platform.entity.NewNutrientsEntity;
import com.platform.service.NewNutrientsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 14:49:30
 */
@RestController
@RequestMapping("newnutrients")
public class NewNutrientsController {
    @Autowired
    private NewNutrientsService newNutrientsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("newnutrients:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<NewNutrientsEntity> newNutrientsList = newNutrientsService.queryList(query);
        int total = newNutrientsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(newNutrientsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("newnutrients:info")
    public R info(@PathVariable("id") Integer id) {
        NewNutrientsEntity newNutrients = newNutrientsService.queryObject(id);

        return R.ok().put("newNutrients", newNutrients);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("newnutrients:save")
    public R save(@RequestBody NewNutrientsEntity newNutrients) {
        newNutrientsService.save(newNutrients);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("newnutrients:update")
    public R update(@RequestBody NewNutrientsEntity newNutrients) {
        newNutrientsService.update(newNutrients);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("newnutrients:delete")
    public R delete(@RequestBody Integer[] ids) {
        newNutrientsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<NewNutrientsEntity> list = newNutrientsService.queryList(params);

        return R.ok().put("list", list);
    }
}
