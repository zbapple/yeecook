package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;


import com.platform.entity.DishesVo;
import com.platform.entity.UserNutritionMenuVo;
import com.platform.entity.UserVo;

import com.platform.service.ApiDishesService;
import com.platform.service.ApiUserNutritionMenuService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:59
 */
@Api(tags = "用户营养餐单")
@RestController
@RequestMapping("/api/usernutrition")
public class ApiUserNutritionMenuController extends ApiBaseAction {
    @Autowired
    private ApiUserNutritionMenuService userNutritionMenuService;
    @Autowired
    private ApiDishesService dishesService;
    @ApiOperation(value = "获取用户餐单")
    @PostMapping("info")
    public Object info(@LoginUser UserVo loginuser ){
        Map<String,Object> result=new HashMap<>();
        JSONObject infojson=this.getJsonRequest();
        Long userid=loginuser.getUserId();
        Map infomap=new HashMap();
        infomap.put("nideshopUserid",userid);
        List<UserNutritionMenuVo> userNutritionMenuVoList=userNutritionMenuService.queryList(infomap);
        if(userNutritionMenuVoList!=null){
            result.put("userNutritionMenuVoList",userNutritionMenuVoList);
            result.put("flag",1);
            return  toResponsSuccess(result);
        }else{
                result.put("flag",0);
            return toResponsSuccess(result);
        }

    }
    @ApiOperation(value = "门店的套餐")
    @IgnoreAuth
    @PostMapping("storeinfo")
    public Object storeinfo(){
        Map<String,Object> result=new HashMap<>();
        try {
            JSONObject storeinfo=this.getJsonRequest();
            Map storemap=new HashMap();
            Integer storeid=storeinfo.getInteger("storid");
            String menutype=storeinfo.getString("menutype");
            storemap.put("storeid",storeid);
            storemap.put("menutype",menutype);
            List<UserNutritionMenuVo> userNutritionMenuVoList=userNutritionMenuService.queryList(storemap);
            Integer id=0;
            String menuname=null;
            String coveroic=null;
            String proinciple=null;
            for (UserNutritionMenuVo userNutritionMenuVo:userNutritionMenuVoList){
                id=userNutritionMenuVo.getId();
                menuname =userNutritionMenuVo.getMenuName();
                coveroic=userNutritionMenuVo.getMenuCoverPic();
            }
            Map dismap=new HashMap();
            dismap.put("id",id);
            List<DishesVo> dishesVos=dishesService.queryList(dismap);
        }catch (Exception e){
            return  toResponsMsgSuccess("联系管理员");
        }
        return result;
    }

    @ApiOperation(value = "套餐详情")
    @IgnoreAuth
    @PostMapping("menuinfo")
    public Object menuinfo(){
        Map<String,Object> result=new HashMap<>();
        JSONObject infojson=this.getJsonRequest();
        try {
            Map infomap=new HashMap();
            infomap.put("storeid",infojson.getInteger("storeid"));
            infomap.put("menuid",infojson.getInteger("menuid"));
            List<UserNutritionMenuVo> userNutritionMenuVoList=userNutritionMenuService.queryList(infomap);
            result.put("userNutritionMenuVoList",userNutritionMenuVoList);
        }catch (Exception e){
            return toResponsMsgSuccess("请联系管理员!");
        }
        return result;
    }
}
