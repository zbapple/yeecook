package com.platform.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.dao.ApiMealMapper;
import com.platform.entity.MealDisheEntity;
import com.platform.entity.MealEntity;
import com.platform.entity.StroeEntity;
import com.platform.service.ApiMealDisheService;
import com.platform.service.ApiMealService;
import com.platform.service.ApiMenutypeService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.entity.MenutypeEntity;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-27 16:50:29
 */
@Api(tags = "门店套餐类型")
@RestController
@RequestMapping("/api/menutype")
public class ApiMenutypeController extends ApiBaseAction {
    @Autowired
    private ApiMenutypeService menutypeService;
    @Autowired
    private ApiMealService mealService;
    @Autowired
    private ApiMealDisheService mealDisheService;
    @ApiOperation(value = "门店餐单类型")
    @IgnoreAuth
    @PostMapping("menutype")
    public Object menutype(){
        Map<String,Object> result=new HashMap<>();
        JSONObject menujosn=this.getJsonRequest();
        try {
            Integer stroeid=menujosn.getInteger("stroeid");
            Map menutypemap=new HashMap();
            menutypemap.put("stroeid",stroeid);
            List<MenutypeEntity> menutypeEntityList=menutypeService.queryList(menutypemap);
//            result.put("menutypeEntityList",menutypeEntityList);
            Integer typeid=menutypeEntityList.get(0).getId();
            List typeinfo=new ArrayList();
            for(MenutypeEntity menutypeEntity:menutypeEntityList){
                Map typemap=new HashMap();
                String typename=menutypeEntity.getMenuTypename();
                Integer mealtype=menutypeEntity.getId();
                typemap.put("typename",typename);
                typemap.put("mealtype",mealtype);
                typeinfo.add(typemap);
            }
            Map typemap=new HashMap();
            typemap.put("mealid",typeid);
            typemap.put("stroeid",stroeid);
            List<MealEntity> mealEntityList=mealService.queryList(typemap);

                Integer  id=0;
                for(MealEntity mealEntity:mealEntityList){
                    id=mealEntity.getId();
                    Map meal=new HashMap();
                    meal.put("mealid",id);
                    String dishesname=null;
                    List<MealDisheEntity> mealDisheEntityList=mealDisheService.queryList(meal);
                    List mealinfo=new ArrayList();
                    for(MealDisheEntity mealDisheEntity:mealDisheEntityList){
                        dishesname=mealDisheEntity.getDishesname();
                        mealinfo.add(dishesname);
                    }
                    mealEntity.setDishesname(mealinfo);
                }
                result.put("typeinfo",typeinfo);
             result.put("mealEntityList",mealEntityList);
        }catch (Exception e){
            return toResponsMsgSuccess("请联系管理员");
        }
        return result;
    }
}
