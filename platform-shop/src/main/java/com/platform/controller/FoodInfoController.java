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

import com.platform.entity.FoodInfoEntity;
import com.platform.service.FoodInfoService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
@RestController
@RequestMapping("foodinfo")
public class FoodInfoController {
    @Autowired
    private FoodInfoService foodInfoService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("foodinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FoodInfoEntity> foodInfoList = foodInfoService.queryList(query);
        int total = foodInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(foodInfoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("foodinfo:info")
    public R info(@PathVariable("id") Integer id) {
        FoodInfoEntity foodInfo = foodInfoService.queryObject(id);

        return R.ok().put("foodInfo", foodInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("foodinfo:save")
    public R save(@RequestBody FoodInfoEntity foodInfo) {
        foodInfoService.save(foodInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("foodinfo:update")
    public R update(@RequestBody FoodInfoEntity foodInfo) {
        foodInfoService.update(foodInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("foodinfo:delete")
    public R delete(@RequestBody Integer[] ids) {
        foodInfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<FoodInfoEntity> list = foodInfoService.queryList(params);

        return R.ok().put("list", list);
    }
}
