package com.platform.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.annotation.SysLog;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysDeptService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.SupplierEntity;
import com.platform.service.SupplierService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-09-12 14:23:53
 */
@RestController
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("supplier:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        query.put("isDelete", 0);
        List<SupplierEntity> supplierList = supplierService.queryList(query);
        int total = supplierService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(supplierList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("supplier:info")
    public R info(@PathVariable("id") Long id) {
        SupplierEntity supplier = supplierService.queryObject(id);

        return R.ok().put("supplier", supplier);
    }

    /**
     * 保存
     */
    @SysLog("新增供应商")
    @RequestMapping("/save")
    @RequiresPermissions("supplier:save")
    public R save(@RequestBody SupplierEntity supplier) {
        supplierService.save(supplier);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改供应商")
    @RequestMapping("/update")
    @RequiresPermissions("supplier:update")
    public R update(@RequestBody SupplierEntity supplier) {
        supplierService.update(supplier);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除供应商")
    @RequestMapping("/delete")
    @RequiresPermissions("supplier:delete")
    public R delete(@RequestBody Long[] ids) {
        supplierService.deleteBatch(ids);

        return R.ok();
    }
    /**
     *  删除
     **/
    @RequestMapping("/deleteAll")
    public R deleteAll(@RequestBody Integer[] ids){
        supplierService.deleteAll(ids);
        return R.ok();
    }
    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        params.put("isDelete", 0);
        List<SupplierEntity> list = supplierService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查找供应商
     **/
    @RequestMapping("/name")
    public R queryName(){

        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        Long deptId=user.getDeptId();
        if (deptId==1) {
            deptId=10l;
            SupplierEntity supplier = supplierService.queryName(deptId);
            return R.ok().put("supplier",supplier);
        }
        SupplierEntity supplier = supplierService.queryName(deptId);
        return R.ok().put("supplier",supplier);
    }


    /**
     * 修改状态
     **/

    @RequestMapping("/updateStatus")
    public R updateStatus(@Param("id") @RequestBody Integer id){
        supplierService.updateStatus(id);
        return  R.ok();
    }

    /**
     * 门店回收站
     **/
    @RequestMapping("/historyList")
    public R historyList(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        query.put("isDelete", 1);
        List<SupplierEntity> supplierList = supplierService.queryList(query);
        if (supplierList !=null && supplierList.size()>0){
            for (SupplierEntity supplierEntity: supplierList) {
                //获得删除时间
                Date date=new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowdate=dateFormat.format(date);
                supplierEntity.setDeleteTime(nowdate);
            }
        }
        int total = supplierService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(supplierList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
    /**
     * 门店从回收站恢复
     */
    @RequestMapping("/back")
    public R back(@RequestBody Long[] ids) {
        supplierService.back(ids);

        return R.ok();
    }
    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public R queryTotal(@RequestParam Map<String, Object> params) {

        params.put("isDelete", 1);
        int sum = supplierService.queryTotal(params);
        return R.ok().put("sum", sum);
    }
}
