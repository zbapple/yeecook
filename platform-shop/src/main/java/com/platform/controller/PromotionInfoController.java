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

import com.platform.entity.PromotionInfoEntity;
import com.platform.service.PromotionInfoService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-22 11:58:47
 */
@RestController
@RequestMapping("promotioninfo")
public class PromotionInfoController {
    @Autowired
    private PromotionInfoService promotionInfoService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("promotioninfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<PromotionInfoEntity> promotionInfoList = promotionInfoService.queryList(query);
        int total = promotionInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(promotionInfoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("promotioninfo:info")
    public R info(@PathVariable("id") Integer id) {
        PromotionInfoEntity promotionInfo = promotionInfoService.queryObject(id);

        return R.ok().put("promotionInfo", promotionInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("promotioninfo:save")
    public R save(@RequestBody PromotionInfoEntity promotionInfo) {
        promotionInfoService.save(promotionInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("promotioninfo:update")
    public R update(@RequestBody PromotionInfoEntity promotionInfo) {
        promotionInfoService.update(promotionInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("promotioninfo:delete")
    public R delete(@RequestBody Integer[] ids) {
        promotionInfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<PromotionInfoEntity> list = promotionInfoService.queryList(params);

        return R.ok().put("list", list);
    }
}
