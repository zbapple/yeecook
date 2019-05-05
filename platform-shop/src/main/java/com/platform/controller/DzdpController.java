package com.platform.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platform.charles.xcf.XcfCharlesInfoEntity;
import com.platform.utils.*;
import com.platform.utils.excel.ExcelExport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.DzdpEntity;
import com.platform.service.DzdpService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-02-25 17:08:00
 */
@RestController
@RequestMapping("dzdp")
public class DzdpController {
    @Autowired
    private DzdpService dzdpService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dzdp:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<DzdpEntity> dzdpList = dzdpService.queryList(query);
        int total = dzdpService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(dzdpList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dzdp:info")
    public R info(@PathVariable("id") Long id) {
        DzdpEntity dzdp = dzdpService.queryObject(id);

        return R.ok().put("dzdp", dzdp);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dzdp:save")
    public R save(@RequestBody DzdpEntity dzdp) {
        dzdpService.save(dzdp);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dzdp:update")
    public R update(@RequestBody DzdpEntity dzdp) {
        dzdpService.update(dzdp);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dzdp:delete")
    public R delete(@RequestBody Long[] ids) {
        dzdpService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<DzdpEntity> list = dzdpService.queryList(params);

        return R.ok().put("list", list);
    }

    @RequestMapping("/exclorder")
    public R fileDownLoad(HttpServletResponse response,
                          @RequestParam String name, @RequestParam String areas, @RequestParam String categories,@RequestParam String filename) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotEmpty(name)) {
            params.put("name", name);
        }
        if (StringUtils.isNotEmpty(areas)) {
            params.put("areas", areas);
        }
        if (StringUtils.isNotEmpty(categories)) {
            params.put("categories", categories);
        }

        params.put("sidx", "categories");
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
            List<DzdpEntity> xcfCharlesInfoEntityList = dzdpService.queryList(params);
            String[] header = new String[]{"商家名称(name)","省份(province)","城市(city)","区域(areas)",	"地址(address)","分类(categories)	","电话(phones)"};

            for (DzdpEntity lingd : xcfCharlesInfoEntityList
            ) {
                List<Object> obj = new ArrayList<>();
                obj.add(lingd.getName());
                obj.add(lingd.getProvince());
                obj.add(lingd.getCity());
                obj.add(lingd.getAreas());
                obj.add(lingd.getAddress());
                obj.add(lingd.getCategories());
                obj.add(lingd.getPhones());
                list1.add(obj.toArray());
            }
            ee1.addSheetByArray("餐饮商户信息", list1, header);
            ee1.getWorkbook().write(out);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("导出失败");
        }
    }
}
