package com.platform.controller;

import java.util.List;
import java.util.Map;

import com.platform.annotation.IgnoreAuth;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.JobEntity;
import com.platform.service.JobService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-11 10:36:32
 */
@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    private JobService jobService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("job:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<JobEntity> jobList = jobService.queryList(query);
        int total = jobService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
    /**
     * 官网招聘信息
     **/
    @RequestMapping("/message")
    @IgnoreAuth
    public R message(){
        List<JobEntity> message=jobService.queryMessage();
        return  R.ok().put("message",message);
    }
    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("job:info")
    public R info(@PathVariable("id") Integer id) {
        JobEntity job = jobService.queryObject(id);

        return R.ok().put("job", job);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("job:save")
    public R save(@RequestBody JobEntity job) {
        jobService.save(job);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("job:update")
    public R update(@RequestBody JobEntity job) {
        jobService.update(job);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("job:delete")
    public R delete(@RequestBody Integer[] ids) {
        jobService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<JobEntity> list = jobService.queryList(params);

        return R.ok().put("list", list);
    }
}
