package com.platform.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.entity.StroeEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.platform.service.StroeService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author 17
 * @email inaoie@163.com
 * @date 2019-11-01 11:00:09
 */
@RestController
@RequestMapping("stroe")
public class StroeController {
    @Autowired
    private StroeService stroeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("stroe:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        query.put("isDelete",0);
        List<StroeEntity> stroeList = stroeService.queryList(query);
        if (stroeList !=null && stroeList.size()>0){
            for (StroeEntity stroeEntity: stroeList) {
                //拼接省市区到详细地址里
                String province=stroeEntity.getProvince();
                String city=stroeEntity.getCity();
                String distrct=stroeEntity.getDistrct();
                String addres=stroeEntity.getAddress();
                String address=province+city+distrct+addres;
                stroeEntity.setAddress(address);
            }
        }
        int total = stroeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(stroeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("stroe:info")
    public R info(@PathVariable("id") Integer id) {
        StroeEntity stroe = stroeService.queryObject(id);
        //时间组件问题
        if (stroe.getStoreTime() !=null && stroe.getStoreTime().length()>0) {
            String datetime = stroe.getStoreTime();
            String[] s = datetime.split("-");
            String time1 = s[0];
            String time2 = s[1];
            List<String> list = new ArrayList<>();
            list.add(0, time1);
            list.add(1, time2);
            stroe.setStoreTimes(list);
        }

        return R.ok().put("stroe", stroe);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("stroe:save")
    public R save(@RequestBody StroeEntity stroe) {
        stroeService.save(stroe);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("stroe:update")
    public R update(@RequestBody StroeEntity stroe) {
        stroeService.update(stroe);

        return R.ok();
    }

    /**
     * 删除(进入回收站)
     */
    @RequestMapping("/delete")
    @RequiresPermissions("stroe:delete")
    public R delete(@RequestBody Integer[] ids) {
        stroeService.deleteBatch(ids);
        return R.ok();
    }
   /**
    *  删除
    **/
    @RequestMapping("/deleteAll")
    public R deleteAll(@RequestBody Integer[] ids){
        stroeService.deleteAll(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StroeEntity> list = stroeService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 修改状态
     **/

    @RequestMapping("/updateStatus")
    public R updateStatus(@Param("id") @RequestBody Integer id){
        stroeService.updateStatus(id);
        return  R.ok();
    }

    /**
     * 门店回收站
     **/
    @RequestMapping("/historyList")
    public R historyList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        query.put("isDelete", 1);
        List<StroeEntity> stroeList = stroeService.queryList(query);
        if (stroeList !=null && stroeList.size()>0){
            for (StroeEntity stroeEntity: stroeList) {
                //拼接省市区到详细地址里
                String province=stroeEntity.getProvince();
                String city=stroeEntity.getCity();
                String distrct=stroeEntity.getDistrct();
                String addres=stroeEntity.getAddress();
                String address=province+city+distrct+addres;
                stroeEntity.setAddress(address);
                //获得删除时间
                Date date=new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowdate=dateFormat.format(date);
                stroeEntity.setDeleteTime(nowdate);
            }
        }
        int total = stroeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(stroeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
    /**
     * 门店从回收站恢复
     */
    @RequestMapping("/back")
    public R back(@RequestBody Integer[] ids) {
        stroeService.back(ids);

        return R.ok();
    }
    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public R queryTotal(@RequestParam Map<String, Object> params) {

        params.put("isDelete", 1);
        int sum = stroeService.queryTotal(params);
        return R.ok().put("stroeSum", sum);
    }
}
