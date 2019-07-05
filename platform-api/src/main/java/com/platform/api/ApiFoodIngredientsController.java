package com.platform.api;


import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.FoodIngredientsVo;
import com.platform.service.ApiFoodIngredientsService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜品食材表
 id
 食材名称
 食材数量
 食材总卡路里
 菜品idController
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:06:22
 */
@Api(tags = "食材")
@RestController
@RequestMapping("/api/foodingredients")
public class ApiFoodIngredientsController extends ApiBaseAction {
    @Autowired
    private ApiFoodIngredientsService foodIngredientsService;

    @ApiOperation(value = "获取菜品食材信息")
    @PostMapping("info")
    @IgnoreAuth
    public Object info(){
        JSONObject infjsonparam=this.getJsonRequest();
        Integer dishesid=infjsonparam.getInteger("dishesid");
        Map foodingr=new HashMap();
        foodingr.put("dishesid",dishesid);
        List<FoodIngredientsVo> foodIngredientsVo=foodIngredientsService.queryList(foodingr);
        return  toResponsSuccess(foodIngredientsVo);
    }
}
