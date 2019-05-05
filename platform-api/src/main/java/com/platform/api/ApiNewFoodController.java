package com.platform.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.annotation.IgnoreAuth;
import com.platform.service.ApiNewFoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.entity.ApiNewFoodVo;
import com.platform.utils.R;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-06 02:59:56
 */
@Api(tags = "新菜")
@RestController
@RequestMapping("/api/newfood")
public class ApiNewFoodController {
    @Autowired
    private ApiNewFoodService newFoodService;



    /**
     * 查看信息
     */
    @ApiOperation(value = "菜品视频信息")
    @IgnoreAuth
    @PostMapping("info/{id}")
    public R info(@PathVariable("id") Integer id) {

        ApiNewFoodVo newFood = newFoodService.queryObject(id);
        Type type = new TypeToken<List<Map>>(){}.getType();
        List<Map> list1 = new Gson().fromJson(newFood.getYycsRemark(),type);
        List<Map> list2 = new Gson().fromJson(newFood.getTile3Remark(),type);
        newFood.setList1(list1);
        newFood.setList2(list2);
        return R.ok().put("newFood", newFood);
    }


}
