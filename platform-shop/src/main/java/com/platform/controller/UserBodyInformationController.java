package com.platform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platform.entity.UserEntity;
import com.platform.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.UserBodyInformationEntity;
import com.platform.service.UserBodyInformationService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 用户身体信息表
Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:20:43
 */
@RestController
@RequestMapping("userbodyinformation")
public class UserBodyInformationController {
    @Autowired
    private UserBodyInformationService userBodyInformationService;
    @Autowired
    private UserService userService;
    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("userbodyinformation:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserBodyInformationEntity> userBodyInformationList = userBodyInformationService.queryList(query);
        int total = userBodyInformationService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userBodyInformationList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("userbodyinformation:info")
    public R info(@PathVariable("id") Integer id) {
        UserBodyInformationEntity userBodyInformation = userBodyInformationService.queryObject(id);

        return R.ok().put("userBodyInformation", userBodyInformation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("userbodyinformation:save")
    public R save(@RequestBody UserBodyInformationEntity userBodyInformation) {
        String nickname=userBodyInformation.getNickname();
        Map params=new HashMap();
        params.put("nickname",nickname);
        List<UserEntity> userEntityList=userService.queryList(params);
        for(UserEntity userEntityiTem:userEntityList){
            Integer userid=userEntityiTem.getId();
            userBodyInformation.setNideshopUserId(userid);
        }

        userBodyInformationService.save(userBodyInformation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("userbodyinformation:update")
    public R update(@RequestBody UserBodyInformationEntity userBodyInformation) {
        String nickname=userBodyInformation.getNickname();
        Map params=new HashMap();
        params.put("nickname",nickname);
        List<UserEntity> userEntityList=userService.queryList(params);
        for(UserEntity userEntityiTem:userEntityList){
            Integer userid=userEntityiTem.getId();
            userBodyInformation.setNideshopUserId(userid);
        }
        userBodyInformationService.update(userBodyInformation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("userbodyinformation:delete")
    public R delete(@RequestBody Integer[] ids) {
        userBodyInformationService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UserBodyInformationEntity> list = userBodyInformationService.queryList(params);

        return R.ok().put("list", list);
    }
}
