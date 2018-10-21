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

import com.platform.entity.ActivationCardEntity;
import com.platform.service.ActivationCardService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-21 01:13:48
 */
@RestController
@RequestMapping("activationcard")
public class ActivationCardController {
    @Autowired
    private ActivationCardService activationCardService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("activationcard:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ActivationCardEntity> activationCardList = activationCardService.queryList(query);
        int total = activationCardService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(activationCardList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("activationcard:info")
    public R info(@PathVariable("id") Integer id) {
        ActivationCardEntity activationCard = activationCardService.queryObject(id);

        return R.ok().put("activationCard", activationCard);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("activationcard:save")
    public R save(@RequestBody ActivationCardEntity activationCard) {
        activationCardService.save(activationCard);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("activationcard:update")
    public R update(@RequestBody ActivationCardEntity activationCard) {
        activationCardService.update(activationCard);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("activationcard:delete")
    public R delete(@RequestBody Integer[] ids) {
        activationCardService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ActivationCardEntity> list = activationCardService.queryList(params);

        return R.ok().put("list", list);
    }
}
