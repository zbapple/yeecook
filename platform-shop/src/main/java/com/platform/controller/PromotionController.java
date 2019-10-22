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

import com.platform.entity.PromotionEntity;
import com.platform.service.PromotionService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-22 10:14:44
 */
@RestController
@RequestMapping("promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("promotion:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<PromotionEntity> promotionList = promotionService.queryList(query);
        int total = promotionService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(promotionList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("promotion:info")
    public R info(@PathVariable("id") Integer id) {
        PromotionEntity promotion = promotionService.queryObject(id);

        return R.ok().put("promotion", promotion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("promotion:save")
    public R save(@RequestBody PromotionEntity promotion) {
        promotionService.save(promotion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("promotion:update")
    public R update(@RequestBody PromotionEntity promotion) {
        promotionService.update(promotion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("promotion:delete")
    public R delete(@RequestBody Integer[] ids) {
        promotionService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<PromotionEntity> list = promotionService.queryList(params);

        return R.ok().put("list", list);
    }
}
