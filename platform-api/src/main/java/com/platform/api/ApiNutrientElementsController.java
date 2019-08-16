package com.platform.api;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.FoodIngredientsVo;
import com.platform.entity.NutrientElementsVo;

import com.platform.service.ApiFoodIngredientsService;
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
    @Autowired
    private ApiFoodIngredientsService foodIngredientsService;

    @ApiOperation(value = "获取菜品中的营养元素")
    @PostMapping("nutrieninfo")
    public Object nutrieninfo(){
        JSONObject dishesinfojson=this.getJsonRequest();
        Map nutrieninfomap=new HashMap();
        nutrieninfomap.put("dishesid",dishesinfojson.getInteger("dishesid"));
        List<FoodIngredientsVo>  foodIngredientsVos=foodIngredientsService.queryList(nutrieninfomap);
        Integer foodmaterialid=0;
        for(FoodIngredientsVo foodIngredientsVoItem:foodIngredientsVos){
            foodmaterialid=foodIngredientsVoItem.getFoodMaterialId();
        }
        Map map=new HashMap();
        map.put("id",foodmaterialid);
      List<NutrientElementsVo> nutrientElementsVos=nutrientElementsService.queryList(map);
            for(NutrientElementsVo nutrientElementsVoItem:nutrientElementsVos){
                if(nutrientElementsVoItem.getNutrientElementsName().equals("钙")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else if(nutrientElementsVoItem.getNutrientElementsName().equals("蛋白质")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("铁")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("碳水化合物")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("锌")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("镁")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("脂肪")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("钾")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("钠")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("磷")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("膳食纤维")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("维生素A")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("维生素C")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("维生素E")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("胡萝卜素")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("维生素B1")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("维生素B2")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("烟酸")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("胆固醇")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("铜")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("锰")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
                else  if(nutrientElementsVoItem.getNutrientElementsName().equals("晒")){
                    Double nutrienM=+nutrientElementsVoItem.getContentg();
                    nutrientElementsVoItem.setContentg(nutrienM);
                }
            }
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
        List<FoodIngredientsVo>  foodIngredientsVos=foodIngredientsService.queryList(nutrieninfomap);
        Integer foodmaterid=0;
        for(FoodIngredientsVo foodIngredientsVoItem:foodIngredientsVos){
            foodmaterid=foodIngredientsVoItem.getFoodMaterialId();
        }
        Map foodmatermap=new HashMap();
        foodmatermap.put("id",foodmaterid);
        List<NutrientElementsVo> nutrientElementsVoss=nutrientElementsService.queryList(foodmatermap);
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
        BigDecimal bigDecimal=new BigDecimal(ratioprotein);
        ratioprotein=bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
        Double ratiofat=fat/total;
        BigDecimal bigDecimal1=new BigDecimal(ratiofat);
        ratiofat=bigDecimal1.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
        Double ratoco2=co2/total;
        BigDecimal bigDecimal2=new BigDecimal(ratoco2);
        ratoco2=bigDecimal2.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
        result.put("ratioprotein",ratioprotein);
        result.put("ratiofat",ratiofat);
        result.put("ratoco2",ratoco2);
        return toResponsSuccess(result);
    }
}
