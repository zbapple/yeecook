package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.DishesStepsVo;
import com.platform.service.ApiDishesStepsService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




/**
 * 菜品步骤表
 id
 序号
 图片
 步骤描述
 菜品idController
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:31
 */
@Api(tags = "菜品步骤表")
@RestController
@RequestMapping("/api/DishesSteps")
public class ApiDishesStepsController extends ApiBaseAction {
    @Autowired
    private ApiDishesStepsService dishesStepsService;
    @ApiOperation(value = "菜品信息查找")
    @PostMapping("dishesstepinfo")
    public Object dishesstepinfo(Integer dishesid){
        Map<String,Object> result=new HashMap<>();
        JSONObject dishesStepparam=this.getJsonRequest();
        dishesid=dishesStepparam.getInteger("dishesid");
        List<DishesStepsVo> dishesStepsVos=dishesStepsService.queryList(dishesStepparam);
        DishesStepsVo dishesStep=new DishesStepsVo();
        String  stepdescribe="";
        for(DishesStepsVo dishesStepsVoitem:dishesStepsVos){
            stepdescribe=dishesStepsVoitem.getStepsDescribe();
        }
        String[] dishesStepsp=stepdescribe.split(",");
        result.put("dishesStepsVos",dishesStepsVos);
        return toResponsSuccess (result);
    }
}