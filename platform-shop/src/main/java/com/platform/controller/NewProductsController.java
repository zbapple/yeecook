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

import com.platform.entity.NewProductsEntity;
import com.platform.service.NewProductsService;
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
@RequestMapping("newproducts")
public class NewProductsController {
    @Autowired
    private NewProductsService newProductsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("newproducts:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<NewProductsEntity> newProductsList = newProductsService.queryList(query);
        int total = newProductsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(newProductsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("newproducts:info")
    public R info(@PathVariable("id") Integer id) {
        NewProductsEntity newProducts = newProductsService.queryObject(id);

        return R.ok().put("newProducts", newProducts);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("newproducts:save")
    public R save(@RequestBody NewProductsEntity newProducts) {
        newProductsService.save(newProducts);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("newproducts:update")
    public R update(@RequestBody NewProductsEntity newProducts) {
        newProductsService.update(newProducts);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("newproducts:delete")
    public R delete(@RequestBody Integer[] ids) {
        newProductsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<NewProductsEntity> list = newProductsService.queryList(params);

        return R.ok().put("list", list);
    }
}
