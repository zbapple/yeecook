package com.platform.api;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.NutrientElementsVo;

import com.platform.service.ApiNutrientElementsService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 营养元素表
 id
 营养成分名称
 元素图片
 单位
 营养元素功能描述Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:08:11
 */
@Api(tags = "营养元素")
@RestController
@RequestMapping("/api/nutrientelements")
public class ApiNutrientElementsController extends ApiBaseAction {
    @Autowired
    private ApiNutrientElementsService nutrientElementsService;

    @ApiOperation(value = "获取菜品中的营养元素")
    @PostMapping("nutrieninfo")
    public Object nutrieninfo(){
        JSONObject dishesinfojson=this.getJsonRequest();
        Map nutrieninfomap=new HashMap();
        nutrieninfomap.put("dishesid",dishesinfojson.getLong("dishesid"));
        List<NutrientElementsVo> nutrientElementsVos=nutrientElementsService.queryList(nutrieninfomap);
        return toResponsSuccess(nutrientElementsVos);
    }

    @ApiOperation(value = "营养比例")
    @PostMapping("proportion")
    @IgnoreAuth
    public Object proportion(){
        Map<String,Object> result=new HashMap<>();
        JSONObject dishesinfojson=this.getJsonRequest();
        Integer dishesid=dishesinfojson.getInteger("dishesid");
        Map nutrieninfomap=new HashMap();
        nutrieninfomap.put("dishesid",dishesid);
        List<NutrientElementsVo> nutrientElementsVoss=nutrientElementsService.queryList(nutrieninfomap);
        Double protein=0.0;
        Double fat=0.0;
        Double co2=0.0;
        for(NutrientElementsVo nutrientElementsVoItem:nutrientElementsVoss){
            if(nutrientElementsVoItem.getNutrientElementsName().equals("蛋白质")){
                protein=nutrientElementsVoItem.getContentg();
            }
            else if(nutrientElementsVoItem.getNutrientElementsName().equals("脂肪") ){
                fat=nutrientElementsVoItem.getContentg();
            }else if(nutrientElementsVoItem.getNutrientElementsName().equals("碳水化合物")){
                co2=nutrientElementsVoItem.getContentg();
            }
        }
            int total=nutrientElementsService.queryTotal(nutrieninfomap);
        Double ratioprotein=protein/total;
        Double ratiofat=fat/total;
        Double ratoco2=co2/total;
        result.put("ratioprotein",ratioprotein);
        result.put("ratiofat",ratiofat);
        result.put("ratoco2",ratoco2);
        result.put("nutrientElementsVoss",nutrientElementsVoss);
        result.put("total",total);
        return toResponsSuccess(result);
    }
}
