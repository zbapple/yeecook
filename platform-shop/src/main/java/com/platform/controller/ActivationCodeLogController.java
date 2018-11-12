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

import com.platform.entity.ActivationCodeLogEntity;
import com.platform.service.ActivationCodeLogService;
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
@RequestMapping("activationcodelog")
public class ActivationCodeLogController {
    @Autowired
    private ActivationCodeLogService activationCodeLogService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("activationcodelog:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ActivationCodeLogEntity> activationCodeLogList = activationCodeLogService.queryList(query);
        int total = activationCodeLogService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(activationCodeLogList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("activationcodelog:info")
    public R info(@PathVariable("id") Integer id) {
        ActivationCodeLogEntity activationCodeLog = activationCodeLogService.queryObject(id);

        return R.ok().put("activationCodeLog", activationCodeLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("activationcodelog:save")
    public R save(@RequestBody ActivationCodeLogEntity activationCodeLog) {
        activationCodeLogService.save(activationCodeLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("activationcodelog:update")
    public R update(@RequestBody ActivationCodeLogEntity activationCodeLog) {
        activationCodeLogService.update(activationCodeLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("activationcodelog:delete")
    public R delete(@RequestBody Integer[] ids) {
        activationCodeLogService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ActivationCodeLogEntity> list = activationCodeLogService.queryList(params);

        return R.ok().put("list", list);
    }
}
