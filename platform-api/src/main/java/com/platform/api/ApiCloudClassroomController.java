package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.entity.CloudClassroomVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiCloudClassroomService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 云课堂表
 Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-21 12:58:05
 */
@Api(tags = "云课堂")
@RestController
@RequestMapping("/api/cloudclassroom")
public class ApiCloudClassroomController extends ApiBaseAction {
    @Autowired
    private ApiCloudClassroomService cloudClassroomService;
    @ApiOperation(value = "轮播图")
    @PostMapping("baner")
    public Object baner(Integer cloudid){
        JSONObject baerjson=this.getJsonRequest();
        cloudid=baerjson.getInteger("cloudid");
        CloudClassroomVo cloudClassroomVoList=cloudClassroomService.queryObject(cloudid);
        return toResponsSuccess(cloudClassroomVoList);
    }
    @ApiOperation(value = "分页获取云课堂")
    @PostMapping("list")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size){
        JSONObject jsonObject=this.getJsonRequest();
       page=jsonObject.getInteger("page");
       size=jsonObject.getInteger("size");
        Map params = new HashMap();
        params.put("fields", "id,video_cover_pic,video_title,video_subtitle");
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "id");
        params.put("order", "asc");
        Query query=new Query(params);
        List<CloudClassroomVo> cloudlist=cloudClassroomService.queryList(query);
        int total=cloudClassroomService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(cloudlist,total,query.getLimit(),query.getPage());
        return toResponsSuccess(pageUtil);
    }
}