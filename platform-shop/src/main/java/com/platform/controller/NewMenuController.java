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

import com.platform.entity.NewMenuEntity;
import com.platform.service.NewMenuService;
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
@RequestMapping("newmenu")
public class NewMenuController {
    @Autowired
    private NewMenuService newMenuService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("newmenu:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<NewMenuEntity> newMenuList = newMenuService.queryList(query);
        int total = newMenuService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(newMenuList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("newmenu:info")
    public R info(@PathVariable("id") Integer id) {
        NewMenuEntity newMenu = newMenuService.queryObject(id);

        return R.ok().put("newMenu", newMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("newmenu:save")
    public R save(@RequestBody NewMenuEntity newMenu) {
        newMenuService.save(newMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("newmenu:update")
    public R update(@RequestBody NewMenuEntity newMenu) {
        newMenuService.update(newMenu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("newmenu:delete")
    public R delete(@RequestBody Integer[] ids) {
        newMenuService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<NewMenuEntity> list = newMenuService.queryList(params);

        return R.ok().put("list", list);
    }


}
