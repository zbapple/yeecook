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

import com.platform.entity.ServeInfoEntity;
import com.platform.service.ServeInfoService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-21 01:13:48
 */
@RestController
@RequestMapping("serveinfo")
public class ServeInfoController {
    @Autowired
    private ServeInfoService serveInfoService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("serveinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ServeInfoEntity> serveInfoList = serveInfoService.queryList(query);
        int total = serveInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(serveInfoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("serveinfo:info")
    public R info(@PathVariable("id") Integer id) {
        ServeInfoEntity serveInfo = serveInfoService.queryObject(id);

        return R.ok().put("serveInfo", serveInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("serveinfo:save")
    public R save(@RequestBody ServeInfoEntity serveInfo) {
        serveInfoService.save(serveInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("serveinfo:update")
    public R update(@RequestBody ServeInfoEntity serveInfo) {
        serveInfoService.update(serveInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("serveinfo:delete")
    public R delete(@RequestBody Integer[] ids) {
        serveInfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ServeInfoEntity> list = serveInfoService.queryList(params);

        return R.ok().put("list", list);
    }
}
