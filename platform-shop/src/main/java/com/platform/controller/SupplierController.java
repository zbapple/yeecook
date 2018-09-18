package com.platform.controller;

import java.util.List;
import java.util.Map;

import com.platform.annotation.SysLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.SupplierEntity;
import com.platform.service.SupplierService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-09-12 14:23:53
 */
@RestController
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("supplier:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SupplierEntity> supplierList = supplierService.queryList(query);
        int total = supplierService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(supplierList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("supplier:info")
    public R info(@PathVariable("id") Long id) {
        SupplierEntity supplier = supplierService.queryObject(id);

        return R.ok().put("supplier", supplier);
    }

    /**
     * 保存
     */
    @SysLog("新增供应商")
    @RequestMapping("/save")
    @RequiresPermissions("supplier:save")
    public R save(@RequestBody SupplierEntity supplier) {
        supplierService.save(supplier);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改供应商")
    @RequestMapping("/update")
    @RequiresPermissions("supplier:update")
    public R update(@RequestBody SupplierEntity supplier) {
        supplierService.update(supplier);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除供应商")
    @RequestMapping("/delete")
    @RequiresPermissions("supplier:delete")
    public R delete(@RequestBody Long[] ids) {
        supplierService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SupplierEntity> list = supplierService.queryList(params);

        return R.ok().put("list", list);
    }
}
