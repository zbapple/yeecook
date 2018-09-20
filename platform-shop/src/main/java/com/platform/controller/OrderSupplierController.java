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

import com.platform.entity.OrderSupplierEntity;
import com.platform.service.OrderSupplierService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-09-19 12:23:49
 */
@RestController
@RequestMapping("ordersupplier")
public class OrderSupplierController {
    @Autowired
    private OrderSupplierService orderSupplierService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ordersupplier:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<OrderSupplierEntity> orderSupplierList = orderSupplierService.queryList(query);
        int total = orderSupplierService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(orderSupplierList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ordersupplier:info")
    public R info(@PathVariable("id") Integer id) {
        OrderSupplierEntity orderSupplier = orderSupplierService.queryObject(id);

        return R.ok().put("orderSupplier", orderSupplier);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ordersupplier:save")
    public R save(@RequestBody OrderSupplierEntity orderSupplier) {
        orderSupplierService.save(orderSupplier);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ordersupplier:update")
    public R update(@RequestBody OrderSupplierEntity orderSupplier) {
        orderSupplierService.update(orderSupplier);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ordersupplier:delete")
    public R delete(@RequestBody Integer[] ids) {
        orderSupplierService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<OrderSupplierEntity> list = orderSupplierService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public R queryTotal(@RequestParam Map<String, Object> params) {
        int sum = orderSupplierService.queryTotal(params);

        return R.ok().put("sum", sum);
    }

    /**
     * 确定收货
     *
     * @param id
     * @return
     */
    @RequestMapping("/confirm")
    @RequiresPermissions("ordersupplier:confirm")
    public R confirm(@RequestBody Integer id) {
        orderSupplierService.confirm(id);

        return R.ok();
    }

    /**
     * 发货
     *
     * @param orderSupplier
     * @return
     */
    @RequestMapping("/sendGoods")
    @RequiresPermissions("ordersupplier:sendGoods")
    public R sendGoods(@RequestBody OrderSupplierEntity orderSupplier) {
        orderSupplierService.sendGoods(orderSupplier);

        return R.ok();
    }
}
