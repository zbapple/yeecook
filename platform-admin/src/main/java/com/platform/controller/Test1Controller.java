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

import com.platform.entity.Test1Entity;
import com.platform.service.Test1Service;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-04-03 15:20:28
 */
@RestController
@RequestMapping("test1")
public class Test1Controller {
    @Autowired
    private Test1Service test1Service;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("test1:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<Test1Entity> test1List = test1Service.queryList(query);
        int total = test1Service.queryTotal(query);

        PageUtils pageUtil = new PageUtils(test1List, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("test1:info")
    public R info(@PathVariable("id") Integer id) {
        Test1Entity test1 = test1Service.queryObject(id);

        return R.ok().put("test1", test1);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("test1:save")
    public R save(@RequestBody Test1Entity test1) {
        test1Service.save(test1);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("test1:update")
    public R update(@RequestBody Test1Entity test1) {
        test1Service.update(test1);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("test1:delete")
    public R delete(@RequestBody Integer[] ids) {
        test1Service.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<Test1Entity> list = test1Service.queryList(params);

        return R.ok().put("list", list);
    }
}
