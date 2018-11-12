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

import com.platform.entity.ActivationGeneratorEntity;
import com.platform.service.ActivationGeneratorService;
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
@RequestMapping("activationgenerator")
public class ActivationGeneratorController {
    @Autowired
    private ActivationGeneratorService activationGeneratorService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("activationgenerator:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ActivationGeneratorEntity> activationGeneratorList = activationGeneratorService.queryList(query);
        int total = activationGeneratorService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(activationGeneratorList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("activationgenerator:info")
    public R info(@PathVariable("id") Integer id) {
        ActivationGeneratorEntity activationGenerator = activationGeneratorService.queryObject(id);

        return R.ok().put("activationGenerator", activationGenerator);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("activationgenerator:save")
    public R save(@RequestBody ActivationGeneratorEntity activationGenerator) {
        activationGeneratorService.save(activationGenerator);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("activationgenerator:update")
    public R update(@RequestBody ActivationGeneratorEntity activationGenerator) {
        activationGeneratorService.update(activationGenerator);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("activationgenerator:delete")
    public R delete(@RequestBody Integer[] ids) {
        activationGeneratorService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ActivationGeneratorEntity> list = activationGeneratorService.queryList(params);

        return R.ok().put("list", list);
    }
}
