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

import com.platform.entity.MenuDetailsEntity;
import com.platform.service.MenuDetailsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 餐单详情表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:23:22
 */
@RestController
@RequestMapping("menudetails")
public class MenuDetailsController {
    @Autowired
    private MenuDetailsService menuDetailsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("menudetails:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<MenuDetailsEntity> menuDetailsList = menuDetailsService.queryList(query);
        int total = menuDetailsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(menuDetailsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("menudetails:info")
    public R info(@PathVariable("id") Integer id) {
        MenuDetailsEntity menuDetails = menuDetailsService.queryObject(id);

        return R.ok().put("menuDetails", menuDetails);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("menudetails:save")
    public R save(@RequestBody MenuDetailsEntity menuDetails) {
        menuDetailsService.save(menuDetails);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("menudetails:update")
    public R update(@RequestBody MenuDetailsEntity menuDetails) {
        menuDetailsService.update(menuDetails);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("menudetails:delete")
    public R delete(@RequestBody Integer[] ids) {
        menuDetailsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<MenuDetailsEntity> list = menuDetailsService.queryList(params);

        return R.ok().put("list", list);
    }
}
