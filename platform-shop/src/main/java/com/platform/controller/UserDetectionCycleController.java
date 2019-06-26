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

import com.platform.entity.UserDetectionCycleEntity;
import com.platform.service.UserDetectionCycleService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 用户检测周期表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:17:10
 */
@RestController
@RequestMapping("userdetectioncycle")
public class UserDetectionCycleController {
    @Autowired
    private UserDetectionCycleService userDetectionCycleService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("userdetectioncycle:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserDetectionCycleEntity> userDetectionCycleList = userDetectionCycleService.queryList(query);
        int total = userDetectionCycleService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userDetectionCycleList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("userdetectioncycle:info")
    public R info(@PathVariable("id") Integer id) {
        UserDetectionCycleEntity userDetectionCycle = userDetectionCycleService.queryObject(id);

        return R.ok().put("userDetectionCycle", userDetectionCycle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("userdetectioncycle:save")
    public R save(@RequestBody UserDetectionCycleEntity userDetectionCycle) {
        userDetectionCycleService.save(userDetectionCycle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("userdetectioncycle:update")
    public R update(@RequestBody UserDetectionCycleEntity userDetectionCycle) {
        userDetectionCycleService.update(userDetectionCycle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("userdetectioncycle:delete")
    public R delete(@RequestBody Integer[] ids) {
        userDetectionCycleService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UserDetectionCycleEntity> list = userDetectionCycleService.queryList(params);

        return R.ok().put("list", list);
    }
}
