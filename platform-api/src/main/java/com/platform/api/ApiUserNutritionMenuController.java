package com.platform.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.entity.MenuDetailsVo;
import com.platform.entity.UserDetectionCycleVo;
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

}
