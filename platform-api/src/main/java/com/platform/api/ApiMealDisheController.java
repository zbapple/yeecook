package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.service.ApiMealDisheService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.entity.MealDisheVo;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-27 16:50:29
 */
@Api(tags = "套餐关联的菜品")
@RestController
@RequestMapping("/api/mealdishe")
public class ApiMealDisheController extends ApiBaseAction {
    @Autowired
    private ApiMealDisheService mealDisheService;
    @ApiOperation(value = "门店套餐菜品")
    @IgnoreAuth
    @PostMapping("mealdishes")
    public Object mealdishes(){
        Map<String,Object> result=new HashMap<>();
        JSONObject dishesjson=this.getJsonRequest();
        try {
            Map dishesmap=new HashMap();
            dishesmap.put("mealid",dishesjson.getInteger("mealid"));
            List<MealDisheVo> mealDisheVoList =mealDisheService.queryList(dishesmap);
            result.put("mealDisheEntityList", mealDisheVoList);
        }catch (Exception e){
            return toResponsMsgSuccess("请联系管理员");
        }
        return result;
    }
}
