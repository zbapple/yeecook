package com.platform.api;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import com.platform.dao.ApiDishesMapper;
import com.platform.entity.DishesVo;

import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




/**
 * 菜品表
 id
 菜品名称
 菜品封面图片
 菜品卡路里Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:40
 */
@Api(tags = "菜品")
@RestController
@RequestMapping("/api/dishes")
public class ApiDishesController extends ApiBaseAction {
    @Autowired
    private ApiDishesMapper dishesService;
    @ApiOperation(value ="获取菜品信息" )
    @PostMapping("dishesinfo")
    public Object dishesinfo(){
        JSONObject dishesparam=this.getJsonRequest();
        Integer dishesid=dishesparam.getInteger("dishesid");
        Map dishesmap=new HashMap();
        dishesmap.put("dishesid",dishesid);
        List<DishesVo>  dishesVoList=dishesService.queryList(dishesmap);
        return   toResponsSuccess(dishesVoList);
    }
}
