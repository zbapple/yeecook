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

import com.platform.entity.CloudClassroomGoodEntity;
import com.platform.service.CloudClassroomGoodService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 云课堂商品关联表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:34:57
 */
@RestController
@RequestMapping("cloudclassroomgood")
public class CloudClassroomGoodController {
    @Autowired
    private CloudClassroomGoodService cloudClassroomGoodService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cloudclassroomgood:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CloudClassroomGoodEntity> cloudClassroomGoodList = cloudClassroomGoodService.queryList(query);
        int total = cloudClassroomGoodService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(cloudClassroomGoodList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cloudclassroomgood:info")
    public R info(@PathVariable("id") Integer id) {
        CloudClassroomGoodEntity cloudClassroomGood = cloudClassroomGoodService.queryObject(id);

        return R.ok().put("cloudClassroomGood", cloudClassroomGood);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cloudclassroomgood:save")
    public R save(@RequestBody CloudClassroomGoodEntity cloudClassroomGood) {
        cloudClassroomGoodService.save(cloudClassroomGood);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cloudclassroomgood:update")
    public R update(@RequestBody CloudClassroomGoodEntity cloudClassroomGood) {
        cloudClassroomGoodService.update(cloudClassroomGood);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cloudclassroomgood:delete")
    public R delete(@RequestBody Integer[] ids) {
        cloudClassroomGoodService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<CloudClassroomGoodEntity> list = cloudClassroomGoodService.queryList(params);

        return R.ok().put("list", list);
    }
}
