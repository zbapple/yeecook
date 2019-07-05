package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.entity.CloudClassroomGoodVo;
import com.platform.service.ApiCloudClassroomGoodService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 云课堂商品关联表
 id
 课件ID
 商品idController
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:08:17
 */
@Api(tags = "课件配套商品")
@RestController
@RequestMapping("/api/cloudclassroomgood")
public class ApiCloudClassroomGoodController extends ApiBaseAction {
    @Autowired
    private ApiCloudClassroomGoodService cloudClassroomGoodService;
    @ApiOperation(value = "商品")
    @PostMapping("info")
    public Object info(){
        JSONObject infojsonparam=this.getJsonRequest();
        Integer videoid=infojsonparam.getInteger("videoid");
        Map infomap=new HashMap();
        infomap.put("videoid", videoid);
        List<CloudClassroomGoodVo> cloudClassroomGoodVos=cloudClassroomGoodService.queryList(infomap);
        return  toResponsSuccess(cloudClassroomGoodVos);
    }
}
