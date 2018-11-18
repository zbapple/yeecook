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

import com.platform.entity.XetYqmEntity;
import com.platform.service.XetYqmService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-18 13:21:53
 */
@RestController
@RequestMapping("xetyqm")
public class XetYqmController {
    @Autowired
    private XetYqmService xetYqmService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("xetyqm:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<XetYqmEntity> xetYqmList = xetYqmService.queryList(query);
        int total = xetYqmService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(xetYqmList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{invitationCode}")
    @RequiresPermissions("xetyqm:info")
    public R info(@PathVariable("invitationCode") String invitationCode) {
        XetYqmEntity xetYqm = xetYqmService.queryObject(invitationCode);

        return R.ok().put("xetYqm", xetYqm);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("xetyqm:save")
    public R save(@RequestBody XetYqmEntity xetYqm) {
        xetYqmService.save(xetYqm);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("xetyqm:update")
    public R update(@RequestBody XetYqmEntity xetYqm) {
        xetYqmService.update(xetYqm);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("xetyqm:delete")
    public R delete(@RequestBody String[] invitationCodes) {
        xetYqmService.deleteBatch(invitationCodes);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<XetYqmEntity> list = xetYqmService.queryList(params);

        return R.ok().put("list", list);
    }
}
