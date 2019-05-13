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

import com.platform.entity.MenucategoryEntity;
import com.platform.service.MenucategoryService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-07 22:28:24
 */
@RestController
@RequestMapping("menucategory")
public class MenucategoryController {
    @Autowired
    private MenucategoryService menucategoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("menucategory:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<MenucategoryEntity> menucategoryList = menucategoryService.queryList(query);
        int total = menucategoryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(menucategoryList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("menucategory:info")
    public R info(@PathVariable("id") Integer id) {
        MenucategoryEntity menucategory = menucategoryService.queryObject(id);

        return R.ok().put("menucategory", menucategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("menucategory:save")
    public R save(@RequestBody MenucategoryEntity menucategory) {
        menucategoryService.save(menucategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("menucategory:update")
    public R update(@RequestBody MenucategoryEntity menucategory) {
        menucategoryService.update(menucategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("menucategory:delete")
    public R delete(@RequestBody Integer[] ids) {
        menucategoryService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<MenucategoryEntity> list = menucategoryService.queryList(params);

        return R.ok().put("list", list);
    }
}
