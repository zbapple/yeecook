package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.dao.ApiUserCommentsMapper;
import com.platform.entity.UserCommentsVo;
import com.platform.entity.UserVo;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

import javax.json.JsonObject;

/**
 * 用户评论表
 用户id
 用户评论
 课件id
 评论分数
 评论时间
 回复类型 0是 1否
 回复id
 idController
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:50
 */
@Api(tags = "用户评论")
@RestController
@RequestMapping("/api/usercomments")
public class ApiUserCommentsController  extends ApiBaseAction {
    @Autowired
    private ApiUserCommentsMapper userCommentsService;
    @ApiOperation(value = "用户写品论")
    @PostMapping("add")
    public Object add(@LoginUser UserVo loginuser){
        JSONObject useraddjson=this.getJsonRequest();
        UserCommentsVo userCommentsVo=new UserCommentsVo();
        if(null!=useraddjson){
            userCommentsVo.setNideshopUserId(loginuser.getUserId());
            userCommentsVo.setCommentsScore(useraddjson.getString("commentsscore"));
            userCommentsVo.setCommentsTime(useraddjson.getDate("commentstime"));
            userCommentsVo.setUserComment(useraddjson.getString("usercomment"));
            userCommentsService.save(userCommentsVo);
        }else {
            return  toResponsMsgSuccess("添加失败");
        }
        return toResponsMsgSuccess("添加成功");
    }
    @ApiOperation(value = "获取用户品论")
    @PostMapping("info")
    public  Object info(){
        Map<String,Object> result=new HashMap<>();
        UserCommentsVo userCommentsVo=new UserCommentsVo();
      JSONObject userinfo=this.getJsonRequest();
      Integer videoid=userinfo.getInteger("videoid");
      Map userinfomap=new HashMap();
      userinfomap.put("videoid",videoid);
      List<UserCommentsVo> userCommentsVoList=userCommentsService.queryList(userinfomap);
      int total=userCommentsService.queryTotal(userinfomap);
        result.put("userCommentsVoList",userCommentsVoList);
        result.put("total",total);
      return  toResponsSuccess(result);
    }
}