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

import com.platform.entity.SysPrinterEntity;
import com.platform.service.SysPrinterService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-09 12:15:00
 */
@RestController
@RequestMapping("sysprinter")
public class SysPrinterController {
    @Autowired
    private SysPrinterService sysPrinterService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysprinter:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysPrinterEntity> sysPrinterList = sysPrinterService.queryList(query);
        int total = sysPrinterService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysPrinterList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysprinter:info")
    public R info(@PathVariable("id") Long id) {
        SysPrinterEntity sysPrinter = sysPrinterService.queryObject(id);

        return R.ok().put("sysPrinter", sysPrinter);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysprinter:save")
    public R save(@RequestBody SysPrinterEntity sysPrinter) {
        sysPrinterService.save(sysPrinter);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysprinter:update")
    public R update(@RequestBody SysPrinterEntity sysPrinter) {
        sysPrinterService.update(sysPrinter);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysprinter:delete")
    public R delete(@RequestBody Long[] ids) {
        sysPrinterService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysPrinterEntity> list = sysPrinterService.queryList(params);

        return R.ok().put("list", list);
    }
}
