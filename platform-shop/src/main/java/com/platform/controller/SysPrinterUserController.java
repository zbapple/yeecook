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

import com.platform.entity.SysPrinterUserEntity;
import com.platform.service.SysPrinterUserService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-09 15:47:35
 */
@RestController
@RequestMapping("sysprinteruser")
public class SysPrinterUserController {
    @Autowired
    private SysPrinterUserService sysPrinterUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysprinteruser:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysPrinterUserEntity> sysPrinterUserList = sysPrinterUserService.queryList(query);
        int total = sysPrinterUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysPrinterUserList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysprinteruser:info")
    public R info(@PathVariable("id") Long id) {
        SysPrinterUserEntity sysPrinterUser = sysPrinterUserService.queryObject(id);

        return R.ok().put("sysPrinterUser", sysPrinterUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysprinteruser:save")
    public R save(@RequestBody SysPrinterUserEntity sysPrinterUser) {


        sysPrinterUserService.save(sysPrinterUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysprinteruser:update")
    public R update(@RequestBody SysPrinterUserEntity sysPrinterUser) {
        sysPrinterUserService.update(sysPrinterUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysprinteruser:delete")
    public R delete(@RequestBody Long[] ids) {
        sysPrinterUserService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysPrinterUserEntity> list = sysPrinterUserService.queryList(params);

        return R.ok().put("list", list);
    }
}
