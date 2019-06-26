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

import com.platform.entity.CloudClassroomEntity;
import com.platform.service.CloudClassroomService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 云课堂表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:35:47
 */
@RestController
@RequestMapping("cloudclassroom")
public class CloudClassroomController {
    @Autowired
    private CloudClassroomService cloudClassroomService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cloudclassroom:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CloudClassroomEntity> cloudClassroomList = cloudClassroomService.queryList(query);
        int total = cloudClassroomService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(cloudClassroomList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cloudclassroom:info")
    public R info(@PathVariable("id") Integer id) {
        CloudClassroomEntity cloudClassroom = cloudClassroomService.queryObject(id);

        return R.ok().put("cloudClassroom", cloudClassroom);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cloudclassroom:save")
    public R save(@RequestBody CloudClassroomEntity cloudClassroom) {
        cloudClassroomService.save(cloudClassroom);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cloudclassroom:update")
    public R update(@RequestBody CloudClassroomEntity cloudClassroom) {
        cloudClassroomService.update(cloudClassroom);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cloudclassroom:delete")
    public R delete(@RequestBody Integer[] ids) {
        cloudClassroomService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<CloudClassroomEntity> list = cloudClassroomService.queryList(params);

        return R.ok().put("list", list);
    }
}
