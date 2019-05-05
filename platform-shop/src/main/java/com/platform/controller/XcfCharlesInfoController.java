package com.platform.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import com.platform.utils.*;
import com.platform.utils.excel.ExcelExport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.charles.xcf.XcfCharlesInfoEntity;
import com.platform.service.XcfCharlesInfoService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;


/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-12-07 00:47:39
 */
@RestController
@RequestMapping("xcfcharlesinfo")
public class XcfCharlesInfoController {
    @Autowired
    private XcfCharlesInfoService xcfCharlesInfoService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("xcfcharlesinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        if(StringUtils.isNotEmpty((String)query.get("addtime[0]"))){
            query.put("addtime", DateUtils.parseTime((String) query.get("addtime[0]")));
            query.put("addtime1", DateUtils.parseTime((String) query.get("addtime[1]")));
        }
        List<XcfCharlesInfoEntity> xcfCharlesInfoList = xcfCharlesInfoService.queryList(query);
        int total = xcfCharlesInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(xcfCharlesInfoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("xcfcharlesinfo:info")
    public R info(@PathVariable("id") Long id) {
        XcfCharlesInfoEntity xcfCharlesInfo = xcfCharlesInfoService.queryObject(id);

        return R.ok().put("xcfCharlesInfo", xcfCharlesInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("xcfcharlesinfo:save")
    public R save(@RequestBody XcfCharlesInfoEntity xcfCharlesInfo) {
        xcfCharlesInfoService.save(xcfCharlesInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("xcfcharlesinfo:update")
    public R update(@RequestBody XcfCharlesInfoEntity xcfCharlesInfo) {
        xcfCharlesInfoService.update(xcfCharlesInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("xcfcharlesinfo:delete")
    public R delete(@RequestBody Long[] ids) {
        xcfCharlesInfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<XcfCharlesInfoEntity> list = xcfCharlesInfoService.queryList(params);

        return R.ok().put("list", list);
    }

    @RequestMapping("/exclorder")
    public R fileDownLoad(HttpServletResponse response,
                          @RequestParam String name, @RequestParam String years, @RequestParam String month, @RequestParam String day, @RequestParam String weeks, @RequestParam String sales, @RequestParam String lecturer, @RequestParam String filename ,@RequestParam  String [] addtime) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotEmpty(name)) {
            params.put("name", name);
        }
        if (StringUtils.isNotEmpty(years)) {
            params.put("years", Integer.valueOf(years));
        }
        if (StringUtils.isNotEmpty(month)) {
            params.put("month", month);
        }
        if (StringUtils.isNotEmpty(day)) {
            params.put("day", day);
        }
        if (StringUtils.isNotEmpty(weeks)) {
            params.put("weeks", weeks);
        }
        if (StringUtils.isNotEmpty(sales)) {
            params.put("sales", sales);
        }
        if (StringUtils.isNotEmpty(lecturer)) {
            params.put("lecturer", lecturer);
        }
        if (addtime!=null&&addtime.length>0) {
            params.put("addtime", DateUtils.parseTime(addtime[0]));
            params.put("addtime1", DateUtils.parseTime(addtime[1]));
        }
        params.put("sidx", "sales");
        params.put("order", "desc");
        response.setContentType("application/binary;charset=UTF-8");
        try {
            ServletOutputStream out = response.getOutputStream();
            try {

                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename + ".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            ExcelExport ee1 = new ExcelExport();

            List<Object[]> list1 = new ArrayList<Object[]>();
            List<XcfCharlesInfoEntity> xcfCharlesInfoEntityList = xcfCharlesInfoService.queryList(params);
            String[] header = new String[]{"课程","售价", "销量", "讲师", "抓取时间" ,"周"};

            for (XcfCharlesInfoEntity lingd : xcfCharlesInfoEntityList
            ) {
                List<Object> obj = new ArrayList<>();
                obj.add(lingd.getCourse());
                obj.add(lingd.getPrice());
                obj.add(lingd.getSales());
                obj.add(lingd.getLecturer());
                obj.add(DateUtils.format(lingd.getAddtime()));
                obj.add(lingd.getWeeks());
                list1.add(obj.toArray());
            }
            ee1.addSheetByArray("下厨房", list1, header);
            ee1.getWorkbook().write(out);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("导出失败");
        }
    }
}
