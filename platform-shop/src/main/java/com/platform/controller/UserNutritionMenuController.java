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

import com.platform.entity.UserNutritionMenuEntity;
import com.platform.service.UserNutritionMenuService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 用户营养餐单表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:05:27
 */
@RestController
@RequestMapping("usernutritionmenu")
public class UserNutritionMenuController {
    @Autowired
    private UserNutritionMenuService userNutritionMenuService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("usernutritionmenu:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserNutritionMenuEntity> userNutritionMenuList = userNutritionMenuService.queryList(query);
        int total = userNutritionMenuService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userNutritionMenuList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("usernutritionmenu:info")
    public R info(@PathVariable("id") Integer id) {
        UserNutritionMenuEntity userNutritionMenu = userNutritionMenuService.queryObject(id);

        return R.ok().put("userNutritionMenu", userNutritionMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("usernutritionmenu:save")
    public R save(@RequestBody UserNutritionMenuEntity userNutritionMenu) {
        userNutritionMenuService.save(userNutritionMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("usernutritionmenu:update")
    public R update(@RequestBody UserNutritionMenuEntity userNutritionMenu) {
        userNutritionMenuService.update(userNutritionMenu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("usernutritionmenu:delete")
    public R delete(@RequestBody Integer[] ids) {
        userNutritionMenuService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UserNutritionMenuEntity> list = userNutritionMenuService.queryList(params);

        return R.ok().put("list", list);
    }
}
