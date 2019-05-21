package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.ApiNewProductsVo;
import com.platform.service.impl.ApiNewProductsService;
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
 * @date 2019-05-18 10:28:00
 */
@Api(tags = "菜谱详情")
@RestController
@RequestMapping("/api/products")
public class ApiNewProductsController extends ApiBaseAction {
    @Autowired
    private ApiNewProductsService newProductsService;

    @ApiOperation("产品")
    @PostMapping(value = "poductslist")
    @IgnoreAuth
    public Object poductslist(String mfoodName){
        Map<String,Object> param=new HashMap<>();
        param.put("mfoodName",mfoodName);
        List<ApiNewProductsVo> apiNewProductsVo=newProductsService.queryList(param);
        System.out.print(apiNewProductsVo);
        return toResponsSuccess(apiNewProductsVo);
    }
}
