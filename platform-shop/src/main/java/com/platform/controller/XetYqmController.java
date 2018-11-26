package com.platform.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.entity.SysUserEntity;
import com.platform.oss.OSSFactory;
import com.platform.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.XetYqmEntity;
import com.platform.service.XetYqmService;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 上传文件
     *
     * @param file 文件
     * @return R
     * @throws Exception 异常
     */
    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String[] heard = {"批次" ,"批次名称","邀请码","邀请码链接","是否使用","使用人id","使用人昵称","邀请码标题","使用须知","申请人","申请原因","生效时间","失效时间","生成时间"};

        List<XetYqmEntity> yqmEntityList = CsvUtils.readCSV(file,heard,XetYqmEntity.class);
                int i=0;
        if(null!=yqmEntityList&&yqmEntityList.size()>0) {
                 i=  xetYqmService.addBatch(yqmEntityList) ;
        }
        R r = new R();
        r.put("url", i);
        r.put("link", i);
        return r;
    }


}
