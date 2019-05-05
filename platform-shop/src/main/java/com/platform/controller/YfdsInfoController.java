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

import com.platform.entity.YfdsInfoEntity;
import com.platform.service.YfdsInfoService;
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
@RequestMapping("yfdsinfo")
public class YfdsInfoController {
    @Autowired
    private YfdsInfoService yfdsInfoService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("yfdsinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<YfdsInfoEntity> yfdsInfoList = yfdsInfoService.queryList(query);
        int total = yfdsInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(yfdsInfoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("yfdsinfo:info")
    public R info(@PathVariable("id") Integer id) {
        YfdsInfoEntity yfdsInfo = yfdsInfoService.queryObject(id);

        return R.ok().put("yfdsInfo", yfdsInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("yfdsinfo:save")
    public R save(@RequestBody YfdsInfoEntity yfdsInfo) {
        yfdsInfoService.save(yfdsInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("yfdsinfo:update")
    public R update(@RequestBody YfdsInfoEntity yfdsInfo) {
        yfdsInfoService.update(yfdsInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("yfdsinfo:delete")
    public R delete(@RequestBody Integer[] ids) {
        yfdsInfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<YfdsInfoEntity> list = yfdsInfoService.queryList(params);

        return R.ok().put("list", list);
    }
}
