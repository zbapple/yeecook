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

import com.platform.entity.ActivationGeneratorLogEntity;
import com.platform.service.ActivationGeneratorLogService;
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
@RequestMapping("activationgeneratorlog")
public class ActivationGeneratorLogController {
    @Autowired
    private ActivationGeneratorLogService activationGeneratorLogService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("activationgeneratorlog:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ActivationGeneratorLogEntity> activationGeneratorLogList = activationGeneratorLogService.queryList(query);
        int total = activationGeneratorLogService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(activationGeneratorLogList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("activationgeneratorlog:info")
    public R info(@PathVariable("id") Integer id) {
        ActivationGeneratorLogEntity activationGeneratorLog = activationGeneratorLogService.queryObject(id);

        return R.ok().put("activationGeneratorLog", activationGeneratorLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("activationgeneratorlog:save")
    public R save(@RequestBody ActivationGeneratorLogEntity activationGeneratorLog) {
        activationGeneratorLogService.save(activationGeneratorLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("activationgeneratorlog:update")
    public R update(@RequestBody ActivationGeneratorLogEntity activationGeneratorLog) {
        activationGeneratorLogService.update(activationGeneratorLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("activationgeneratorlog:delete")
    public R delete(@RequestBody Integer[] ids) {
        activationGeneratorLogService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ActivationGeneratorLogEntity> list = activationGeneratorLogService.queryList(params);

        return R.ok().put("list", list);
    }
}
