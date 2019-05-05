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

import com.platform.entity.SupplieInfoEntity;
import com.platform.service.SupplieInfoService;
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
@RequestMapping("supplieinfo")
public class SupplieInfoController {
    @Autowired
    private SupplieInfoService supplieInfoService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("supplieinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SupplieInfoEntity> supplieInfoList = supplieInfoService.queryList(query);
        int total = supplieInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(supplieInfoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("supplieinfo:info")
    public R info(@PathVariable("id") Integer id) {
        SupplieInfoEntity supplieInfo = supplieInfoService.queryObject(id);

        return R.ok().put("supplieInfo", supplieInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("supplieinfo:save")
    public R save(@RequestBody SupplieInfoEntity supplieInfo) {
        supplieInfoService.save(supplieInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("supplieinfo:update")
    public R update(@RequestBody SupplieInfoEntity supplieInfo) {
        supplieInfoService.update(supplieInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("supplieinfo:delete")
    public R delete(@RequestBody Integer[] ids) {
        supplieInfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SupplieInfoEntity> list = supplieInfoService.queryList(params);

        return R.ok().put("list", list);
    }
}
