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

import com.platform.entity.ServeTypeEntity;
import com.platform.service.ServeTypeService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-28 00:48:59
 */
@RestController
@RequestMapping("servetype")
public class ServeTypeController {
    @Autowired
    private ServeTypeService serveTypeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("servetype:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ServeTypeEntity> serveTypeList = serveTypeService.queryList(query);
        int total = serveTypeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(serveTypeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("servetype:info")
    public R info(@PathVariable("id") Integer id) {
        ServeTypeEntity serveType = serveTypeService.queryObject(id);

        return R.ok().put("serveType", serveType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("servetype:save")
    public R save(@RequestBody ServeTypeEntity serveType) {
        serveTypeService.save(serveType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("servetype:update")
    public R update(@RequestBody ServeTypeEntity serveType) {
        serveTypeService.update(serveType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("servetype:delete")
    public R delete(@RequestBody Integer[] ids) {
        serveTypeService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ServeTypeEntity> list = serveTypeService.queryList(params);

        return R.ok().put("list", list);
    }
}
