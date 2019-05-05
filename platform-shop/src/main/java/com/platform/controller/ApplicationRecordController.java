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

import com.platform.entity.ApplicationRecordEntity;
import com.platform.service.ApplicationRecordService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-06 02:42:19
 */
@RestController
@RequestMapping("applicationrecord")
public class ApplicationRecordController {
    @Autowired
    private ApplicationRecordService applicationRecordService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("applicationrecord:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ApplicationRecordEntity> applicationRecordList = applicationRecordService.queryList(query);
        int total = applicationRecordService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(applicationRecordList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("applicationrecord:info")
    public R info(@PathVariable("id") Integer id) {
        ApplicationRecordEntity applicationRecord = applicationRecordService.queryObject(id);

        return R.ok().put("applicationRecord", applicationRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("applicationrecord:save")
    public R save(@RequestBody ApplicationRecordEntity applicationRecord) {
        applicationRecordService.save(applicationRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("applicationrecord:update")
    public R update(@RequestBody ApplicationRecordEntity applicationRecord) {
        applicationRecordService.update(applicationRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("applicationrecord:delete")
    public R delete(@RequestBody Integer[] ids) {
        applicationRecordService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ApplicationRecordEntity> list = applicationRecordService.queryList(params);

        return R.ok().put("list", list);
    }
}
