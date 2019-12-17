package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.entity.CollectVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiCollectService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "用户收藏")
@RestController
@RequestMapping("/api/collect")
public class ApiCollectController extends ApiBaseAction {
    @Autowired
    private ApiCollectService collectService;

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "获取用户收藏")
    @PostMapping("list")
    public Object list(@LoginUser UserVo loginUser, Integer typeId) {

        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        param.put("type_id", typeId);
        List<CollectVo> collectEntities = collectService.queryList(param);

//        Query query = new Query(param);
//        int total = collectService.queryTotal(query);
//        ApiPageUtils pageUtil = new ApiPageUtils(collectEntities, total, query.getLimit(), query.getPage());
        return toResponsSuccess(collectEntities);
    }

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "添加取消收藏")
    @PostMapping("addordelete")
    public Object addordelete(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Integer typeId = jsonParam.getInteger("typeId");
        Integer valueId = jsonParam.getInteger("valueId");

        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        param.put("type_id", typeId);
        param.put("value_id", valueId);
        List<CollectVo> collectEntities = collectService.queryList(param);
        //
        Integer collectRes = null;
        String handleType = "add";
        if (null == collectEntities || collectEntities.size() < 1) {
            CollectVo collectEntity = new CollectVo();
            collectEntity.setAdd_time(System.currentTimeMillis() / 1000);
            collectEntity.setType_id(typeId);
            collectEntity.setValue_id(valueId);
            collectEntity.setChecked(1);
            collectEntity.setIs_attention(0);
            collectEntity.setUser_id(loginUser.getUserId());
            //添加收藏
            collectRes = collectService.save(collectEntity);
        } else {
            //取消收藏
            collectRes = collectService.delete(collectEntities.get(0).getId());
            handleType = "delete";
        }

        if (collectRes > 0) {
            Map data = new HashMap();
            data.put("type", handleType);
            return toResponsSuccess(data);
        }
        return toResponsFail("操作失败");
    }
    @ApiOperation(value = "添加取消收藏门店")
    @PostMapping("addordeletestroe")
    public Object addordeletestroe(@LoginUser UserVo loginUser){
        JSONObject jsonParam = getJsonRequest();
        Integer typeId = jsonParam.getInteger("typeId");
        Integer stroeid=jsonParam.getInteger("stroeid");

        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        param.put("type_id", typeId);
        param.put("stroeid", stroeid);
        List<CollectVo> collectEntities = collectService.queryList(param);
        //
        Integer collectRes = null;
        String handleType = "add";
        if (null == collectEntities || collectEntities.size() < 1) {
            CollectVo collectEntity = new CollectVo();
            collectEntity.setAdd_time(System.currentTimeMillis() / 1000);
            collectEntity.setType_id(typeId);
            collectEntity.setStroeid(stroeid);
            collectEntity.setChecked(1);
            collectEntity.setIs_attention(0);
            collectEntity.setUser_id(loginUser.getUserId());
            //添加收藏
            collectRes = collectService.save(collectEntity);
        } else {
            //取消收藏
            collectRes = collectService.delete(collectEntities.get(0).getId());
            handleType = "delete";
        }

        if (collectRes > 0) {
            Map data = new HashMap();
            data.put("type", handleType);
            return toResponsSuccess(data);
        }
        return  toResponsFail("操作失败");
    }

    @ApiOperation(value = "取消收藏")
    @PostMapping("deleteinfo")
    public Object deleteinfo(@LoginUser UserVo loginuser){
        Map<String,Object> result=new HashMap<>();
        Integer typeid=0;
//        JSONObject deletejson=this.getJsonRequest();
        CollectVo collectVo=new CollectVo();
        collectVo.setUser_id(loginuser.getUserId());
        collectService.deleteinfo(collectVo);
        return toResponsSuccess(list(loginuser,typeid));
    }

    @ApiOperation(value = "收藏修改")
    @PostMapping("updateinfo")
    public Object updateinfo(@LoginUser UserVo loginuser){
        Integer typeid=0;
        JSONObject updatejson=this.getJsonRequest();
            Integer checke=updatejson.getInteger("checked");
        Integer valueid=updatejson.getInteger("value_id");
        Integer stroeid=updatejson.getInteger("stroeid");
        Map listmap=new HashMap();
        listmap.put("user_id",loginuser.getUserId());
        List<CollectVo> collectVoList=collectService.queryList(listmap);
        if(collectVoList.size()==0){
            return toResponsSuccess(list(loginuser,typeid));
        }
        if(valueid!=null){
            if(checke==1){
                CollectVo collectVo=new CollectVo();
                collectVo.setChecked(checke);
                collectVo.setUser_id(loginuser.getUserId());
                collectVo.setValue_id(valueid);
                collectService.Updateinfo(collectVo);
            }else if(checke==0) {
                CollectVo collectVo=new CollectVo();
                collectVo.setChecked(checke);
                collectVo.setUser_id(loginuser.getUserId());
                collectVo.setValue_id(valueid);
                collectService.Updateinfo(collectVo);
            }
        }
        if(stroeid!=null){
            if(checke==1){
                CollectVo collectVo=new CollectVo();
                collectVo.setChecked(checke);
                collectVo.setUser_id(loginuser.getUserId());
                collectVo.setStroeid(stroeid);
                collectService.Updateinfo(collectVo);
            }else if(checke==0) {
                CollectVo collectVo=new CollectVo();
                collectVo.setChecked(checke);
                collectVo.setUser_id(loginuser.getUserId());
                collectVo.setStroeid(stroeid);
                collectService.Updateinfo(collectVo);
            }
        }
        if(stroeid==null&&valueid==null){
            if(checke==1){
                CollectVo collectVo=new CollectVo();
                collectVo.setChecked(checke);
                collectVo.setUser_id(loginuser.getUserId());
                collectService.Updateinfo(collectVo);
            }else if(checke==0){
                CollectVo collectVo=new CollectVo();
                collectVo.setChecked(checke);
                collectVo.setUser_id(loginuser.getUserId());
                collectService.Updateinfo(collectVo);
            }
        }

        return toResponsSuccess(list(loginuser,typeid));
    }
}
