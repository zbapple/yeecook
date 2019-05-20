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

import com.platform.entity.NewMenuListEntity;
import com.platform.service.NewMenuListService;
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
@RequestMapping("newmenulist")
public class NewMenuListController {
    @Autowired
    private NewMenuListService newMenuListService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("newmenulist:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<NewMenuListEntity> newMenuListList = newMenuListService.queryList(query);
        int total = newMenuListService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(newMenuListList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("newmenulist:info")
    public R info(@PathVariable("id") Integer id) {
        NewMenuListEntity newMenuList = newMenuListService.queryObject(id);

        return R.ok().put("newMenuList", newMenuList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("newmenulist:save")
    public R save(@RequestBody NewMenuListEntity newMenuList) {
        newMenuListService.save(newMenuList);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("newmenulist:update")
    public R update(@RequestBody NewMenuListEntity newMenuList) {
        newMenuListService.update(newMenuList);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("newmenulist:delete")
    public R delete(@RequestBody Integer[] ids) {
        newMenuListService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<NewMenuListEntity> list = newMenuListService.queryList(params);

        return R.ok().put("list", list);
    }
}
