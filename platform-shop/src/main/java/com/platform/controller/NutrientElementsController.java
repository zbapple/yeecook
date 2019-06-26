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

import com.platform.entity.NutrientElementsEntity;
import com.platform.service.NutrientElementsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 营养元素表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:22:12
 */
@RestController
@RequestMapping("nutrientelements")
public class NutrientElementsController {
    @Autowired
    private NutrientElementsService nutrientElementsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("nutrientelements:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<NutrientElementsEntity> nutrientElementsList = nutrientElementsService.queryList(query);
        int total = nutrientElementsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(nutrientElementsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("nutrientelements:info")
    public R info(@PathVariable("id") Integer id) {
        NutrientElementsEntity nutrientElements = nutrientElementsService.queryObject(id);

        return R.ok().put("nutrientElements", nutrientElements);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("nutrientelements:save")
    public R save(@RequestBody NutrientElementsEntity nutrientElements) {
        nutrientElementsService.save(nutrientElements);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("nutrientelements:update")
    public R update(@RequestBody NutrientElementsEntity nutrientElements) {
        nutrientElementsService.update(nutrientElements);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("nutrientelements:delete")
    public R delete(@RequestBody Integer[] ids) {
        nutrientElementsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<NutrientElementsEntity> list = nutrientElementsService.queryList(params);

        return R.ok().put("list", list);
    }
}
