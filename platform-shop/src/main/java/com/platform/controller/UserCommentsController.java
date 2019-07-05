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

import com.platform.entity.UserCommentsEntity;
import com.platform.service.UserCommentsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 用户评论表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:19:06
 */
@RestController
@RequestMapping("usercomments")
public class UserCommentsController {
    @Autowired
    private UserCommentsService userCommentsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("usercomments:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserCommentsEntity> userCommentsList = userCommentsService.queryList(query);
        int total = userCommentsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userCommentsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("usercomments:info")
    public R info(@PathVariable("id") Integer id) {
        UserCommentsEntity userComments = userCommentsService.queryObject(id);

        return R.ok().put("userComments", userComments);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("usercomments:save")
    public R save(@RequestBody UserCommentsEntity userComments) {
        userCommentsService.save(userComments);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("usercomments:update")
    public R update(@RequestBody UserCommentsEntity userComments) {
        userCommentsService.update(userComments);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("usercomments:delete")
    public R delete(@RequestBody Integer[] ids) {
        userCommentsService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UserCommentsEntity> list = userCommentsService.queryList(params);

        return R.ok().put("list", list);
    }
}
